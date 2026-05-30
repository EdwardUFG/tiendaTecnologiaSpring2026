package com.ufg.tiendatech.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ufg.tiendatech.models.Categoria;
import com.ufg.tiendatech.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
   
    List<Producto> findByCategoria(Categoria categoria);
   
    List<Producto> findByDestacado(int destacado);
  
    List<Producto> findByPrecioBetween(double precioMin, double precioMax);
}
