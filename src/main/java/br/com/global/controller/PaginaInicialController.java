package br.com.global.controller;

import br.com.global.dto.PaginaInicialMoradorDTO;
import br.com.global.dto.PaginaInicialSindicoDTO;
import br.com.global.service.PaginaInicialService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("paginainicial")
public class PaginaInicialController {
    private PaginaInicialService paginaInicialService;

    public PaginaInicialController() {
        this.paginaInicialService = new PaginaInicialService();
    }

    @GET
    @Path("/moradores/{idMorador}/{idMoradia}/{anoAtual}/{mesAtual}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response trazerDadosParaPaginaInicialMorador(@PathParam("idMorador") Long idMorador, @PathParam("idMoradia") Long idMoradia,
                                                   @PathParam("mesAtual") Integer mes, @PathParam("anoAtual") Integer ano) {
        try {
            PaginaInicialMoradorDTO dto = paginaInicialService.trazerDadosParaPaginaInicialMorador(idMorador, idMoradia, mes, ano);
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/sindicos/{idSindico}/{anoAtual}/{mesAtual}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response trazerDadosParaPaginaInicialSindico(@PathParam("idSindico") Long idSindico,
                                                 @PathParam("mesAtual") Integer mes, @PathParam("anoAtual") Integer ano) {
        try {
            PaginaInicialSindicoDTO dto = paginaInicialService.trazerDadosParaPaginaInicialSindico(idSindico, mes, ano);
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
