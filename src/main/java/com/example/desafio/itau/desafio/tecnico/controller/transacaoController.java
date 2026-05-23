package com.example.desafio.itau.desafio.tecnico.controller;

import com.example.desafio.itau.desafio.tecnico.dto.TransacaoRequestDTO;
import com.example.desafio.itau.desafio.tecnico.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class transacaoController {
    private final TransacaoService transacaoService;
    public transacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.limpar();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TransacaoRequestDTO>> listarTransacoes() {
        List<TransacaoRequestDTO> listaTransacoes = transacaoService.getTransacoes();
        return ResponseEntity.ok(listaTransacoes);
    }

}
