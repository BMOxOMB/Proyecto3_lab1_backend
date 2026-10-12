package com.efrainperez.labbackend.controllers;

import com.efrainperez.labbackend.models.Producto;
import com.efrainperez.labbackend.services.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/producto")
@RequiredArgsConstructor
public class ProductoController {
    //@GetMapping("/X")
    //log.info("x")

    private final ProductoService productoService;

    //Get para todos los usuarios
    @GetMapping
    public ResponseEntity<List<Producto>> findAllProducto(){
        List<Producto> productos = productoService.findAllProducto();
        return ResponseEntity.ok().body(productos);
    }

    //Post solo para el super-admin-role
    @PostMapping
    @PreAuthorize("hasAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = productoService.saveProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    //Put solo para el super-admin-role
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoActualizado = productoService.updateProducto(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    //Delete solo para el super-admin-role
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }


}
