package br.com.global.service;

import br.com.global.domain.model.Solicitacao;

import java.util.List;

public interface ServicosSolicitacao {
    void persistirSolicitacao(Solicitacao solicitacao);
    List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep);
}
