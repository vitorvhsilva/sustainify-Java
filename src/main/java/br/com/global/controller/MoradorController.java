package br.com.global.controller;

import br.com.global.dto.AtualizarStatusMoradorDTO;
import br.com.global.dto.CadastroMoradorInputDTO;
import br.com.global.dto.CadastroSindicoInputDTO;
import br.com.global.dto.LoginDTO;
import br.com.global.service.MoradorService;
import br.com.global.service.SindicoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("moradores")
public class MoradorController {
    private MoradorService moradorService;

    public MoradorController() {
        this.moradorService = new MoradorService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarSolicitacaoDeCadastro(CadastroMoradorInputDTO dto){
        try {
            moradorService.enviarSolicitacaoDeCadastro(dto);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fazerLogin(LoginDTO dto){
        try {
            Long idMorador = moradorService.fazerLogin(dto);
            return Response.status(Response.Status.OK).entity(idMorador).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/status")
    public Response atualizarStatusAtualizacao(AtualizarStatusMoradorDTO dto){
        try {
            moradorService.atualizarStatusAtualizacao(dto.getIdMorador());
            return Response.status(Response.Status.OK).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
