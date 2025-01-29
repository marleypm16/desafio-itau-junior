package com.challange.desafioitau.service;

import com.challange.desafioitau.model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
     private List<Transacao> transacaos  = new ArrayList<Transacao>();

     public List<Transacao> salvarTransacao(Transacao transacao){
         transacaos.add(transacao);
         return transacaos;
    }
}
