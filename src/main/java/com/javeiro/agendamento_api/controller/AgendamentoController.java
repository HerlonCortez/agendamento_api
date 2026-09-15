package com.javeiro.agendamento_api.controller;

import com.javeiro.agendamento_api.business.AgendamentoService;
import com.javeiro.agendamento_api.controller.dto.in.AgendamentoInDto;
import com.javeiro.agendamento_api.controller.dto.out.AgendamentoOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoOutDto> gravarAgendamento(@RequestBody AgendamentoInDto agendamento){
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoOutDto> buscarAgendamentoPorId(@PathVariable Long id){
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id){
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.accepted().build();
    }
}
