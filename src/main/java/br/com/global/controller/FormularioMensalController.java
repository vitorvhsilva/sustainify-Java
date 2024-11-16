package br.com.global.controller;

import br.com.global.dto.CadastroFormularioMensalInputDTO;
import br.com.global.service.FormularioMensalService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
