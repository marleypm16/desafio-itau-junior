package com.challange.desafioitau.service;

import com.challange.desafioitau.model.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EstatisticaService {
    private TransacaoService transacaoService = new TransacaoService();
    private List<Transacao> transacoes = transacaoService.listarTransacoes();
    private List<Transacao> transacoesUltimoMinuto = new ArrayList<>();
    public void calcularEstatisticas(List<Transacao> ultimasTransacoes){


    }
    public List<Transacao> ordenarTransacaoParaMaisRecentes(List<Transacao> transacaos){
        transacaos.sort((t1,t2) -> t2.getDataHora().compareTo(t1.getDataHora()));
        return transacaos;
    }
    public List<Transacao> filtrarTransacoes(List<Transacao> transacoes){
        OffsetDateTime horaAtual = OffsetDateTime.now();
        OffsetDateTime umMinuto = horaAtual.minusMinutes(1);

       return transacoes.stream().filter(transacao -> transacao.getDataHora().isAfter(umMinuto) && transacao.getDataHora().isBefore(horaAtual)).collect(Collectors.toList());
    }


}
