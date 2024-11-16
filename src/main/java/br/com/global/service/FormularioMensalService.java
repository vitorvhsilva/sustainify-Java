package br.com.global.service;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.model.Moradia;
import br.com.global.domain.repository.RepositorioFormulariosMensal;
import br.com.global.dto.CadastroFormularioMensalInputDTO;
import br.com.global.dto.EmissaoOutputDTO;
import br.com.global.infra.dao.FormularioMensalDAO;

import java.util.List;
import java.util.stream.Collectors;

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

        validarFormulario(formularioMensal);

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

    public List<EmissaoOutputDTO> pegarEmissoesPorAnoMoradia(Long idMoradia, Integer ano) {
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorAnoMoradia(idMoradia, ano);
        repositorioFormulariosMensal.fecharConexao();

        return converterFormulariosEmEmissoes(formulariosMensal);
    }

    public List<EmissaoOutputDTO> pegarEmissoesPorAnoComunidade(Long idMoradia, Integer ano) {
        Long idSindico = moradiaService.pegarSindicoPorMoradia(idMoradia);
        moradiaService.fecharConexao();
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorAnoComunidade(idSindico, ano);
        repositorioFormulariosMensal.fecharConexao();

        return converterFormulariosEmEmissoes(formulariosMensal);
    }

    private void validarFormulario(FormularioMensal formularioMensal) {
        FormularioMensal formularioMensalPego = repositorioFormulariosMensal.verificarSeFormularioExiste(formularioMensal);
        if (formularioMensalPego.getMesEmitido() != null || formularioMensalPego.getAnoEmitido() != null) throw new RuntimeException("Emissão do mês já feita!");
    }

    private String converterMes(Integer mesNumero) {
        return switch (mesNumero) {
            case 1 -> "Janeiro";
            case 2 -> "Fevereiro";
            case 3 -> "Março";
            case 4 -> "Abril";
            case 5 -> "Maio";
            case 6 -> "Junho";
            case 7 -> "Julho";
            case 8 -> "Agosto";
            case 9 -> "Setembro";
            case 10 -> "Outubro";
            case 11 -> "Novembro";
            case 12 -> "Dezembro";
            default -> "Mês Desconhecido";
        };
    }

    private List<EmissaoOutputDTO> converterFormulariosEmEmissoes(List<FormularioMensal> formularios) {
        return formularios.stream()
                .map(f -> new EmissaoOutputDTO(converterMes(f.getMesEmitido()), f.getEmissaoCarbonoMensal()))
                .toList();
    }
}
