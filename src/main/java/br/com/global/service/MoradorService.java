package br.com.global.service;

import br.com.global.domain.model.Morador;
import br.com.global.domain.model.Solicitacao;
import br.com.global.domain.repository.RepositorioMoradores;
import br.com.global.dto.CadastroMoradorInputDTO;
import br.com.global.infra.dao.MoradorDAO;

public class MoradorService {
    private RepositorioMoradores repositorioMoradores;
    private ServicosSolicitacao servicosSolicitacao;
    private ServicosComunidade servicosComunidade;

    public MoradorService() {
        this.repositorioMoradores = new MoradorDAO();
        this.servicosSolicitacao = new SolicitacaoService();
        this.servicosComunidade = new ComunidadeService();
    }

    public void enviarSolicitacaoDeCadastro(CadastroMoradorInputDTO dto) {
        Long idMorador = repositorioMoradores.obterProximoId();

        Morador morador = new Morador(dto.getNomeMorador(), dto.getCpfMorador(), dto.getEmailMorador(), dto.getSenhaMorador(), dto.getTelefoneMorador(), 0);

        Long idSindico = servicosComunidade.retornarSindicoPorCep(dto.getCepSolicitacao());

        if (idSindico == null) throw new RuntimeException("Comunidade não existe!");

        Solicitacao solicitacao = new Solicitacao(idMorador, idSindico, dto.getCepSolicitacao(), dto.getNumResidenciaSolicitacao());
        servicosSolicitacao.verificarSeSolicitacaoExiste(solicitacao);

        repositorioMoradores.persistirMorador(morador, idMorador);
        servicosSolicitacao.persistirSolicitacao(solicitacao);

        repositorioMoradores.fecharConexao();
    }

    public void atualizarStatusAtualizacao(Long idMorador) {
        repositorioMoradores.atualizarStatusAtualizacao(idMorador);
        servicosSolicitacao.deletarSolicitacao(idMorador);
        repositorioMoradores.fecharConexao();

    }
}
