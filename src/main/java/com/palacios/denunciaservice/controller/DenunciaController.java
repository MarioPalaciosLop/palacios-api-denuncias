package com.palacios.denunciaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palacios.denunciaservice.dto.DenunciaDTO;
import com.palacios.denunciaservice.entity.Denuncia;
import com.palacios.denunciaservice.service.DenunciaService;

import com.palacios.denunciaservice.converter.DenunciaConverter;

@RestController
@RequestMapping("/denuncias/")
public class DenunciaController {

    @Autowired
    private DenunciaConverter converter;

    @Autowired
    private DenunciaService service;

    @GetMapping()
    public ResponseEntity<List<DenunciaDTO>> findAll(
            @RequestParam(value = "dni", required = false, defaultValue = "") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Denuncia> grupos;
        if (dni == null) {
            grupos = service.findAll(page);
        } else {
            grupos = service.findByDni(dni, page);
        }

        if (grupos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<DenunciaDTO> gruposDTO = converter.fromEntity(grupos);
        return ResponseEntity.ok(gruposDTO);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DenunciaDTO> findById(@PathVariable("id") int id) {
        Denuncia grupos = service.findById(id);
        if (grupos == null) {
            return ResponseEntity.notFound().build();
        }
        DenunciaDTO gruposDTO = converter.fromEntity(grupos);
        return ResponseEntity.ok(gruposDTO);
    }

    @PostMapping()
    public ResponseEntity<DenunciaDTO> save(@RequestBody DenunciaDTO denunciasDTO) {
        Denuncia registro = service.save(converter.fromDTO(denunciasDTO));
        DenunciaDTO registroDTO = converter.fromEntity(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DenunciaDTO> update(@PathVariable("id") int id,
            @RequestBody DenunciaDTO denunciaDTO) {
        Denuncia registro = service.update(converter.fromDTO(denunciaDTO));
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        DenunciaDTO registroDTO = converter.fromEntity(registro);
        return ResponseEntity.ok(registroDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DenunciaDTO> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

}
