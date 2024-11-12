package it.unibas.codicefiscalerest.rest;

import it.unibas.codicefiscalerest.modello.Persona;
import it.unibas.codicefiscalerest.service.ServiceCodiciFiscale;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("/codiciFiscali")
@PermitAll
public class RisorsaCodiciFiscali {
    
    @Inject
    private ServiceCodiciFiscale serviceCodiciFiscali;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/calcola")
    public String calcolaCodiceFiscale(@NotNull @Valid Persona persona) {
        return serviceCodiciFiscali.calcolaCaodiceFiscale(persona);       
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> findAllPersone() {
        return serviceCodiciFiscali.findAllPersone();
    }
    
    @DELETE
    public void deleteAllPersone() {
        this.serviceCodiciFiscali.deleteAllPersone();
    }

}
