package br.com.global.domain.repository;

import br.com.global.domain.model.Solicitacao;
import br.com.global.dto.AtualizarStatusSolicitacaoDTO;

import java.util.List;

public interface RepositorioSolicitacoes {
    void persistirSolicitacao(Solicitacao solicitacao);
    void fecharConexao();
    List<Solicitacao> pegarSolicitacoesNaComunidadePorCep(String cep);
    Solicitacao verificarSeSolicitacaoExiste(Solicitacao solicitacao);
    void deletarSolicitacao(Long idMorador);
    void atualizarSolicitacao(AtualizarStatusSolicitacaoDTO dto);
    List<Solicitacao> pegarSolicitacoesNaComunidadePorMorador(Long idMorador);
}
