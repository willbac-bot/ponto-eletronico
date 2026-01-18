package com.empresa.pontoeletronico.infra.repository;

import com.empresa.pontoeletronico.application.port.out.RegistroPontoRepository;
import com.empresa.pontoeletronico.domain.model.RegistroPonto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MongoRegistroPontoRepository implements RegistroPontoRepository {

    private final SpringDataRegistroPontoRepository mongoRepo;

    public MongoRegistroPontoRepository(
            SpringDataRegistroPontoRepository mongoRepo
    ) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public List<RegistroPonto> buscarRegistrosDoDia(
            String funcionarioId,
            LocalDate data
    ) {
        return mongoRepo
                .findByFuncionarioIdAndDataOrderByHorarioAsc(
                        funcionarioId, data
                )
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void salvar(RegistroPonto registro) {
        mongoRepo.save(toDocument(registro));
    }

    private RegistroPonto toDomain(RegistroPontoDocument doc) {
        return new RegistroPonto(
                doc.getFuncionarioId(),
                doc.getData(),
                doc.getHorario(),
                doc.getTipo()
        );
    }

    private RegistroPontoDocument toDocument(RegistroPonto domain) {
        RegistroPontoDocument doc = new RegistroPontoDocument();
        doc.setFuncionarioId(domain.getFuncionarioId());
        doc.setData(domain.getData());
        doc.setHorario(domain.getHorario());
        doc.setTipo(domain.getTipo());
        return doc;
    }
}
