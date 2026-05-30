package com.ufg.tiendatech.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufg.tiendatech.models.Categoria;
import com.ufg.tiendatech.models.Producto;
import com.ufg.tiendatech.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository repoProductos;

    @Override
    public void guardar(Producto producto) {
        repoProductos.save(producto);
    }

    @Override
    public List<Producto> buscarTodos() {
        return repoProductos.findAll();
    }

    @Override
    public Producto buscarPorId(Integer idProducto) {
        Optional<Producto> optional = repoProductos.findById(idProducto);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idProducto) {
        repoProductos.deleteById(idProducto);
    }

    @Override
    public List<Producto> buscarPorCategoria(Categoria categoria) {
        return repoProductos.findByCategoria(categoria);
    }

    @Override
    public List<Producto> buscarDestacados() {
   
        return repoProductos.findByDestacado(1);
    }
}