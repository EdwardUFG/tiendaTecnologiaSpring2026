package com.ufg.tiendatech.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufg.tiendatech.models.Producto;
import com.ufg.tiendatech.services.ICategoriaService;
import com.ufg.tiendatech.services.IProductoService;

@Controller
@RequestMapping(value="/productos")
public class ProductoController {

    @Autowired
    private IProductoService serviceProductos;
    
    @Autowired
    private ICategoriaService serviceCategorias;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("productos", serviceProductos.buscarTodos());
        return "productos/listProductos";
    }

    @GetMapping("/create")
    public String crear(Producto producto, Model model) {
    
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        return "productos/formProducto";
    }

  
    @PostMapping("/save")
    public String guardar(Producto producto, BindingResult result, Model model) {
        
     
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }

            model.addAttribute("categorias", serviceCategorias.buscarTodas());
            return "productos/formProducto";
        }
        
        serviceProductos.guardar(producto);
        System.out.println("Producto guardado con éxito: " + producto);
        return "redirect:/productos/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}