package org.serratec.aula5.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.aula5.exception.EnumValidationException;

public enum Categoria {
    HATCH, SEDAN, PICAPE, SUV;

    @JsonCreator
    public static Categoria verifica(String value) throws EnumValidationException {
        for (Categoria c : values()) {
            if (value.equals(c.name())) {
                return c;
            }
        }
        throw new EnumValidationException("Categoria Inválida! Valores validos: HATCH, SEDAN, PICAPE, SUV");
    }

    }