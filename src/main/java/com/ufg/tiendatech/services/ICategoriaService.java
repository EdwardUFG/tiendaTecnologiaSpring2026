package com.ufg.tiendatech.services;

import java.util.List;
import com.ufg.tiendatech.models.Categoria;

public interface ICategoriaService {
    void guardar(Categoria categoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
    void eliminar(Integer idCategoria);
}