package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Premio;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioComunidades;
import br.com.global.domain.repository.RepositorioPremios;
import br.com.global.domain.repository.RepositorioSindicos;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.dto.LoginDTO;
import br.com.global.infra.dao.ComunidadeDAO;
import br.com.global.infra.dao.ConnectionFactory;
import br.com.global.infra.dao.PremioDAO;
import br.com.global.infra.dao.SindicoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SindicoService {
    private Connection conexao;
    private RepositorioSindicos repositorioSindicos;
    private RepositorioComunidades repositorioComunidades;
    private RepositorioPremios repositorioPremios;

    public SindicoService() {
        this.conexao = new ConnectionFactory().obterConexao();
        this.repositorioSindicos = new SindicoDAO(conexao);
        this.repositorioComunidades = new ComunidadeDAO(conexao);
        this.repositorioPremios = new PremioDAO(conexao);
    }

    public void persistirSindicoeComunidade(CadastroSindicoInputDTO dto) {
        validarSindico(dto);
        Long idSindico = repositorioSindicos.obterProximoId();
        Sindico sindico = new Sindico(dto.getNomeSindico(), dto.getCpfSindico(), dto.getEmailSindico(), dto.getSenhaSindico(), dto.getTelefoneSindico());

        Comunidade comunidade = new Comunidade(idSindico, dto.getRuaComunidade(), dto.getNumComunidade(), dto.getCepComunidade());

        if (repositorioComunidades.verificarSeComunidadeExistePorCep(comunidade.getCepComunidade())) throw new RuntimeException("Comunidade já existe!");

        repositorioSindicos.persistirSindico(sindico, idSindico);
        repositorioComunidades.persistirComunidade(comunidade);

        Premio premio1 = new Premio(comunidade.getIdSindico(), 1, "");
        Premio premio2 = new Premio(comunidade.getIdSindico(), 2, "");
        Premio premio3 = new Premio(comunidade.getIdSindico(), 3, "");

        repositorioPremios.persistirPremio(premio1);
        repositorioPremios.persistirPremio(premio2);
        repositorioPremios.persistirPremio(premio3);

        fecharConexao();
    }

    public Long retornarIdDoSindicoPorCpf(String cpf) {
        Long idSindico = repositorioSindicos.retornarIdDoSindicoPorCpf(cpf);
        fecharConexao();
        return idSindico;
    }


    public Sindico retornarSindicoPorIdSindico(Long idSindico) {
        Sindico sindico = repositorioSindicos.retornarSindicoPorIdSindico(idSindico);
        fecharConexao();
        return sindico;
    }

    public Long fazerLogin(LoginDTO dto) {
        Long idSindico = repositorioSindicos.fazerLogin(dto);
        fecharConexao();
        return idSindico;
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

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
