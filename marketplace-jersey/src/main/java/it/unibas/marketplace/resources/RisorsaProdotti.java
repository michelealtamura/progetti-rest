package it.unibas.marketplace.resources;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.unibas.marketplace.modello.dto.ProdottoDTO;
import it.unibas.marketplace.service.ServiceProdotti;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("/prodotti")
public class RisorsaProdotti {

    @Inject
    ServiceProdotti serviceProdotti;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "bearerAuth")
    public List<ProdottoDTO> getProdotti(@QueryParam("marca") String marca, @QueryParam("categoria") String categoria) {
        return serviceProdotti.getProdotti(marca, categoria);
    }
}
