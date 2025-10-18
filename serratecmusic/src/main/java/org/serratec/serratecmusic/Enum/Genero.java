package org.serratec.serratecmusic.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.serratecmusic.exception.EnumValidationException;

public enum Genero {

    ROCK, POP, SAMBA, FUNK, TRAP,
    FORRO, MPB, FORRÓ,
    SERTANEJO, PAGODE, AXÉ,
    ELETRÔNICA, BLUES, JAZZ, RAP, ;

    @JsonCreator
    public static Genero verificaGenero(String value) throws EnumValidationException{
        for(Genero g : values()){
            if(value.equals(g.name())){
                return g;
            }
        }
        throw new EnumValidationException("Genero Desconhecido!");
    }

}
