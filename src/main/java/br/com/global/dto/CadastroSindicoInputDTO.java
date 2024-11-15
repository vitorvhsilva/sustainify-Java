package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastroSindicoInputDTO {
    @JsonProperty
    private String nomeSindico;
    @JsonProperty
    private String cpfSindico;
    @JsonProperty
    private String emailSindico;
    @JsonProperty
    private String senhaSindico;
    @JsonProperty
    private String telefoneSindico;
    @JsonProperty
    private String ruaComunidade;
    @JsonProperty
    private String numComunidade;
    @JsonProperty
    private String cepComunidade;

    public CadastroSindicoInputDTO() {
    }

    public CadastroSindicoInputDTO(String nomeSindico, String cpfSindico, String emailSindico, String senhaSindico, String telefoneSindico, String ruaComunidade, String numComunidade, String cepComunidade) {
        this.nomeSindico = nomeSindico;
        this.cpfSindico = cpfSindico;
        this.emailSindico = emailSindico;
        this.senhaSindico = senhaSindico;
        this.telefoneSindico = telefoneSindico;
        this.ruaComunidade = ruaComunidade;
        this.numComunidade = numComunidade;
        this.cepComunidade = cepComunidade;
    }

    public String getNomeSindico() {
        return nomeSindico;
    }

    public void setNomeSindico(String nomeSindico) {
        this.nomeSindico = nomeSindico;
    }

    public String getCpfSindico() {
        return cpfSindico;
    }

    public void setCpfSindico(String cpfSindico) {
        this.cpfSindico = cpfSindico;
    }

    public String getEmailSindico() {
        return emailSindico;
    }

    public void setEmailSindico(String emailSindico) {
        this.emailSindico = emailSindico;
    }

    public String getSenhaSindico() {
        return senhaSindico;
    }

    public void setSenhaSindico(String senhaSindico) {
        this.senhaSindico = senhaSindico;
    }

    public String getTelefoneSindico() {
        return telefoneSindico;
    }

    public void setTelefoneSindico(String telefoneSindico) {
        this.telefoneSindico = telefoneSindico;
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
}
