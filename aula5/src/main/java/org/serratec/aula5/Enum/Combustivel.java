package org.serratec.aula5.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.aula5.exception.EnumValidationException;

public enum Combustivel {
    ALCOOL(1,"ÁLCOOL"),
    GASOLINA(2,"GASOLINA"),
    DIESEL(3,"Diesel"),
    FLEX(4,"Flex");

    private Integer codigo;
    private String tipo;

    private Combustivel(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public String getTipo() {
        return tipo;
    }

    @JsonCreator
    public static Combustivel verifica(Integer value) throws EnumValidationException{
        for(Combustivel c : values()) {
            if (value.equals(c.getCodigo())) {
                return c;
            }
        }
        throw new EnumValidationException("Combustiveis Inválida! Valores validos: ALCOOL, GASOLINA, DIESEL, FLEX, ");
    }
}
