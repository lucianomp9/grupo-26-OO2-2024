package com.unla.grupo26.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo26.entities.Almacen;
import com.unla.grupo26.repositories.IAlmacenRepositorio;
import com.unla.grupo26.services.IAlmacenServicio;

@Service
@Transactional
public class AlmacenServicio implements IAlmacenServicio {

    @Autowired
    private IAlmacenRepositorio almacenRepositorio;

    @Override
    public Almacen obtenerAlmacenPorId(Long idAlmacen) {
        return almacenRepositorio.findById(idAlmacen)
                                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
    }


}
