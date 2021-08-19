package com.brikton.labapps.msproducto.service;

import java.util.List;

import com.brikton.labapps.msproducto.domain.Material;
import com.brikton.labapps.msproducto.domain.Unidad;

public interface ProductoService {

    Material save(Material material);

    Material update(Material materialActualizado);

    List<Material> getByStockRange(Integer stockMinimo, Integer stockMaximo) throws Exception;

    Material getByName(String nombre) throws Exception;

    List<Material> getByPrice(Double precio) throws Exception;

    List<Material> getTodos() throws Exception;

    List<Unidad> getTodasUnidades() throws Exception;

    Integer getStock(Integer idMaterial) throws Exception;

    Material getMaterial(Integer id) throws Exception;
}
