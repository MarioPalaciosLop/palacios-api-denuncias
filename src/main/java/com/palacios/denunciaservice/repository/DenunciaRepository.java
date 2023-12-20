package com.palacios.denunciaservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palacios.denunciaservice.entity.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia,Integer>{
    List<Denuncia> findByDniContaining(String dni, Pageable page);
}