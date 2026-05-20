package com.example.desafio.itau.desafio.tecnico.service;

import com.example.desafio.itau.desafio.tecnico.dto.EstatisticaResponseDTO;
import com.example.desafio.itau.desafio.tecnico.dto.TransacaoRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class EstatisticaService {
    private final TransacaoService transacaoService;
    private final int segundosBusca;

    public EstatisticaService(
            TransacaoService transacaoService,
            @Value("${estatistica.segundos:60}") int segundosBusca){
                this.transacaoService = transacaoService;
                this.segundosBusca = segundosBusca;
    }

    public EstatisticaResponseDTO calcular() {
        OffsetDateTime tempoLimite = OffsetDateTime.now().minusSeconds(segundosBusca);

        List<TransacaoRequestDTO> transacoesValidas = transacaoService.getTransacoes()
                .stream()
                .filter(transacao -> transacao.getData().isAfter(tempoLimite))
                .toList();

        if (transacoesValidas.isEmpty()) {
            return new EstatisticaResponseDTO(0.0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        double count = transacoesValidas.size();

        BigDecimal soma = transacoesValidas.stream()
                .map(TransacaoRequestDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal min = transacoesValidas.stream()
                .map(TransacaoRequestDTO::getValor)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal max = transacoesValidas.stream()
                .map(TransacaoRequestDTO::getValor)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal avg = soma.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);

        return new EstatisticaResponseDTO(count, soma, avg, min,  max);
    }
}
