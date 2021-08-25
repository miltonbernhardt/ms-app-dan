package com.brikton.labapps.msproducto.feign;

import com.brikton.labapps.msproducto.domain.Material;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("MS-PRODUCTO")
public interface ProductoRestInterface {

    @PostMapping
    ResponseEntity<?> crearProducto(@RequestBody Material materialNuevo);

    @PutMapping
    ResponseEntity<?> actualizarProducto(@RequestBody Material materialActualizado);

    @GetMapping(path = "/{id}")
    ResponseEntity<?> obtenerProducto(@PathVariable Integer id);

    @GetMapping(path = "/nombre/{nombre}")
    ResponseEntity<?> obtenerProductoPorNombre(@PathVariable String nombre);

    @GetMapping(path = "/stock/{idProducto}")
    ResponseEntity<?> obtenerStockProducto(@PathVariable Integer idProducto);

    @GetMapping(path = "/stock/{stockMinimo}/{stockMaximo}")
    ResponseEntity<?> obtenerProductoPorRangoStock(@PathVariable Integer stockMinimo, @PathVariable Integer stockMaximo);

    @GetMapping(path = "/precio/{precio}")
    ResponseEntity<?> obtenerProductoPorPrecio(@PathVariable Double precio);
}
