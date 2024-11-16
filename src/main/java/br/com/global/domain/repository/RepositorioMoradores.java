package br.com.global.domain.repository;

import br.com.global.domain.model.Morador;
import br.com.global.dto.LoginDTO;

public interface RepositorioMoradores {
    Long obterProximoId();
    void persistirMorador(Morador morador, Long idMorador);
    void fecharConexao();
    boolean moradorExistePorCpf(String cpf);
    boolean moradorExistePorEmail(String email);
    Long fazerLogin(LoginDTO dto);
}
