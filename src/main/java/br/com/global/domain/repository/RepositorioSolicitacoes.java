package br.com.global.domain.repository;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;

import java.util.List;

public interface RepositorioSolicitacoes {
    void persistirSolicitacao(Solicitacao solicitacao);
    void fecharConexao();
    List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep);
}
