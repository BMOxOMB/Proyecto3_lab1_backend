package com.efrainperez.labbackend.services.Impl;

import com.efrainperez.labbackend.models.Categoria;
import com.efrainperez.labbackend.repository.CategoriaRepository;
import com.efrainperez.labbackend.services.CategoriaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;


    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(()->new RuntimeException("Error: La categoria con ID "+ id + " no existe"));
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria update(Long id, Categoria categoria) {
        Categoria categoriaExistente = findById(id);

        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Categoria categoriaExistente = findById(id);
        categoriaRepository.delete(categoriaExistente);
    }
}