package com.unla.grupo26.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo26.entities.Lote;
import com.unla.grupo26.repositories.ILoteRepositorio;
import com.unla.grupo26.services.ILoteServicio;

@Service
@Transactional
public class LoteServicio implements ILoteServicio {

    @Autowired
    private ILoteRepositorio loteRepository;

    @Override
    public Lote registrarNuevoLote(Lote lote) {
        return loteRepository.save(lote);
    }


}
