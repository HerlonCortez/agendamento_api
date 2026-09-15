package com.javeiro.agendamento_api.business;

import com.javeiro.agendamento_api.business.mapper.IAgendamentoMapper;
import com.javeiro.agendamento_api.controller.dto.in.AgendamentoInDto;
import com.javeiro.agendamento_api.controller.dto.out.AgendamentoOutDto;
import com.javeiro.agendamento_api.infrastructure.entities.Agendamento;
import com.javeiro.agendamento_api.infrastructure.exception.NotFoundException;
import com.javeiro.agendamento_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository repository;
    private final IAgendamentoMapper mapper;

    public AgendamentoOutDto gravarAgendamento(AgendamentoInDto agendamento) {
       return mapper.paraOutDto(repository.save(mapper.paraEntity(agendamento)));
    }

    public AgendamentoOutDto buscarAgendamentoPorId(Long id) {
        return mapper.paraOutDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado")));
    }

    public void cancelarAgendamento(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));

        repository.save(mapper.paraEntityCancelamento(agendamento));
    }
}
