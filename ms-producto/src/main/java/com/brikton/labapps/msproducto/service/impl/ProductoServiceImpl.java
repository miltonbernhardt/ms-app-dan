package com.brikton.labapps.msproducto.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.brikton.labapps.msproducto.domain.Material;
import com.brikton.labapps.msproducto.domain.Unidad;
import com.brikton.labapps.msproducto.repository.ProductoRepository;
import com.brikton.labapps.msproducto.repository.UnidadRepository;
import com.brikton.labapps.msproducto.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UnidadRepository unidadRepository;

    private Unidad getUnidadCorrecta(Material material) {
        List<Unidad> unidad = unidadRepository.findByDescripcion(material.getUnidad().getDescripcion());
        if (unidad.isEmpty())
            return unidadRepository.save(material.getUnidad());
        else
            return unidad.get(0);
    }

    @Override
    @Transactional
    public Material save(Material materialNuevo) {
        materialNuevo.setId(null);
        materialNuevo.setUnidad(getUnidadCorrecta(materialNuevo));
        return productoRepository.save(materialNuevo);
    }

    @Override
    @Transactional
    public Material update(Material materialActualizado) {
        materialActualizado.setUnidad(getUnidadCorrecta(materialActualizado));
        return productoRepository.save(materialActualizado);
    }

    @Override
    public List<Material> getByStockRange(Integer stockMinimo, Integer stockMaximo) throws Exception {
        List<Material> materiales = productoRepository.getMaterialPorRangoStock(stockMinimo, stockMaximo);
        if (materiales.size() == 0)
            throw new Exception(
                    "No hay productos que tenga un stock entre " + stockMinimo + " y " + stockMaximo + " unidades.");
        return materiales;
    }

    @Override
    public Material getByName(String nombre) throws Exception {
        List<Material> material = productoRepository.getMaterialPorNombre(nombre);
        if (material.size() <= 0)
            throw new Exception("No hay un producto que tenga a " + nombre + " como nombre. ");
        return material.get(0);
    }

    @Override
    public List<Material> getByPrice(Double precio) throws Exception {
        List<Material> materiales = productoRepository.getMaterialPorPrecio(precio);
        System.out.println(materiales);
        if (materiales.size() == 0)
            throw new Exception("No hay productos que tenga un precio de " + precio);
        return materiales;
    }

    @Override
    public Integer getStock(Integer idMaterial) throws Exception {
        Optional<Material> material = productoRepository.findById(idMaterial);
        if (material.isEmpty())
            throw new Exception("Material no encontrado " + material);
        else
            return material.get().getStockActual();
    }

    @Override
    public Material getMaterial(Integer id) throws Exception {
        Optional<Material> m = productoRepository.findById(id);
        if (m.isPresent())
            return m.get();
        else
            throw new Exception("Recurso no encontrado: material " + id);
    }

    @Override
    public List<Material> getTodos() throws Exception {
        return productoRepository.findAll();
    }

    @Override
    public List<Unidad> getTodasUnidades() throws Exception {
        return unidadRepository.findAll();
    }

}
