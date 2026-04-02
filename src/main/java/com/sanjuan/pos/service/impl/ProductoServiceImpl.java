package com.sanjuan.pos.service.impl;

import com.sanjuan.pos.controller.ProductoController;
import com.sanjuan.pos.dto.ActualizarProductoDTO;
import com.sanjuan.pos.entity.Color;
import com.sanjuan.pos.entity.Producto;
import com.sanjuan.pos.repository.ColorRepository;
import com.sanjuan.pos.repository.ProductoRepository;
import com.sanjuan.pos.service.InventarioLoteServiceInterface;
import com.sanjuan.pos.service.ProductoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoServiceInterface {

    private final ProductoRepository productoRepository;
    private final ColorRepository colorRepository;
    
    @Override
    public Producto buscarPorCodigo(String codigo) {
        return productoRepository.findByCodigoBarrasAndHabilitadoTrue(codigo)
                .orElseThrow(() -> new RuntimeException("El producto no existe o está inactivo"));
    }

    
    @Override
    public List<Producto> buscarPorDescripcion(String descripcion) {
        List<Producto> productos = productoRepository
                .findByDescripcionContainingIgnoreCaseAndHabilitadoTrue(descripcion);
        if (productos.isEmpty()) {
            throw new RuntimeException("No se encontraron productos activos con esa descripción");
        }
        return productos;
    }
    
    @Override
    public List<Producto> buscarPorEquivalencia(String equivalencia) {
        List<Producto> productos = productoRepository
                .findByEquivalenciaContainingIgnoreCaseAndHabilitadoTrue(equivalencia);
        if (productos.isEmpty()) {
            throw new RuntimeException("No se encontraron productos activos con esa equivalencia");
        }
        return productos;
    }
    
    @Override
    public String crearProducto(Producto producto) {
        producto.setHabilitado(true);
        producto.setFechaActualizacion(LocalDate.now());

        productoRepository.save(producto);

        return "Producto guardado exitosamente";
    }
    
    @Override
    public String actualizarProducto(String codigo, ActualizarProductoDTO dto) {
        Producto producto = productoRepository.findByCodigoBarrasAndHabilitadoTrue(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no existe"));
        //Revisar que puede cambiar el usuario vs que se actualiza o calcula por separado
        if (dto.getDescripcion() != null) producto.setDescripcion(dto.getDescripcion());
        if (dto.getColorId() != null) {
            Color color = colorRepository.findById(dto.getColorId())
                    .orElseThrow(() -> new RuntimeException("Color no existe"));
            producto.setColor(color);
        }
        if (dto.getPrecioPublico() != null) producto.setPrecioPublico(dto.getPrecioPublico());
        if (dto.getPrecioDescuento() != null) producto.setPrecioDescuento(dto.getPrecioDescuento());
        if (dto.getPrecioFarmacia() != null) producto.setPrecioFarmacia(dto.getPrecioFarmacia());
        if (dto.getUltimoCosto() != null) producto.setUltimoCosto(dto.getUltimoCosto());
        if (dto.getIva() != null) producto.setIva(dto.getIva());
        if (dto.getIeps() != null) producto.setIeps(dto.getIeps());
        if (dto.getAntibiotico() != null) producto.setAntibiotico(dto.getAntibiotico());
        if (dto.getActualizable() != null) producto.setActualizable(dto.getActualizable());
        if (dto.getEquivalencia() != null) producto.setEquivalencia(dto.getEquivalencia());
        if (dto.getSsa() != null) producto.setSsa(dto.getSsa());
        if (dto.getLaboratorio() != null) producto.setLaboratorio(dto.getLaboratorio());
        if (dto.getCls() != null) producto.setCls(dto.getCls());
        if (dto.getZona() != null) producto.setZona(dto.getZona());
        if (dto.getPareto() != null) producto.setPareto(dto.getPareto());
        if (dto.getPresentacion() != null) producto.setPresentacion(dto.getPresentacion());
        if (dto.getProveedor1() != null) producto.setProveedor1(dto.getProveedor1());
        if (dto.getProveedor2() != null) producto.setProveedor2(dto.getProveedor2());
        if (dto.getProveedor3() != null) producto.setProveedor3(dto.getProveedor3());

        producto.setFechaActualizacion(LocalDate.now());

        productoRepository.save(producto);

        return "Producto actualizado correctamente";
    }

	@Override
	public String eliminarProducto(String codigo) {
		Producto producto = productoRepository.findByCodigoBarrasAndHabilitadoTrue(codigo)
        .orElseThrow(() -> new RuntimeException("El producto no existe"));
		producto.setHabilitado(false);
		producto.setFechaActualizacion(LocalDate.now());
		
		productoRepository.save(producto);
		
		return "Producto eliminado exitosamente";
	}

    /*
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
    }*/
}