package com.challange.desafioitau.controller;

import com.challange.desafioitau.model.Transacao;
import com.challange.desafioitau.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/transacao")
@Tag(name = "Transação Controller", description = "Endpoints para gerenciar transações")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @Operation(summary = "Criar uma transação", description = "Cria uma nova transação com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados faltando ou incorretos)"),
            @ApiResponse(responseCode = "422", description = "Transação não processável (data futura ou valor negativo)")
    })
    public ResponseEntity<Transacao> criarTransacao(@RequestBody Transacao transacao) {
        if (transacao.getDataHora() == null || transacao.getValor() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (transacao.getDataHora().isAfter(OffsetDateTime.now()) || transacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Transacao transacaoSalva = transacaoService.salvarTransacao(transacao);
        return new ResponseEntity<>(transacaoSalva, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar transações", description = "Retorna uma lista de todas as transações cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso")
    public ResponseEntity<List<Transacao>> listarTransacoes() {
        List<Transacao> transacoes = transacaoService.listarTransacoes();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "Deletar transações", description = "Remove todas as transações cadastradas")
    @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso")
    public ResponseEntity<Void> deletarTransacao() {
        transacaoService.deletarTransacao();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}