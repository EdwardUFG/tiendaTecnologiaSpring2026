package com.ufg.tiendatech.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufg.tiendatech.models.Categoria;
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
    public String crear(Producto producto, Model model, RedirectAttributes attributes) {
        List<Categoria> listaCategorias = serviceCategorias.buscarTodas();
        
        if (listaCategorias.isEmpty()) {
            attributes.addFlashAttribute("alerta", "¡Alto ahí! Debes crear al menos una categoría antes de poder registrar un producto.");
            return "redirect:/categorias/index";
        }
   
        model.addAttribute("categorias", listaCategorias);
        return "productos/formProducto";
    }

    @PostMapping("/save")
    public String guardar(Producto producto, BindingResult result, Model model, 
                          @RequestParam(value = "archivoImagen", required = false) MultipartFile multiPart) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", serviceCategorias.buscarTodas());
            return "productos/formProducto";
        }

        if (multiPart != null && !multiPart.isEmpty()) {
            String ruta = "C://proyectosSQL//imagenes//"; 
            try {
                byte[] bytes = multiPart.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + multiPart.getOriginalFilename());
                Files.createDirectories(Paths.get(ruta)); 
                Files.write(rutaAbsoluta, bytes);
                
                producto.setImagen(multiPart.getOriginalFilename());
            } catch (Exception e) {
                System.out.println("Error al subir la imagen: " + e.getMessage());
            }
        }

        serviceProductos.guardar(producto);
        System.out.println("Producto guardado con éxito: " + producto.getNombre());
        return "redirect:/productos/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") Integer idProducto, Model model) {
        Producto producto = serviceProductos.buscarPorId(idProducto);
      
        if (producto == null) {
            return "redirect:/productos/index";
        }
        
        model.addAttribute("producto", producto);
        return "productos/detalleProducto"; 
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer idProducto, Model model) {
        Producto producto = serviceProductos.buscarPorId(idProducto);
        
        if (producto == null) {
            return "redirect:/productos/index";
        }
        
        if (producto.getCategoria() == null) {
            producto.setCategoria(new Categoria());
        }
        
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", serviceCategorias.buscarTodas()); 
        
        return "productos/formProducto"; 
    }       

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer idProducto) {
        serviceProductos.eliminar(idProducto);
        return "redirect:/productos/index";
    }
}