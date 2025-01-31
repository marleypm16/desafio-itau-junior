package com.challange.desafioitau.service;

import com.challange.desafioitau.model.Transacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    private final List<Transacao> transacoes = new ArrayList<>();

    public Transacao salvarTransacao(Transacao transacao) {
        transacoes.add(transacao);
        return transacao;
    }

}
