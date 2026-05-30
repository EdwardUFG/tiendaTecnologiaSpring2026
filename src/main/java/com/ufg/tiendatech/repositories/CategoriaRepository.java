package com.ufg.tiendatech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufg.tiendatech.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
   
}