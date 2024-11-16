package br.com.global.service;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.model.Moradia;
import br.com.global.domain.repository.RepositorioFormulariosMensal;
import br.com.global.dto.CadastroFormularioMensalInputDTO;
import br.com.global.infra.dao.FormularioMensalDAO;

import java.util.List;

public class FormularioMensalService {
    private RepositorioFormulariosMensal repositorioFormulariosMensal;
    private MoradiaService moradiaService;

    public FormularioMensalService() {
        this.repositorioFormulariosMensal = new FormularioMensalDAO();
        this.moradiaService = new MoradiaService();
    }

    public void persistirFormulario(CadastroFormularioMensalInputDTO dto) {
        Long idSindico = moradiaService.pegarSindicoPorMoradia(dto.getIdMoradia());
        Moradia moradia = moradiaService.pegarMoradiaPorMoradia(dto.getIdMoradia());
        moradiaService.fecharConexao();

        FormularioMensal formularioMensal = new FormularioMensal(dto.getIdMoradia(), idSindico, dto.getValorContaLuzMensal(),
                dto.getEnergiaGastaMensal(), dto.getEmissaoCarbonoMensal(), moradia.getNumMoradia(), dto.getMesEmitido(), dto.getAnoEmitido());
        repositorioFormulariosMensal.persistirFormularioMensal(formularioMensal);
        repositorioFormulariosMensal.fecharConexao();
    }

    public List<FormularioMensal> pegarFormulariosPorMesAnoComunidade(Long idMoradia, Integer mes, Integer ano) {
        Long idSindico = moradiaService.pegarSindicoPorMoradia(idMoradia);
        moradiaService.fecharConexao();
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorMesAnoComunidade(idSindico, mes, ano);
        repositorioFormulariosMensal.fecharConexao();
        return formulariosMensal;

    }
}
