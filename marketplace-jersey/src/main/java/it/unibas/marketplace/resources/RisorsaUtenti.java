package it.unibas.marketplace.resources;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.unibas.marketplace.modello.dto.OrdineDTO;
import it.unibas.marketplace.modello.dto.UtenteDTO;
import it.unibas.marketplace.service.ServiceUtenti;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;

@RequestScoped
@Path("/utenti")
public class RisorsaUtenti {

    @Inject
    private ServiceUtenti serviceUtenti;

    @Context
    SecurityContext securityContext;

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public String login(@Valid UtenteDTO utenteDTO) {
        return serviceUtenti.login(utenteDTO);
    }

    @GET
    @Path("/me/ordini")
    @Produces(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "bearerAuth")
    public List<OrdineDTO> getOrdini() {
        String email = securityContext.getUserPrincipal().getName();
        return serviceUtenti.getOrdini(email);
    }

    @POST
    @Path("/me/ordini")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "bearerAuth")
    public Long creaOrdine(@Valid OrdineDTO ordineDTO) {
        String email = securityContext.getUserPrincipal().getName();
        return serviceUtenti.creaOrdine(email, ordineDTO);
    }

    @PUT
    @Path("/me/ordini/{idOrdine}")
    @Consumes(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "bearerAuth")
    public void aggiornaOrdine(@PathParam("idOrdine") Long idOrdine, @Valid OrdineDTO ordineDTO) {
        String email = securityContext.getUserPrincipal().getName();
        serviceUtenti.aggiornaOrdine(email, idOrdine, ordineDTO);
    }

}
