package com.challange.desafioitau.controller;

import com.challange.desafioitau.model.Transacao;
import com.challange.desafioitau.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    public void criarTransacao(@RequestBody Transacao transacao) {
        transacaoService.salvarTransacao(transacao);
    }
}
