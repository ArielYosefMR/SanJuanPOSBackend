package com.sanjuan.pos.service.impl;

import com.sanjuan.pos.dto.*;
import com.sanjuan.pos.entity.*;
import com.sanjuan.pos.repository.ProductoRepository;
import com.sanjuan.pos.repository.VentaRepository;
import com.sanjuan.pos.service.InventarioLoteServiceInterface;
import com.sanjuan.pos.service.VentaServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaServiceInterface {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final InventarioLoteServiceInterface inventarioService;

    @Override
    public VentaResponseDTO crearVenta(VentaRequestDTO request) {

        //Validación inicial
        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new RuntimeException("La venta no tiene detalles");
        }

        List<DetalleVenta> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVentaRequestDTO dto : request.getDetalles()) {

            if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
                throw new RuntimeException("Cantidad inválida");
            }

            Producto producto = productoRepository.findById(dto.getProductoId())
                    .filter(Producto::getActivo)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado o inactivo"));

            InventarioLote lote = inventarioService.obtenerLotePorId(dto.getLoteId());

            if (!lote.getProducto().getId().equals(producto.getId())) {
                throw new RuntimeException("El lote no pertenece al producto");
            }

            if (producto.getPrecioPublico() == null) {
                throw new RuntimeException("El producto no tiene precio definido");
            }

            inventarioService.descontarStockPorLote(lote.getId(), dto.getCantidad());

            BigDecimal precio = producto.getPrecioPublico();

            BigDecimal subtotal = precio
                    .multiply(BigDecimal.valueOf(dto.getCantidad()))
                    .setScale(2, RoundingMode.HALF_UP);

            DetalleVenta detalle = DetalleVenta.builder()
                    .producto(producto)
                    .lote(lote)
                    .cantidad(dto.getCantidad())
                    .precioUnitario(precio)
                    .subtotal(subtotal)
                    .build();

            detalles.add(detalle);
            total = total.add(subtotal);
        }

        Venta venta = Venta.builder()
                .fecha(LocalDateTime.now())
                .total(total)
                .detalles(detalles)
                .build();

        detalles.forEach(d -> d.setVenta(venta));

        Venta guardada = ventaRepository.save(venta);

        return mapToResponse(guardada);
    }

    private VentaResponseDTO mapToResponse(Venta venta) {

    	List<DetalleResponseDTO> detalles = venta.getDetalles().stream()
    		    .map(d -> {
    		        return DetalleResponseDTO.builder()
    		                .producto(d.getProducto().getDescripcion())
    		                .lote(d.getLote().getLote())
    		                .cantidad(d.getCantidad())
    		                .precioUnitario(d.getPrecioUnitario())
    		                .subtotal(d.getSubtotal())
    		                .build();
    		    })
    		    .toList();

        return VentaResponseDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .total(venta.getTotal())
                .detalles(detalles)
                .build();
    }
}