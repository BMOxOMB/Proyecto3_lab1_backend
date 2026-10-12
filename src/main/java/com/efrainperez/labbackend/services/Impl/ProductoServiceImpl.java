package com.efrainperez.labbackend.services.Impl;

import com.efrainperez.labbackend.models.Categoria;
import com.efrainperez.labbackend.models.Producto;
import com.efrainperez.labbackend.repository.ProductoRepository;
import com.efrainperez.labbackend.services.CategoriaService;
import com.efrainperez.labbackend.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    @Override
    public List<Producto> findAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Error: No existe el producto con el id: " + id));
    }

    @Override
    public Producto saveProducto(Producto producto) {
        Categoria categoria = categoriaService
                .findById(producto.getCategoria().getId());
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        Producto productoExistente = findProductoById(id);
        Categoria categoria = categoriaService.findById(producto.getCategoria().getId());
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidadEnStock(producto.getCantidadEnStock());
        productoExistente.setCategoria(categoria);
        return productoRepository.save(productoExistente);
    }

    @Override
    public void deleteProducto(Long id) {
        Producto productoExistente = findProductoById(id);
        productoRepository.delete(productoExistente);
    }
}
