package com.javeiro.agendamento_api.business;

import com.javeiro.agendamento_api.business.mapper.IAgendamentoMapper;
import com.javeiro.agendamento_api.controller.dto.in.AgendamentoInDto;
import com.javeiro.agendamento_api.controller.dto.out.AgendamentoOutDto;
import com.javeiro.agendamento_api.infrastructure.entities.Agendamento;
import com.javeiro.agendamento_api.infrastructure.enums.StatusNotificacaoEnum;
import com.javeiro.agendamento_api.infrastructure.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private AgendamentoInDto agendamentoInDto;
    private AgendamentoOutDto agendamentoOutDto;
    private Agendamento agendamentoEntity;

    @BeforeEach
    void setUp() {
        agendamentoEntity = new Agendamento(1L, "email@email.com", "55887996578",
                LocalDateTime.of(2026, 9, 15, 11, 1, 1),
                LocalDateTime.now(),null,
                "Favor retornar a loja com urgência",
                StatusNotificacaoEnum.AGENDADO);

        agendamentoInDto = new AgendamentoInDto("cortez.parente@gmail.com","88997131420",
                "Favor retornar a loja com urgência", LocalDateTime.of(2026, 9, 15, 11, 1, 1));
        agendamentoOutDto = new AgendamentoOutDto(1l,"cortez.parente@gmail.com","88997131420",
                "Favor retornar a loja com urgência", LocalDateTime.of(2026, 9, 15, 11, 1, 1), StatusNotificacaoEnum.AGENDADO);

    }


    @Test
    void deveGravarAgendamentoComSucesso(){
        when(agendamentoMapper.paraEntity(agendamentoInDto)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraOutDto(agendamentoEntity)).thenReturn(agendamentoOutDto);

        AgendamentoOutDto outDto = agendamentoService.gravarAgendamento(agendamentoInDto);

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoInDto);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraOutDto(agendamentoEntity);

        assertThat(outDto).usingRecursiveComparison().isEqualTo(agendamentoOutDto);



    }
}
