package com.empresa.pontoeletronico.infra.repository;

import com.empresa.pontoeletronico.domain.model.TipoRegistro;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "registros_ponto")
public class RegistroPontoDocument {

    @Id
    private String id;

    private String funcionarioId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(String funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate data) {
        Data = data;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public TipoRegistro getTipo() {
        return Tipo;
    }

    public void setTipo(TipoRegistro tipo) {
        Tipo = tipo;
    }

    private LocalDate Data;
    private LocalDateTime horario;
    private TipoRegistro Tipo;
}
