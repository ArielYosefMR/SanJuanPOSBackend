package com.sanjuan.pos.service;

import com.sanjuan.pos.dto.ActualizarProductoDTO;
import com.sanjuan.pos.entity.Producto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductoServiceInterface {

	Producto buscarPorCodigo(String codigo);
	List<Producto> buscarPorDescripcion(String descripcion);
	List<Producto> buscarPorEquivalencia(String equivalencia);
	String crearProducto(Producto producto);
	String actualizarProducto(String codigo, ActualizarProductoDTO dto);
	String eliminarProducto(String codigo);
	
	/*
    Producto crearProducto(Producto producto);

    Producto actualizarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);

    Producto obtenerProductoPorId(Long id);

    List<Producto> listarProductos();

    Producto buscarPorCodigoBarras(String codigoBarras);

    Producto buscarPorCodigoBarrasExtendido(String codigoBarrasExtendido);

	void importarProductos(MultipartFile file) throws Exception;
	*/
}