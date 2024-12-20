package it.unibas.marketplace.persistenza.mock;

import it.unibas.marketplace.modello.Prodotto;
import it.unibas.marketplace.persistenza.IDAOProdotto;
import java.util.ArrayList;
import java.util.List;


public class DAOProdottoMock extends DAOGenericoMock<Prodotto> implements IDAOProdotto{

    @Override
    public List<Prodotto> findByMarcaCategoria(String marca, String categoria) {
        List<Prodotto> prodotti = new ArrayList<>();
        for (Prodotto prodotto : this.findAll()) {
            if((marca == null || prodotto.getMarca().equalsIgnoreCase(marca)) &&
                    categoria == null || prodotto.getCategoria().equalsIgnoreCase(categoria)) {
                prodotti.add(prodotto);
            }           
        }
        return prodotti;
    }

}
