package com.javeiro.agendamento_api.business.mapper;

import com.javeiro.agendamento_api.controller.dto.in.AgendamentoInDto;
import com.javeiro.agendamento_api.controller.dto.out.AgendamentoOutDto;
import com.javeiro.agendamento_api.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {
    Agendamento paraEntity(AgendamentoInDto agendamento);
    AgendamentoOutDto paraOutDto(Agendamento agendamento);
}