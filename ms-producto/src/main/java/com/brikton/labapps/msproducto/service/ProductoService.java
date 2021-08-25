package com.brikton.labapps.msproducto.service;

import java.util.List;

import com.brikton.labapps.msproducto.domain.Material;
import com.brikton.labapps.msproducto.domain.Unidad;

public interface ProductoService {

    Material saveProducto(Material material);

    Material updateProducto(Material materialActualizado);

    List<Material> getProductoByRangoStock(Integer stockMinimo, Integer stockMaximo);

    Material getProductoByNombre(String nombre);

    List<Material> getProductoByPrecio(Double precio);

    List<Material> getAllProductos();

    List<Unidad> getUnidadesProducto();

    Integer getStockProducto(Integer idMaterial);

    Material getMaterial(Integer id);
}
