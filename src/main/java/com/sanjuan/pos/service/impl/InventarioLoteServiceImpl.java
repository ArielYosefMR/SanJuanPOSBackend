package com.sanjuan.pos.service.impl;

import com.sanjuan.pos.entity.InventarioLote;
import com.sanjuan.pos.entity.Producto;
import com.sanjuan.pos.repository.InventarioLoteRepository;
import com.sanjuan.pos.repository.ProductoRepository;
import com.sanjuan.pos.service.InventarioLoteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioLoteServiceImpl implements InventarioLoteServiceInterface {

    private final InventarioLoteRepository repository;
    private final ProductoRepository productoRepository;

    @Override
    public InventarioLote agregarLote(Long productoId, String lote, Integer cantidad, LocalDate fechaCaducidad) {

        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        Producto producto = productoRepository.findById(productoId)
                .filter(Producto::getActivo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado o inactivo"));

        InventarioLote nuevoLote = InventarioLote.builder()
                .producto(producto)
                .lote(lote)
                .stock(cantidad)
                .fechaCaducidad(fechaCaducidad)
                .build();

        return repository.save(nuevoLote);
    }

    @Override
    public List<InventarioLote> obtenerLotesDisponibles(Long productoId) {
        return repository.findByProductoIdAndStockGreaterThan(productoId, 0);
    }

    @Override
    public InventarioLote descontarStockPorLote(Long loteId, Integer cantidad) {

        InventarioLote lote = repository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        if (lote.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente en el lote");
        }

        lote.setStock(lote.getStock() - cantidad);

        return repository.save(lote);
    }

    @Override
    public Integer obtenerStockTotal(Long productoId) {
        List<InventarioLote> lotes = repository.findByProductoId(productoId);

        return lotes.stream()
                .mapToInt(InventarioLote::getStock)
                .sum();
    }

    @Override
    public InventarioLote obtenerLotePorId(Long loteId) {
        return repository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));
    }
}