package com.challange.desafioitau.controller;

import com.challange.desafioitau.model.Transacao;
import com.challange.desafioitau.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @PostMapping
    public  ResponseEntity<Transacao>criarTransacao(@RequestBody Transacao transacao) {
        Transacao transacaoSalva = transacaoService.salvarTransacao(transacao);
        return new ResponseEntity<>(transacaoSalva, HttpStatus.CREATED);
    }
}
