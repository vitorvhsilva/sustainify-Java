package br.com.global.controller;

import br.com.global.domain.model.Premio;
import br.com.global.service.MoradiaService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("moradias")
public class MoradiaController {
    private MoradiaService moradiaService;

    public MoradiaController() {
        this.moradiaService = new MoradiaService();
    }

    @GET
    @Path("/{numResidencia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarMoradiaPorMorador(@PathParam("numResidencia") String numResidencia){
        try {
            Long idMoradia = moradiaService.pegarMoradiaPorMorador(numResidencia);
            return Response.status(Response.Status.OK).entity(idMoradia).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/premios/{idMoradia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarPremiosPorMoradia(@PathParam("idMoradia") Long idMoradia){
        try {
            List<Premio> premios = moradiaService.pegarPremiosPorMoradia(idMoradia);
            return Response.status(Response.Status.OK).entity(premios).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
