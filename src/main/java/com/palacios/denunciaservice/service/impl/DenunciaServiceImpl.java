package com.palacios.denunciaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palacios.denunciaservice.entity.Denuncia;
import com.palacios.denunciaservice.repository.DenunciaRepository;
import com.palacios.denunciaservice.service.DenunciaService;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> findByDni(String dni, Pageable page) {
        try {
            return repository.findByDniContaining(dni, page);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Denuncia findByDniPath(String dni) {
        try {
            Pageable page = PageRequest.of(0, 1);
            Denuncia registro = repository.findByDniContaining(dni, page).stream().findFirst().orElse(null);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Denuncia findById(int id) {
        try {
            Denuncia registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Denuncia save(Denuncia denuncia) {
        try {
            Denuncia registro = repository.save(denuncia);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Denuncia update(Denuncia denuncia) {
        try {
            Denuncia registro = repository.findById(denuncia.getId()).orElseThrow();
            registro.setDni(denuncia.getDni());
            registro.setFecha(denuncia.getFecha());
            registro.setTitulo(denuncia.getTitulo());
            registro.setDireccion(denuncia.getDireccion());
            registro.setDescripcion(denuncia.getDescripcion());
            repository.save(registro);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try {
            Denuncia registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e) {

        }
    }

}
