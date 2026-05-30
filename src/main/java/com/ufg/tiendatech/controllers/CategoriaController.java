package com.ufg.tiendatech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufg.tiendatech.models.Categoria;
import com.ufg.tiendatech.services.ICategoriaService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService serviceCategorias;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        return "categorias/listCategorias";
    }

    @GetMapping("/create")
    public String crear(Categoria categoria) {

        return "categorias/formCategoria";
    }

    @PostMapping("/save")
    public String guardar(Categoria categoria) {
        serviceCategorias.guardar(categoria);
        System.out.println("Categoría guardada: " + categoria.getNombre());
        return "redirect:/categorias/index";
    }
}