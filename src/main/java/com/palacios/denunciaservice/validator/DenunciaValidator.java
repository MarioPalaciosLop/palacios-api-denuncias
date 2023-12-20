package com.palacios.denunciaservice.validator;

import java.util.Date;

import com.palacios.denunciaservice.entity.Denuncia;

public class DenunciaValidator {

    public static void validacion(Denuncia denuncia) {
        if (denuncia == null) {
            throw new RuntimeException("La denuncia no puede ser nula");
        }

        validarDni(denuncia.getDni());
        validarFecha(denuncia.getFecha());
        validarTitulo(denuncia.getTitulo());
        validarDireccion(denuncia.getDireccion());
    }

    private static void validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new RuntimeException("El DNI no puede ser nulo o vacío");
        }
    }

    private static void validarFecha(Date fecha) {
        if (fecha == null) {
            throw new RuntimeException("La fecha no puede ser nula");
        }
    }

    private static void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new RuntimeException("El título no puede ser nulo o vacío");
        }
    }

    private static void validarDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new RuntimeException("La dirección no puede ser nula o vacía");
        }
    }
}
