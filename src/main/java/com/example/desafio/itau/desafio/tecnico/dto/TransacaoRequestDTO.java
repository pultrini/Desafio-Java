package com.example.desafio.itau.desafio.tecnico.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class TransacaoRequestDTO {
    private BigDecimal valor;
    private OffsetDateTime data;
}
