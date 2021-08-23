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

    private final ProductoRepository productoRepository;
    private final UnidadRepository unidadRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, UnidadRepository unidadRepository) {
        this.productoRepository = productoRepository;
        this.unidadRepository = unidadRepository;
    }

    private Unidad getUnidadCorrecta(Material material) {
        List<Unidad> unidad = unidadRepository.findByDescripcion(material.getUnidad().getDescripcion());
        if (unidad.isEmpty())
            return unidadRepository.save(material.getUnidad());
        else
            return unidad.get(0);
    }

    @Override
    @Transactional
    public Material saveProducto(Material materialNuevo) {
        materialNuevo.setId(null);
        materialNuevo.setUnidad(getUnidadCorrecta(materialNuevo));
        return productoRepository.save(materialNuevo);
    }

    @Override
    @Transactional
    public Material updateProducto(Material materialActualizado) {
        materialActualizado.setUnidad(getUnidadCorrecta(materialActualizado));
        return productoRepository.save(materialActualizado);
    }

    @Override
    public List<Material> getProductoByRangoStock(Integer stockMinimo, Integer stockMaximo) {
        return productoRepository.getMaterialPorRangoStock(stockMinimo, stockMaximo);
    }

    @Override
    public Material getProductoByNombre(String nombre) {
        List<Material> material = productoRepository.getMaterialPorNombre(nombre);
        if (material.size() <= 0)
            return null;
        return material.get(0);
    }

    @Override
    public List<Material> getProductoByPrecio(Double precio) {
        return productoRepository.getMaterialPorPrecio(precio);
    }

    @Override
    public Integer getStockProducto(Integer idMaterial) {
        Optional<Material> material = productoRepository.findById(idMaterial);
        if (material.isEmpty())
            return -1;
        else
            return material.get().getStockActual();
    }

    @Override
    public Material getMaterial(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Material> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Unidad> getUnidadesProducto() {
        return unidadRepository.findAll();
    }

}
