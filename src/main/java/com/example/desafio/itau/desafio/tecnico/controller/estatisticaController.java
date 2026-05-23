package com.example.desafio.itau.desafio.tecnico.controller;

import com.example.desafio.itau.desafio.tecnico.dto.EstatisticaResponseDTO;
import com.example.desafio.itau.desafio.tecnico.service.EstatisticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class estatisticaController {
    private final EstatisticaService estatisticaService;


    public estatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaResponseDTO> obterEstatisticas() {
        EstatisticaResponseDTO estatisticas = estatisticaService.calcular();
        return ResponseEntity.ok(estatisticas);
    }
}
