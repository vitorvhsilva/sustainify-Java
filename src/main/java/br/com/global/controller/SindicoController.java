package br.com.global.controller;

import br.com.global.domain.model.Sindico;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.service.SindicoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sindicos")
public class SindicoController {
    private SindicoService sindicoService;

    public SindicoController() {
        this.sindicoService = new SindicoService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistirSindicoeComunidade(CadastroSindicoInputDTO dto){
        try {
            sindicoService.persistirSindicoeComunidade(dto);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarSindicoPorIdSindico(@PathParam("id") Long idSindico){
        try {
            Sindico sindico = sindicoService.retornarSindicoPorIdSindico(idSindico);
            return Response.status(Response.Status.OK).entity(sindico).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/id/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarIdDoSindicoPorCpf(@PathParam("cpf") String cpf){
        try {
            Long idSindico = sindicoService.retornarIdDoSindicoPorCpf(cpf);
            return Response.status(Response.Status.OK).entity(idSindico).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
