package com.empresa.pontoeletronico.application.port.out;

import com.empresa.pontoeletronico.domain.model.RegistroPonto;

import java.time.LocalDate;
import java.util.List;

public interface RegistroPontoRepository {
    List<RegistroPonto> buscarRegistrosDoDia (
            String funcionarioId,
            LocalDate data
    );

    void salvar(RegistroPonto registro);
}

