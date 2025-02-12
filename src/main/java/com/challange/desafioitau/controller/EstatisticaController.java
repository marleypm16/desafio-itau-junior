package com.challange.desafioitau.controller;

import com.challange.desafioitau.model.Estatistica;
import com.challange.desafioitau.model.Transacao;
import com.challange.desafioitau.service.EstatisticaService;
import com.challange.desafioitau.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatistica")
@Tag(name = "Estatistica Controller",description = "Rota para retornar estatisticas sobre transações nos ultimos minutos")
public class EstatisticaController {

    private final TransacaoService transacaoService;
    private final EstatisticaService estatisticaService;

    @Autowired
    public EstatisticaController(TransacaoService transacaoService, EstatisticaService estatisticaService) {
        this.transacaoService = transacaoService;
        this.estatisticaService = estatisticaService;
    }
    @Operation(summary = "Listar Estatisticas",description = "Retorna estatisticas das transações")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Estatisticas retornadas com sucesso")
    public ResponseEntity<Estatistica> getEstatistica() {
        // Buscar a lista de transações ativas
        List<Transacao> transacoes = transacaoService.listarTransacoes();

        if (transacoes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Processar estatísticas
        List<Transacao> transacoesOrdenadas = estatisticaService.ordenarTransacaoParaMaisRecentes(transacoes);
        List<Transacao> transacoesUltimoMinuto = estatisticaService.filtrarTransacoes(transacoesOrdenadas);
        Estatistica estatistica = estatisticaService.calcularEstatisticas(transacoesUltimoMinuto);

        return new ResponseEntity<>(estatistica, HttpStatus.OK);
    }
}
