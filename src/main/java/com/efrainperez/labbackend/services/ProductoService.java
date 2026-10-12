package com.efrainperez.labbackend.services;

import com.efrainperez.labbackend.models.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {
    List<Producto> findAllProducto();
    Producto findProductoById(Long id);
    Producto saveProducto(Producto producto);
    Producto updateProducto(Long id, Producto producto);
    void deleteProducto(Long id);

}
