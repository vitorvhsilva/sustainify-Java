package br.com.global.domain.repository;

import br.com.global.domain.model.Sindico;
import br.com.global.dto.LoginDTO;

public interface RepositorioSindicos {
    Long obterProximoId();
    void persistirSindico(Sindico sindico, Long idSindico);
    boolean sindicoExistePorCpf(String cpf);
    boolean sindicoExistePorEmail(String email);
    Long retornarIdDoSindicoPorCpf(String cpf);
    Sindico retornarSindicoPorIdSindico(Long idSindico);
    Long fazerLogin(LoginDTO dto);
}
