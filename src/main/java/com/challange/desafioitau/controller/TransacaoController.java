package com.challange.desafioitau.controller;

import com.challange.desafioitau.model.Transacao;
import com.challange.desafioitau.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @PostMapping
    public  ResponseEntity<Transacao>criarTransacao(@RequestBody Transacao transacao) {
        if (transacao.getDataHora() == null || transacao.getValor() == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (transacao.getDataHora().isAfter(OffsetDateTime.now()) || transacao.getValor().compareTo(BigDecimal.ZERO) < 0 ){
            return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }



        Transacao transacaoSalva = transacaoService.salvarTransacao(transacao);
        return new ResponseEntity<>(transacaoSalva,HttpStatus.CREATED);
    }
    //Desenvolvimento
    @GetMapping
    public ResponseEntity<List<Transacao>> listarTransacoes(){
        List<Transacao> transacaos = transacaoService.listarTransacoes();
        return new ResponseEntity<>(transacaos,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacao(){
        transacaoService.deletarTransacao();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
