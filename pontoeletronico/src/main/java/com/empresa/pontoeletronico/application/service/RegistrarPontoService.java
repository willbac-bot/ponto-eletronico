package com.empresa.pontoeletronico.application.service;

import com.empresa.pontoeletronico.application.port.in.RegistrarPontoUseCase;
import com.empresa.pontoeletronico.application.port.out.RegistroPontoRepository;
import com.empresa.pontoeletronico.domain.model.RegistroPonto;
import com.empresa.pontoeletronico.domain.model.TipoRegistro;
import org.springframework.boot.servlet.autoconfigure.MultipartProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RegistrarPontoService  implements RegistrarPontoUseCase {
    private final RegistroPontoRepository repository;

    public RegistrarPontoService(RegistroPontoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarPonto(String funcionarioId) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime horarioAgora = LocalDateTime.now();

        List<RegistroPonto> registros =
                repository.buscarRegistrosDoDia(funcionarioId, hoje);

        TipoRegistro tipo = definirTipoRegistro(registros);
    }

    private TipoRegistro definirTipoRegistro(List<RegistroPonto> registros) {
        if(registros.isEmpty()) {
            return TipoRegistro.ENTRADA;
        }

        RegistroPonto ultimo = registros.get(registros.size() - 1);
        return ultimo.getTipo() == TipoRegistro.ENTRADA
                ? TipoRegistro.SAIDA
                : TipoRegistro.ENTRADA;
    }
}
