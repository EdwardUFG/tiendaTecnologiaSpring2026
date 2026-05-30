package com.ufg.tiendatech.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufg.tiendatech.models.Categoria;
import com.ufg.tiendatech.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private CategoriaRepository repoCategorias;

    @Override
    public void guardar(Categoria categoria) {
        repoCategorias.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return repoCategorias.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optional = repoCategorias.findById(idCategoria);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {
        repoCategorias.deleteById(idCategoria);
    }
}
