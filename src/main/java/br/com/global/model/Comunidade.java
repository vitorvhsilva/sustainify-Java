package br.com.global.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comunidade {
    @JsonProperty
    private Long idSindico;
    @JsonProperty
    private String ruaComunidade;
    @JsonProperty
    private String numComunidade;
    @JsonProperty
    private String cepComunidade;
    @JsonProperty
    private String bairroComunidade;

    public Comunidade() {
    }

    public Comunidade(Long idSindico, String ruaComunidade, String numComunidade, String cepComunidade, String bairroComunidade) {
        this.idSindico = idSindico;
        this.ruaComunidade = ruaComunidade;
        this.numComunidade = numComunidade;
        this.cepComunidade = cepComunidade;
        this.bairroComunidade = bairroComunidade;
    }

    public Long getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(Long idSindico) {
        this.idSindico = idSindico;
    }

    public String getRuaComunidade() {
        return ruaComunidade;
    }

    public void setRuaComunidade(String ruaComunidade) {
        this.ruaComunidade = ruaComunidade;
    }

    public String getNumComunidade() {
        return numComunidade;
    }

    public void setNumComunidade(String numComunidade) {
        this.numComunidade = numComunidade;
    }

    public String getCepComunidade() {
        return cepComunidade;
    }

    public void setCepComunidade(String cepComunidade) {
        this.cepComunidade = cepComunidade;
    }

    public String getBairroComunidade() {
        return bairroComunidade;
    }

    public void setBairroComunidade(String bairroComunidade) {
        this.bairroComunidade = bairroComunidade;
    }
}
