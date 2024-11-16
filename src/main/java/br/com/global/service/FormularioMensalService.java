package br.com.global.service;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.repository.RepositorioFormulariosMensal;
import br.com.global.dto.CadastroFormularioMensalInputDTO;
import br.com.global.infra.dao.FormularioMensalDAO;

public class FormularioMensalService {
    private RepositorioFormulariosMensal repositorioFormulariosMensal;
    private MoradiaService moradiaService;

    public FormularioMensalService() {
        this.repositorioFormulariosMensal = new FormularioMensalDAO();
        this.moradiaService = new MoradiaService();
    }

    public void persistirFormulario(CadastroFormularioMensalInputDTO dto) {
        Long idSindico = moradiaService.pegarSindicoPorMoradia(dto.getIdMoradia());

        FormularioMensal formularioMensal = new FormularioMensal(dto.getIdMoradia(), idSindico, dto.getValorContaLuzMensal(),
                dto.getEnergiaGastaMensal(), dto.getEmissaoCarbonoMensal(), dto.getMesEmitido(), dto.getAnoEmitido());
        repositorioFormulariosMensal.persistirFormularioMensal(formularioMensal);
        repositorioFormulariosMensal.fecharConexao();
    }
}
