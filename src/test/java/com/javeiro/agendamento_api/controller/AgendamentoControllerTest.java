package com.javeiro.agendamento_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javeiro.agendamento_api.business.AgendamentoService;
import com.javeiro.agendamento_api.controller.dto.in.AgendamentoInDto;
import com.javeiro.agendamento_api.controller.dto.out.AgendamentoOutDto;
import com.javeiro.agendamento_api.infrastructure.enums.StatusNotificacaoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {
    @InjectMocks
    AgendamentoController agendamentoController;

    @Mock
    private AgendamentoService agendamentoService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private AgendamentoInDto agendamentoInDto;
    private AgendamentoOutDto agendamentoOutDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();
        objectMapper.registerModule(new JavaTimeModule());
        LocalDateTime dataEnvio = LocalDateTime.now();
        agendamentoInDto = new AgendamentoInDto("cortez.parente@gmail.com","88997131420",
                "Favor retornar a loja com urgência", LocalDateTime.of(2026, 9, 15, 11, 1, 1));
        agendamentoOutDto = new AgendamentoOutDto(1l,"cortez.parente@gmail.com","88997131420",
                "Favor retornar a loja com urgência", LocalDateTime.of(2026, 9, 15, 11, 1, 1), StatusNotificacaoEnum.AGENDADO);

    }

    @Test
    void deveCriarAgendamentoComWSucesso() throws Exception {
       when(agendamentoService.gravarAgendamento(agendamentoInDto)).thenReturn(agendamentoOutDto);
       mockMvc.perform(post("/agendamento")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(agendamentoInDto)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1l))
               .andExpect(jsonPath("$.emailDestinatario").value("cortez.parente@gmail.com"))
               .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoOutDto.telefoneDestinatario()))
               .andExpect(jsonPath("$.mensagem").value(agendamentoInDto.mensagem()))
               .andExpect(jsonPath("$.dataHoraEnvio").value("15-09-2026 11:01:01"))
               .andExpect(jsonPath("$.statusNotificacao").value(StatusNotificacaoEnum.AGENDADO.toString()));;

               verify(agendamentoService, times(1)).gravarAgendamento(agendamentoInDto);
    }
}
