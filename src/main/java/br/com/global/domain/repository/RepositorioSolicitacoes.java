package br.com.global.domain.repository;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;

public interface RepositorioSolicitacoes {
    void persistirSolicitacao(Solicitacao solicitacao);
    void fecharConexao();
}
