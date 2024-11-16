package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioSindicos;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.infra.dao.SindicoDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SindicoService {
    private RepositorioSindicos repositorioSindicos;
    private ServicosComunidade servicosComunidade;

    public SindicoService() {
        this.repositorioSindicos = new SindicoDAO();
        this.servicosComunidade = new ComunidadeService();
    }

    public void persistirSindicoeComunidade(CadastroSindicoInputDTO dto) {
        validarSindico(dto);
        Long idSindico = repositorioSindicos.obterProximoId();
        Sindico sindico = new Sindico(dto.getNomeSindico(), dto.getCpfSindico(), dto.getEmailSindico(), dto.getSenhaSindico(), dto.getTelefoneSindico());

        Comunidade comunidade = new Comunidade(idSindico, dto.getRuaComunidade(), dto.getNumComunidade(), dto.getCepComunidade());

        repositorioSindicos.persistirSindico(sindico, idSindico);
        servicosComunidade.persistirComunidade(comunidade);

        repositorioSindicos.fecharConexao();
    }

    private boolean validarEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void validarSindico(CadastroSindicoInputDTO dto) {
        if (repositorioSindicos.sindicoExistePorEmail(dto.getEmailSindico())) {
            throw new RuntimeException("Sindico já existe pelo email!");
        }

        if (repositorioSindicos.sindicoExistePorCpf(dto.getCpfSindico())) {
            throw new RuntimeException("Usuário já existe pelo cpf!");
        }

        if (dto.getNomeSindico().length() < 3) {
            throw new RuntimeException("Nome muito pequeno! Digite novamente");
        }

        if (!validarEmail(dto.getEmailSindico())) {
            throw new RuntimeException("Email inválido! Digite novamente");
        }

        if (dto.getSenhaSindico().length() < 8) {
            throw new RuntimeException("Senha inválida! Digite novamente");
        }

        if (dto.getTelefoneSindico().length() < 11) {
            throw new RuntimeException("Telefone inválido! Digite novamente. (Exemplo: 11999999999)");
        }

        if (dto.getCpfSindico().length() != 11) {
            throw new RuntimeException("CPF inválido! Digite novamente. (Exemplo: 12345678999)");
        }
    }
}
