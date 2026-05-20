package com.example.desafio.itau.desafio.tecnico.service;

import com.example.desafio.itau.desafio.tecnico.dto.TransacaoRequestDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransacaoService {
    private final List<TransacaoRequestDTO> transacoes = new CopyOnWriteArrayList<>();

    public void adicionar(TransacaoRequestDTO dto) {
        if (dto.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor deve ser maior ou igual a zero");
        }
        if (dto.getData().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("O data deve ser menor ou igual a data");
        }
        transacoes.add(dto);
    }

    public void limpar() {
        transacoes.clear();
    }

    public List<TransacaoRequestDTO> getTransacoes() {
        return transacoes;
    }
}
