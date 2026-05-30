package com.ufg.tiendatech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufg.tiendatech.services.ICategoriaService;
import com.ufg.tiendatech.services.IProductoService;

@Controller
public class HomeController {

    @Autowired
    private IProductoService serviceProductos;
    
    @Autowired
    private ICategoriaService serviceCategorias;

    @GetMapping("/")
    public String mostrarHome(Model model) {
       
        model.addAttribute("productos", serviceProductos.buscarTodos());
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        return "home";
    }
}