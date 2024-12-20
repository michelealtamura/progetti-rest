package it.unibas.marketplace.persistenza.mock;

import it.unibas.marketplace.modello.Utente;
import it.unibas.marketplace.persistenza.IDAOUtente;


public class DAOUtenteMock extends DAOGenericoMock<Utente> implements IDAOUtente{

    @Override
    public Utente findByEmail(String email) {
        for (Utente utente : this.findAll()) {
            if(utente.getEmail().equalsIgnoreCase(email)) {
                return utente;
            }
        }
        return null;
    }
    

}
