package br.com.global.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Morador {
    @JsonProperty
    private String nomeMorador;
    @JsonProperty
    private String cpfMorador;
    @JsonProperty
    private String emailMorador;
    @JsonProperty
    private String senhaMorador;
    @JsonProperty
    private String telefoneMorador;

    public Morador() {
    }

    public Morador(String nomeMorador, String cpfMorador, String emailMorador, String senhaMorador, String telefoneMorador) {
        this.nomeMorador = nomeMorador;
        this.cpfMorador = cpfMorador;
        this.emailMorador = emailMorador;
        this.senhaMorador = senhaMorador;
        this.telefoneMorador = telefoneMorador;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getCpfMorador() {
        return cpfMorador;
    }

    public void setCpfMorador(String cpfMorador) {
        this.cpfMorador = cpfMorador;
    }

    public String getEmailMorador() {
        return emailMorador;
    }

    public void setEmailMorador(String emailMorador) {
        this.emailMorador = emailMorador;
    }

    public String getSenhaMorador() {
        return senhaMorador;
    }

    public void setSenhaMorador(String senhaMorador) {
        this.senhaMorador = senhaMorador;
    }

    public String getTelefoneMorador() {
        return telefoneMorador;
    }

    public void setTelefoneMorador(String telefoneMorador) {
        this.telefoneMorador = telefoneMorador;
    }
}
