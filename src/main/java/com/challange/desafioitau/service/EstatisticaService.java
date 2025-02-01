package com.challange.desafioitau.service;

import com.challange.desafioitau.model.Estatistica;
import com.challange.desafioitau.model.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstatisticaService {
    public Estatistica calcularEstatisticas(List<Transacao> ultimasTransacoes){
        Estatistica estatistica = new Estatistica();
        if (ultimasTransacoes.isEmpty()) {
            estatistica.setCount(0);
            estatistica.setMax(BigDecimal.ZERO);
            estatistica.setMin(BigDecimal.ZERO);
            estatistica.setSum(BigDecimal.ZERO);
            estatistica.setAvg(BigDecimal.ZERO);
            return estatistica;
        }
        int totalTransacoes = ultimasTransacoes.size();
        BigDecimal maxValor = ultimasTransacoes.stream().map(Transacao::getValor).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        BigDecimal minValor = ultimasTransacoes.stream().map(Transacao::getValor).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        BigDecimal sum = ultimasTransacoes.stream().map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avg = sum.divide(new BigDecimal(totalTransacoes), RoundingMode.CEILING);
        estatistica.setCount(totalTransacoes);
        estatistica.setMax(maxValor);
        estatistica.setMin(minValor);
        estatistica.setSum(sum);
        estatistica.setAvg(avg);
        return estatistica;

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
