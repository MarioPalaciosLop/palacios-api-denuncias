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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palacios.denunciaservice.entity.Denuncia;
import com.palacios.denunciaservice.service.DenunciaService;


@RestController
@RequestMapping("/denuncias/")
public class DenunciaController {
    
    @Autowired
    private DenunciaService service;

    @GetMapping()
    public ResponseEntity<List<Denuncia>> findAll(
            @RequestParam(value = "dni", required = false, defaultValue = "") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Denuncia> empresas;
        if (dni == null) {
            empresas = service.findAll(page);
        } else {
            empresas = service.findByDni(dni, page);
        }

        if (empresas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(empresas);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Denuncia> findById(@PathVariable("id") int id) {
        Denuncia denuncia = service.findById(id);
        if (denuncia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(denuncia);
    }

    @PostMapping()
    public ResponseEntity<Denuncia> create(@RequestBody Denuncia denuncia) {
        Denuncia registro = service.save(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    // @PutMapping(value = "{id}")
    // public ResponseEntity<Denuncia> update(@PathVariable("id") int id,@RequestBody Denuncia denuncia) {
    //     Denuncia registro = service.update(converter.fromDTO(gruposDTO));
    //     if (registro == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     GruposProveedorDTO registroDTO = converter.fromEntity(registro);
    //     return ResponseEntity.ok(registroDTO);
    // }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Denuncia> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }
}
