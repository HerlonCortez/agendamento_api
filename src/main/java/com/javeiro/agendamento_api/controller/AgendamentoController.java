package com.javeiro.agendamento_api.controller;

import com.javeiro.agendamento_api.business.AgendamentoService;
import com.javeiro.agendamento_api.controller.dto.in.AgendamentoInDto;
import com.javeiro.agendamento_api.controller.dto.out.AgendamentoOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoOutDto> gravarAgendamento(@RequestBody AgendamentoInDto agendamento){
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamento));
    }
}
