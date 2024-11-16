package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AtualizarStatusSolicitacaoDTO {
    @JsonProperty
    private Long idMorador;
    @JsonProperty
    private Long idSindico;
    @JsonProperty
    private String numMoradia;

    public AtualizarStatusSolicitacaoDTO() {
    }

    public AtualizarStatusSolicitacaoDTO(Long idMorador, Long idSindico, String numMoradia) {
        this.idMorador = idMorador;
        this.idSindico = idSindico;
        this.numMoradia = numMoradia;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Long idMorador) {
        this.idMorador = idMorador;
    }

    public Long getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(Long idSindico) {
        this.idSindico = idSindico;
    }

    public String getNumMoradia() {
        return numMoradia;
    }

    public void setNumMoradia(String numMoradia) {
        this.numMoradia = numMoradia;
    }
}
