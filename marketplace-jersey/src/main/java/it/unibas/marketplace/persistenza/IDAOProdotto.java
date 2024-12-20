package it.unibas.marketplace.persistenza;

import it.unibas.marketplace.modello.Prodotto;
import java.util.List;

public interface IDAOProdotto extends IDAOGenerico<Prodotto>{
    
    public List<Prodotto> findByMarcaCategoria(String marca, String categoria);
    
}
