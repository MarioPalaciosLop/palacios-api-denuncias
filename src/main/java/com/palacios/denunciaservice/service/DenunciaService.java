package com.palacios.denunciaservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.palacios.denunciaservice.entity.Denuncia;

public interface DenunciaService {

    public List<Denuncia> findAll(Pageable page);

    public List<Denuncia> findByDni(String dni, Pageable page);

    public Denuncia findById(int id);

    public Denuncia findByDniPath(String dni);

    public Denuncia save(Denuncia denuncia);

    public Denuncia update(Denuncia denuncia);

    public void delete(int id);

}
