package br.com.global.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Premio {
    @JsonProperty
    private Long idSindico;
    @JsonProperty
    private Integer posicaoPremio;
    @JsonProperty
    private String premio;

    public Premio() {
    }

    public Premio(Long idSindico, Integer posicaoPremio, String premio) {
        this.idSindico = idSindico;
        this.posicaoPremio = posicaoPremio;
        this.premio = premio;
    }

    public Long getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(Long idSindico) {
        this.idSindico = idSindico;
    }

    public Integer getPosicaoPremio() {
        return posicaoPremio;
    }

    public void setPosicaoPremio(Integer posicaoPremio) {
        this.posicaoPremio = posicaoPremio;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }
}
