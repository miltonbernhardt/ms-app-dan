package com.brikton.labapps.msproducto.rest;

import com.brikton.labapps.msproducto.domain.Material;
import com.brikton.labapps.msproducto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/producto")
public class ProductoRest {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Material materialNuevo) {
        Material creado;
        try {
            creado = this.productoService.save(materialNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(creado);
    }

    @PutMapping
    public ResponseEntity<?> actualizarProducto(@RequestBody Material materialActualizado) {
        Material actualizado;
        try {
            actualizado = this.productoService.update(materialActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Integer id) {
        Material material;
        try {
            material = this.productoService.getMaterial(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(material);
    }

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<?> obtenerProductoPorNombre(@PathVariable String nombre) {
        Material material;
        try {
            material = this.productoService.getByName(nombre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(material);
    }


    @GetMapping(path = "/stock/{idProducto}")
    public ResponseEntity<?> obtenerStockProducto(@PathVariable Integer idProducto) {
        Integer stock;
        try {
            stock = this.productoService.getStock(idProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(stock);
    }

    @GetMapping(path = "/stock/{stockMinimo}/{stockMaximo}")
    public ResponseEntity<?> obtenerProductoPorRangoStock(@PathVariable Integer stockMinimo, @PathVariable Integer stockMaximo) {
        List<Material> listaMateriales;
        try {
            listaMateriales = this.productoService.getByStockRange(stockMinimo, stockMaximo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(listaMateriales);
    }

    @GetMapping(path = "/precio/{precio}")
    public ResponseEntity<?> obtenerProductoPorPrecio(@PathVariable Double precio) {
        List<Material> listaMateriales;
        try {
            listaMateriales = this.productoService.getByPrice(precio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(listaMateriales);
    }
}
