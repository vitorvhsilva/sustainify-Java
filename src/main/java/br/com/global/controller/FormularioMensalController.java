package br.com.global.controller;

import br.com.global.domain.model.FormularioMensal;
import br.com.global.dto.CadastroFormularioMensalInputDTO;
import br.com.global.service.FormularioMensalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("formularios")
public class FormularioMensalController {
    private FormularioMensalService formularioMensalService;

    public FormularioMensalController() {
        this.formularioMensalService = new FormularioMensalService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistirFormulario(CadastroFormularioMensalInputDTO dto){
        try {
            formularioMensalService.persistirFormulario(dto);
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
    @Path("/{mes}/{ano}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarFormulariosPorMesAnoComunidade(@PathParam("mes") Integer mes, @PathParam("ano") Integer ano){
        try {
            List<FormularioMensal> formulariosMensal = formularioMensalService.pegarFormulariosPorMesAnoComunidade(mes, ano);
            return Response.status(Response.Status.OK).entity(formulariosMensal).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
