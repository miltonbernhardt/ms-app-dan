package com.brikton.labapps.msproducto.rest;

import com.brikton.labapps.msproducto.domain.Material;
import com.brikton.labapps.msproducto.domain.Unidad;
import com.brikton.labapps.msproducto.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/producto")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class ProductoRest {

    protected final Logger logger = LoggerFactory.getLogger(ProductoRest.class);

    private final ProductoService productoService;

    @Autowired
    public ProductoRest(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Material materialNuevo) {
        try {
            return ResponseEntity.ok(this.productoService.saveProducto(materialNuevo));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear el producto.");
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarProducto(@RequestBody Material materialActualizado) {
        try {
            return ResponseEntity.ok(this.productoService.updateProducto(materialActualizado));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el producto.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProductos() {
        try {
            List<Material> materiales = this.productoService.getAllProductos();
            if (materiales.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos disponibles");
            return ResponseEntity.ok(materiales);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron encontrar productos.");
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getMaterial(@PathVariable Integer id) {
        try {
            Material material = this.productoService.getMaterial(id);
            if (material == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un producto con el id " + id);
            return ResponseEntity.ok(material);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo encontrar el producto.");
        }
    }

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<?> getProductoByNombre(@PathVariable String nombre) {
        try {
            Material material = this.productoService.getProductoByNombre(nombre);
            if (material == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay un producto que tenga a " + nombre + " como nombre. ");
            return ResponseEntity.ok(material);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener el producto");
        }
    }

    @GetMapping(path = "/stock/{idProducto}")
    public ResponseEntity<?> getStockProducto(@PathVariable Integer idProducto) {
        try {
            Integer stock = this.productoService.getStockProducto(idProducto);
            if (stock == -1)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un producto con ese id " + idProducto);
            return ResponseEntity.ok(stock);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener el stock del producto.");
        }
    }

    @GetMapping(path = "/stock/{stockMinimo}/{stockMaximo}")
    public ResponseEntity<?> getProductoByRangoStock(@PathVariable Integer stockMinimo,
                                                     @PathVariable Integer stockMaximo) {
        try {
            List<Material> materiales = this.productoService.getProductoByRangoStock(stockMinimo, stockMaximo);
            if (materiales.size() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos que tenga un stock entre " + stockMinimo + " y " + stockMaximo + " unidades.");
            return ResponseEntity.ok(materiales);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo encontrar los productos.");
        }
    }

    @GetMapping(path = "/precio/{precio}")
    public ResponseEntity<?> getProductoByPrecio(@PathVariable Double precio) {
        try {
            List<Material> listaMateriales = this.productoService.getProductoByPrecio(precio);
            if (listaMateriales.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos que tenga un precio de " + precio);
            return ResponseEntity.ok(listaMateriales);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron encontrar los productos.");
        }
    }

    @GetMapping(path = "/unidades")
    public ResponseEntity<?> getUnidadesProducto() {
        try {
            List<Unidad> listaUnidades = this.productoService.getUnidadesProducto();
            if (listaUnidades.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay unidades del producto");
            return ResponseEntity.ok(listaUnidades);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron encontrar unidades.");
        }
    }
}
