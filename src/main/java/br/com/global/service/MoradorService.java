package br.com.global.service;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioMoradores;
import br.com.global.dto.CadastroMoradorInputDTO;
import br.com.global.dto.LoginDTO;
import br.com.global.infra.dao.MoradorDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoradorService {
    private RepositorioMoradores repositorioMoradores;
    private SolicitacaoService solicitacaoService;
    private ComunidadeService comunidadeService;

    public MoradorService() {
        this.repositorioMoradores = new MoradorDAO();
        this.solicitacaoService = new SolicitacaoService();
        this.comunidadeService = new ComunidadeService();
    }

    public void enviarSolicitacaoDeCadastro(CadastroMoradorInputDTO dto) {
        validarMorador(dto);
        Long idMorador = repositorioMoradores.obterProximoId();

        Morador morador = new Morador(dto.getNomeMorador(), dto.getCpfMorador(), dto.getEmailMorador(), dto.getSenhaMorador(), dto.getTelefoneMorador());

        Long idSindico = comunidadeService.retornarSindicoPorCep(dto.getCepSolicitacao());

        if (idSindico == null) throw new RuntimeException("Comunidade não existe!");

        Solicitacao solicitacao = new Solicitacao(idMorador, idSindico, dto.getNomeMorador(), dto.getCpfMorador(), dto.getCepSolicitacao(), dto.getNumResidenciaSolicitacao(), 0);
        solicitacaoService.verificarSeSolicitacaoExiste(solicitacao);

        repositorioMoradores.persistirMorador(morador, idMorador);
        solicitacaoService.persistirSolicitacao(solicitacao);

        repositorioMoradores.fecharConexao();
    }

    public Long fazerLogin(LoginDTO dto) {
        Long idMorador = repositorioMoradores.fazerLogin(dto);
        repositorioMoradores.fecharConexao();
        return idMorador;
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
