package com.empresa.pontoeletronico.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroPonto {
    private String funcionarioId;
    private LocalDate data;
    private LocalDateTime horario;
    //private TipoRegistro tipo;

    public RegistroPonto(String funcionarioId, LocalDate data, LocalDateTime horario) {
        this.funcionarioId = funcionarioId;
        this.data = data;
        this.horario = horario;
        //this.tipo = tipo;
    }
}
