package com.sanjuan.pos.service.impl;

import com.sanjuan.pos.entity.Producto;
import com.sanjuan.pos.repository.ProductoRepository;
import com.sanjuan.pos.service.InventarioLoteServiceInterface;
import com.sanjuan.pos.service.ProductoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoServiceInterface {

    private final ProductoRepository repository;

    @Override
    public Producto crearProducto(Producto producto) {
        producto.setActivo(true);

        Producto productoGuardado = repository.save(producto);

        return productoGuardado;
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto existente = obtenerProductoActivo(id);

        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecioPublico(producto.getPrecioPublico());
        existente.setPrecioDescuento(producto.getPrecioDescuento());
        existente.setPrecioFarmacia(producto.getPrecioFarmacia());
        existente.setCosto(producto.getCosto());

        return repository.save(existente);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto = obtenerProductoActivo(id);
        producto.setActivo(false);
        repository.save(producto);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return obtenerProductoActivo(id);
    }

    @Override
    public List<Producto> listarProductos() {
        return repository.findByActivoTrue();
    }

    @Override
    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return repository.findByCodigoBarrasAndActivoTrue(codigoBarras)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Producto buscarPorCodigoBarrasExtendido(String codigoBarrasExtendido) {
        return repository.findByCodigoBarrasExtendidoAndActivoTrue(codigoBarrasExtendido)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Metodo filtro
    private Producto obtenerProductoActivo(Long id) {
        return repository.findById(id)
                .filter(Producto::getActivo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado o inactivo"));
    }
    
    @Override
    public void importarProductos(MultipartFile file) throws Exception {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        );

        String line;
        boolean primeraLinea = true;
        int fila = 0;

        while ((line = reader.readLine()) != null) {
            fila++;

            if (primeraLinea) {
                primeraLinea = false;
                continue;
            }

            try {
                String[] data = line.split(";");

                if (data.length < 15) {
                    System.out.println("Fila incompleta en línea: " + fila);
                    continue;
                }

                String clave = data[0].trim();

                if (repository.findByClave(clave).isPresent()) {
                    System.out.println("Producto duplicado (clave): " + clave);
                    continue;
                }

                Producto producto = Producto.builder()
                        .clave(clave)
                        .codigoBarras(data[1].trim())
                        .codigoBarrasExtendido(data[2].trim())
                        .descripcion(data[3].trim())
                        .precioPublico(new BigDecimal(data[4].trim()))
                        .precioDescuento(new BigDecimal(data[5].trim()))
                        .precioFarmacia(new BigDecimal(data[6].trim()))
                        .costo(new BigDecimal(data[7].trim()))
                        .costoPromedio(new BigDecimal(data[8].trim()))
                        .costoReal(new BigDecimal(data[9].trim()))
                        .iva(Integer.parseInt(data[10].trim()))
                        .ieps(Integer.parseInt(data[11].trim()))
                        .ieps2(Integer.parseInt(data[12].trim()))
                        .activo(Boolean.parseBoolean(data[13].trim()))
                        .antibiotico("1".equals(data[14].trim()))
                        .build();

                repository.save(producto);

            } catch (Exception e) {
                System.out.println("Error en fila " + fila + ": " + e.getMessage());
            }
        }

        reader.close();
    }
}