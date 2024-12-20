package it.unibas.marketplace.service;

import it.unibas.marketplace.modello.dto.ProdottoDTO;
import it.unibas.marketplace.persistenza.DAOFactory;
import it.unibas.marketplace.persistenza.IDAOProdotto;
import it.unibas.marketplace.util.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServiceProdotti {
    
    private IDAOProdotto daoProdotto = DAOFactory.getInstance().getDaoProdotto();

    public List<ProdottoDTO> getProdotti(String marca, String categoria) {
        return Mapper.map(daoProdotto.findByMarcaCategoria(marca, categoria), ProdottoDTO.class);
    }

}
