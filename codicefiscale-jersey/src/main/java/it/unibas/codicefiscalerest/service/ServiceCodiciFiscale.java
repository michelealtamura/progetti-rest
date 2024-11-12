package it.unibas.codicefiscalerest.service;

import it.unibas.codicefiscalerest.modello.Persona;
import it.unibas.codicefiscalerest.persistenza.DAOFactory;
import it.unibas.codicefiscalerest.persistenza.IDAOPersona;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServiceCodiciFiscale {
    
    private IDAOPersona DAOPersona = DAOFactory.getInstance().getDAOPersona();
    
    public String calcolaCaodiceFiscale(Persona persona) {
        this.DAOPersona.makePersistent(persona);
        return persona.getCodiceFiscale();
    }

    public List<Persona> findAllPersone() {
        return this.DAOPersona.findAll();
    }

    public void deleteAllPersone() {
        for (Persona persona : this.DAOPersona.findAll()) {
            this.DAOPersona.makePersistent(persona);            
        }
    }

}
