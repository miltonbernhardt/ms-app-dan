package com.brikton.labapps.msproducto.service;

import com.brikton.labapps.msproducto.domain.Material;

import java.util.List;

public interface ProductoService {

    Material save(Material material);

    Material update(Material materialActualizado);

    List<Material> getByStockRange(Integer stockMinimo, Integer stockMaximo) throws Exception;

    Material getByName(String nombre) throws Exception;

    List<Material> getByPrice(Double precio) throws Exception;

    Integer getStock(Integer idMaterial) throws Exception;

    Material getMaterial(Integer id) throws Exception;
}
