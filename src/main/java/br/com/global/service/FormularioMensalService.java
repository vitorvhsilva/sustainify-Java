package br.com.global.service;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.domain.model.Moradia;
import br.com.global.domain.repository.RepositorioFormulariosMensal;
import br.com.global.domain.repository.RepositorioMoradias;
import br.com.global.dto.CadastroFormularioMensalInputDTO;
import br.com.global.dto.EmissaoOutputDTO;
import br.com.global.dto.EmissaoOutputSQL;
import br.com.global.infra.dao.FormularioMensalDAO;
import br.com.global.infra.dao.MoradiaDAO;

import java.util.List;

public class FormularioMensalService {
    private RepositorioFormulariosMensal repositorioFormulariosMensal;
    private RepositorioMoradias repositorioMoradias;

    public FormularioMensalService() {
        this.repositorioFormulariosMensal = new FormularioMensalDAO();
        this.repositorioMoradias = new MoradiaDAO();
    }

    public void persistirFormulario(CadastroFormularioMensalInputDTO dto) {
        Long idSindico = repositorioMoradias.pegarSindicoPorMoradia(dto.getIdMoradia());
        Moradia moradia = repositorioMoradias.pegarMoradiaPorMoradia(dto.getIdMoradia());
        repositorioMoradias.fecharConexao();

        FormularioMensal formularioMensal = new FormularioMensal(dto.getIdMoradia(), idSindico, dto.getValorContaLuzMensal(),
                dto.getEnergiaGastaMensal(), dto.getEmissaoCarbonoMensal(), moradia.getNumMoradia(), dto.getMesEmitido(), dto.getAnoEmitido());

        validarFormulario(formularioMensal);

        repositorioFormulariosMensal.persistirFormularioMensal(formularioMensal);
        repositorioFormulariosMensal.fecharConexao();
    }

    public List<FormularioMensal> pegarFormulariosPorMesAnoMoradiaComunidade(Long idMoradia, Integer mes, Integer ano) {
        Long idSindico = repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
        repositorioMoradias.fecharConexao();
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorMesAnoSindicoComunidade(idSindico, mes, ano);
        repositorioFormulariosMensal.fecharConexao();
        return formulariosMensal;

    }

    public List<FormularioMensal> pegarFormulariosPorMesAnoSindicoComunidade(Long idSindico, Integer mes, Integer ano) {
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorMesAnoSindicoComunidade(idSindico, mes, ano);
        repositorioFormulariosMensal.fecharConexao();
        return formulariosMensal;
    }

    public List<EmissaoOutputDTO> pegarEmissoesPorAnoMoradia(Long idMoradia, Integer ano) {
        List<FormularioMensal> formulariosMensal = repositorioFormulariosMensal.pegarFormulariosPorAnoMoradia(idMoradia, ano);
        repositorioFormulariosMensal.fecharConexao();

        return formulariosMensal.stream()
                .map(f -> new EmissaoOutputDTO(converterMes(f.getMesEmitido()), f.getEmissaoCarbonoMensal()))
                .toList();
    }

    public List<EmissaoOutputDTO> pegarEmissoesPorAnoMoradiaComunidade(Long idMoradia, Integer ano) {
        Long idSindico = repositorioMoradias.pegarSindicoPorMoradia(idMoradia);
        repositorioMoradias.fecharConexao();
        List<EmissaoOutputSQL> emissoesSQL = repositorioFormulariosMensal.pegarEmissoesPorAnoComunidade(idSindico, ano);
        repositorioFormulariosMensal.fecharConexao();

        return emissoesSQL.stream()
                .map(e -> new EmissaoOutputDTO(converterMes(e.getMes()), e.getEmissao()))
                .toList();
    }

    public List<EmissaoOutputDTO> pegarEmissoesPorAnoSindicoComunidade(Long idSindico, Integer ano) {
        List<EmissaoOutputSQL> emissoesSQL = repositorioFormulariosMensal.pegarEmissoesPorAnoComunidade(idSindico, ano);
        repositorioMoradias.fecharConexao();
        repositorioFormulariosMensal.fecharConexao();

        return emissoesSQL.stream()
                .map(e -> new EmissaoOutputDTO(converterMes(e.getMes()), e.getEmissao()))
                .toList();
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

}
