package com.sanjuan.pos.service;

import com.sanjuan.pos.entity.Producto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductoServiceInterface {

    Producto crearProducto(Producto producto);

    Producto actualizarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);

    Producto obtenerProductoPorId(Long id);

    List<Producto> listarProductos();

    Producto buscarPorCodigoBarras(String codigoBarras);

    Producto buscarPorCodigoBarrasExtendido(String codigoBarrasExtendido);

	void importarProductos(MultipartFile file) throws Exception;
}