package com.javeiro.agendamento_api.infrastructure.repositories;

import com.javeiro.agendamento_api.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
