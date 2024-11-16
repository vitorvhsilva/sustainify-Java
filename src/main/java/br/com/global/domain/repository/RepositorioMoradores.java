package br.com.global.domain.repository;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Sindico;

public interface RepositorioMoradores {
    Long obterProximoId();
    void persistirMorador(Morador morador, Long idMorador);
    void fecharConexao();
    void atualizarStatusAtualizacao(Long idMorador);
    boolean moradorExistePorCpf(String cpf);
    boolean moradorExistePorEmail(String email);
}
