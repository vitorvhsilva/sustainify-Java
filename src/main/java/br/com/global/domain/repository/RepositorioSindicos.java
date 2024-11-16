package br.com.global.domain.repository;

import br.com.global.domain.model.Sindico;

public interface RepositorioSindicos {
    Long obterProximoId();
    void persistirSindico(Sindico sindico, Long idSindico);
    void fecharConexao();
    boolean sindicoExistePorCpf(String cpf);
    boolean sindicoExistePorEmail(String email);
    Long retornarIdDoSindicoPorCpf(String cpf);
    Sindico retornarSindicoPorIdSindico(Long idSindico);
}
