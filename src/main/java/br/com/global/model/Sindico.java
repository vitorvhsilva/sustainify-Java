package br.com.global.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sindico {
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

    public Sindico() {
    }

    public Sindico(String nomeSindico, String cpfSindico, String emailSindico, String senhaSindico, String telefoneSindico) {
        this.nomeSindico = nomeSindico;
        this.cpfSindico = cpfSindico;
        this.emailSindico = emailSindico;
        this.senhaSindico = senhaSindico;
        this.telefoneSindico = telefoneSindico;
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
}
