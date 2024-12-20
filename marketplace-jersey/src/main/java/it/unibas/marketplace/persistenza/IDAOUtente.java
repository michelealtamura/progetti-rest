package it.unibas.marketplace.persistenza;

import it.unibas.marketplace.modello.Utente;

public interface IDAOUtente extends IDAOGenerico<Utente>{
    
    public Utente findByEmail(String email);
    
}
