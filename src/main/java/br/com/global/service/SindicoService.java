package br.com.global.service;

import br.com.global.domain.model.Comunidade;
import br.com.global.domain.model.Sindico;
import br.com.global.domain.repository.RepositorioSindicos;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.infra.dao.SindicoDAO;

public class SindicoService {
    private RepositorioSindicos repositorioSindicos;
    private ServicosComunidade servicosComunidade;

    public SindicoService() {
        this.repositorioSindicos = new SindicoDAO();
        this.servicosComunidade = new ComunidadeService();
    }

    public void persistirSindicoeComunidade(CadastroSindicoInputDTO dto) {
        Long idSindico = repositorioSindicos.obterProximoId();
        Sindico sindico = new Sindico(dto.getNomeSindico(), dto.getCpfSindico(), dto.getEmailSindico(), dto.getSenhaSindico(), dto.getTelefoneSindico());
        repositorioSindicos.persistirSindico(sindico, idSindico);

        Comunidade comunidade = new Comunidade(idSindico, dto.getRuaComunidade(), dto.getNumComunidade(), dto.getCepComunidade());
        servicosComunidade.persistirComunidade(comunidade);

        repositorioSindicos.fecharConexao();
    }
}
