package com.sanjuan.pos.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjuan.pos.dto.CompraDTO;
import com.sanjuan.pos.dto.DetalleCompraDTO;
import com.sanjuan.pos.entity.Compra;
import com.sanjuan.pos.entity.DetalleCompra;
import com.sanjuan.pos.entity.InventarioLote;
import com.sanjuan.pos.entity.Producto;
import com.sanjuan.pos.entity.Proveedor;
import com.sanjuan.pos.repository.CompraRepository;
import com.sanjuan.pos.repository.DetalleCompraRepository;
import com.sanjuan.pos.repository.InventarioLoteRepository;
import com.sanjuan.pos.repository.ProductoRepository;
import com.sanjuan.pos.repository.ProveedorRepository;
import com.sanjuan.pos.service.CompraServiceInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraServiceInterface {

    private final CompraRepository compraRepository;
    private final ProveedorRepository proveedorRepository;
    private final InventarioLoteRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    private final DetalleCompraRepository detalleCompraRepository;

    @Transactional
    public void guardarCompra(CompraDTO dto) {

        Proveedor proveedor = proveedorRepository
            .findByRFC(dto.getRfc())
            .orElseThrow();

        Compra compra = new Compra();
        compra.setFactura(dto.getFactura());
        compra.setFecha(dto.getFecha());
        compra.setProveedor(proveedor);

        compraRepository.save(compra);

        BigDecimal total = BigDecimal.ZERO;

        for (DetalleCompraDTO d : dto.getDetalles()) {

            Producto producto = productoRepository.findByClave(d.getCodigo())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + d.getCodigo()));

            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(compra);
            detalle.setCodigo(producto.getClave());    
            detalle.setDescripcion(producto.getDescripcion());
            detalle.setCantidad(d.getCantidad());
            detalle.setCosto(d.getCosto());

            BigDecimal subtotal = d.getCosto().multiply(BigDecimal.valueOf(d.getCantidad()));
            detalle.setSubtotal(subtotal);

            detalle.setLote(d.getLote());
            detalle.setCaducidad(d.getCaducidad());

            detalleCompraRepository.save(detalle);

            total = total.add(subtotal);

            InventarioLote lote = inventarioRepository
                .findByProductoAndLote(producto, d.getLote())
                .orElse(null);

            if (lote == null) {
                lote = new InventarioLote();
                lote.setProducto(producto);
                lote.setLote(d.getLote());
                lote.setFechaCaducidad(d.getCaducidad());
                lote.setStock(d.getCantidad());
            } else {
                lote.setStock(lote.getStock() + d.getCantidad());
            }

            inventarioRepository.save(lote);
        }

        compra.setTotal(total);
        compraRepository.save(compra);
    }
    
    @Override
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }
}