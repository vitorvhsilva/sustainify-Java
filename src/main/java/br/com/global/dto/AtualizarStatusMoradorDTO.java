package br.com.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AtualizarStatusMoradorDTO {
    @JsonProperty
    private Long idMorador;

    public AtualizarStatusMoradorDTO() {
    }

    public AtualizarStatusMoradorDTO(Long idMorador) {
        this.idMorador = idMorador;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Long idMorador) {
        this.idMorador = idMorador;
    }
}
