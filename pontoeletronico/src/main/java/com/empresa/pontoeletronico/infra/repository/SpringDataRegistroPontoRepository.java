package com.empresa.pontoeletronico.infra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SpringDataRegistroPontoRepository extends MongoRepository<RegistroPontoDocument, String> {

    List<RegistroPontoDocument>
    findByFuncionarioIdAndDataOrderByHorarioAsc(
            String funcionarioId,
            LocalDate data
    );
}

