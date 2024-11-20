package br.com.global.service;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioComunidades;
import br.com.global.domain.repository.RepositorioMoradores;
import br.com.global.domain.repository.RepositorioSolicitacoes;
import br.com.global.dto.CadastroMoradorInputDTO;
import br.com.global.dto.LoginDTO;
import br.com.global.infra.dao.ComunidadeDAO;
import br.com.global.infra.dao.MoradorDAO;
import br.com.global.infra.dao.SolicitacaoDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoradorService {
    private RepositorioMoradores repositorioMoradores;
    private RepositorioSolicitacoes repositorioSolicitacoes;
    private RepositorioComunidades repositorioComunidades;

    public MoradorService() {
        this.repositorioMoradores = new MoradorDAO();
        this.repositorioSolicitacoes = new SolicitacaoDAO();
        this.repositorioComunidades = new ComunidadeDAO();
    }

    public void enviarSolicitacaoDeCadastro(CadastroMoradorInputDTO dto) {
        validarMorador(dto);
        Long idMorador = repositorioMoradores.obterProximoId();

        Morador morador = new Morador(dto.getNomeMorador(), dto.getCpfMorador(), dto.getEmailMorador(), dto.getSenhaMorador(), dto.getTelefoneMorador());

        Long idSindico = repositorioComunidades.retornarSindicoPorCep(dto.getCepSolicitacao());

        if (idSindico == null) throw new RuntimeException("Comunidade não existe!");

        Solicitacao solicitacao = new Solicitacao(idMorador, idSindico, dto.getNomeMorador(), dto.getCpfMorador(), dto.getCepSolicitacao(), dto.getNumResidenciaSolicitacao(), 0);

        Solicitacao solicitacaoPega = repositorioSolicitacoes.verificarSeSolicitacaoExiste(solicitacao);
        if (solicitacaoPega.getNumResidenciaSolicitacao() != null) throw new RuntimeException("Solicitação já existe");

        repositorioMoradores.persistirMorador(morador, idMorador);
        repositorioSolicitacoes.persistirSolicitacao(solicitacao);

        repositorioMoradores.fecharConexao();
    }

    public Long fazerLogin(LoginDTO dto) {
        Long idMorador = repositorioMoradores.fazerLogin(dto);
        repositorioMoradores.fecharConexao();
        return idMorador;
    }

    public Morador retornarMoradorPorIdMorador(Long idMorador) {
        Morador morador = repositorioMoradores.retornarMoradorPorIdMorador(idMorador);
        repositorioMoradores.fecharConexao();
        return morador;
    }

    private boolean validarEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void validarMorador(CadastroMoradorInputDTO dto) {
        if (repositorioMoradores.moradorExistePorEmail(dto.getEmailMorador())) {
            throw new RuntimeException("Morador já existe pelo email!");
        }

        if (repositorioMoradores.moradorExistePorCpf(dto.getCpfMorador())) {
            throw new RuntimeException("Usuário já existe pelo cpf!");
        }

        if (dto.getNomeMorador().length() < 3) {
            throw new RuntimeException("Nome muito pequeno! Digite novamente");
        }

        if (!validarEmail(dto.getEmailMorador())) {
            throw new RuntimeException("Email inválido! Digite novamente");
        }

        if (dto.getSenhaMorador().length() < 8) {
            throw new RuntimeException("Senha inválida! Digite novamente");
        }

        if (dto.getTelefoneMorador().length() < 11) {
            throw new RuntimeException("Telefone inválido! Digite novamente. (Exemplo: 11999999999)");
        }

        if (dto.getCpfMorador().length() != 11) {
            throw new RuntimeException("CPF inválido! Digite novamente. (Exemplo: 12345678999)");
        }
    }

}
