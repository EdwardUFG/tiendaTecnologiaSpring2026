package com.ufg.tiendatech.services;

import java.util.List;
import com.ufg.tiendatech.models.Categoria;
import com.ufg.tiendatech.models.Producto;

public interface IProductoService {
    void guardar(Producto producto);
    List<Producto> buscarTodos();
    Producto buscarPorId(Integer idProducto);
    void eliminar(Integer idProducto);

    List<Producto> buscarPorCategoria(Categoria categoria);
    List<Producto> buscarDestacados();
}