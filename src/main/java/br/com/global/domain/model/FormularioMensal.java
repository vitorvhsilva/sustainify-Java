package br.com.global.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormularioMensal {
    @JsonProperty
    private Long idMoradia;
    @JsonProperty
    private Long idSindico;
    @JsonProperty
    private Double valorContaLuzMensal;
    @JsonProperty
    private Double energiaGastaMensal;
    @JsonProperty
    private Double emissaoCarbonoMensal;
    @JsonProperty
    private String numResidencia;
    @JsonProperty
    private Integer mesEmitido;
    @JsonProperty
    private Integer anoEmitido;

    public FormularioMensal() {
    }

    public FormularioMensal(Long idMoradia, Long idSindico, Double valorContaLuzMensal, Double energiaGastaMensal, Double emissaoCarbonoMensal, String numResidencia, Integer mesEmitido, Integer anoEmitido) {
        this.idMoradia = idMoradia;
        this.idSindico = idSindico;
        this.valorContaLuzMensal = valorContaLuzMensal;
        this.energiaGastaMensal = energiaGastaMensal;
        this.emissaoCarbonoMensal = emissaoCarbonoMensal;
        this.numResidencia = numResidencia;
        this.mesEmitido = mesEmitido;
        this.anoEmitido = anoEmitido;
    }

    public Long getIdMoradia() {
        return idMoradia;
    }

    public void setIdMoradia(Long idMoradia) {
        this.idMoradia = idMoradia;
    }

    public Double getValorContaLuzMensal() {
        return valorContaLuzMensal;
    }

    public void setValorContaLuzMensal(Double valorContaLuzMensal) {
        this.valorContaLuzMensal = valorContaLuzMensal;
    }

    public Double getEmissaoCarbonoMensal() {
        return emissaoCarbonoMensal;
    }

    public void setEmissaoCarbonoMensal(Double emissaoCarbonoMensal) {
        this.emissaoCarbonoMensal = emissaoCarbonoMensal;
    }

    public Long getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(Long idSindico) {
        this.idSindico = idSindico;
    }

    public Double getEnergiaGastaMensal() {
        return energiaGastaMensal;
    }

    public void setEnergiaGastaMensal(Double energiaGastaMensal) {
        this.energiaGastaMensal = energiaGastaMensal;
    }

    public Integer getMesEmitido() {
        return mesEmitido;
    }

    public void setMesEmitido(Integer mesEmitido) {
        this.mesEmitido = mesEmitido;
    }

    public Integer getAnoEmitido() {
        return anoEmitido;
    }

    public void setAnoEmitido(Integer anoEmitido) {
        this.anoEmitido = anoEmitido;
    }

    public String getNumResidencia() {
        return numResidencia;
    }

    public void setNumResidencia(String numResidencia) {
        this.numResidencia = numResidencia;
    }
}
