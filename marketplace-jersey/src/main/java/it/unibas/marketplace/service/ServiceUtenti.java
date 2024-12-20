package it.unibas.marketplace.service;

import it.unibas.marketplace.modello.Acquisto;
import it.unibas.marketplace.modello.Ordine;
import it.unibas.marketplace.modello.Prodotto;
import it.unibas.marketplace.modello.Utente;
import it.unibas.marketplace.modello.dto.AcquistoDTO;
import it.unibas.marketplace.modello.dto.OrdineDTO;
import it.unibas.marketplace.modello.dto.UtenteDTO;
import it.unibas.marketplace.persistenza.DAOFactory;
import it.unibas.marketplace.persistenza.IDAOAcquisto;
import it.unibas.marketplace.persistenza.IDAOOrdine;
import it.unibas.marketplace.persistenza.IDAOProdotto;
import it.unibas.marketplace.persistenza.IDAOUtente;
import it.unibas.marketplace.util.JWTUtil;
import it.unibas.marketplace.util.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ServiceUtenti {
    
    private IDAOUtente daoUtente = DAOFactory.getInstance().getDaoUtente();
    private IDAOOrdine daoOrdine = DAOFactory.getInstance().getDaoOrdine();
    private IDAOProdotto daoProdotto = DAOFactory.getInstance().getDaoProdotto();
    private IDAOAcquisto daoAcquisto = DAOFactory.getInstance().getDaoAcquisto();
    
    public String login(UtenteDTO utenteDTO) {
        Utente utente = daoUtente.findByEmail(utenteDTO.getEmail());
        if(utente == null) {
            throw new IllegalArgumentException("Email scorretta");
        }
        if(!utente.getPassword().equals(utenteDTO.getPassword())) {
            throw new IllegalArgumentException("Password errata");
        }
        return JWTUtil.generaToken(utenteDTO.getEmail());
    }

    public List<OrdineDTO> getOrdini(String email) {
        Utente utente = daoUtente.findByEmail(email);
        if(utente == null) {
            throw new IllegalArgumentException("Utente inesistente");
        }
        List<OrdineDTO> ordiniDTO = Mapper.map(utente.getOrdini(), OrdineDTO.class);
        for (OrdineDTO ordineDTO : ordiniDTO) {
            ordineDTO.setTotaleOrdine(daoOrdine.findById(ordineDTO.getId()).calcolaTotaleOrdine());            
        }
        return ordiniDTO;
    }

    public Long creaOrdine(String email, OrdineDTO ordineDTO) {
        Utente utente = daoUtente.findByEmail(email);
        if(utente == null) {
            throw new IllegalArgumentException("Utente inesistente");
        }
        if(ordineDTO.getId() != null) {
            throw new IllegalArgumentException("L'id dell'ordine deve essere nullo");
        }
        Ordine nuovoOrdine = new Ordine(ordineDTO.getDataOrdine());
        for (AcquistoDTO acquistoDTO : ordineDTO.getAcquisti()) {
            if(acquistoDTO.getId() != null) {
                throw new IllegalArgumentException("L'id degli acquisti deve essere nullo");
            }
            Prodotto prodotto = daoProdotto.findById(acquistoDTO.getProdotto().getId());
            if(prodotto == null) {
                throw new IllegalArgumentException("Id " + acquistoDTO.getProdotto().getId() + " del prodotto inesistente");
            }
            Acquisto nuovoAcquisto = new Acquisto(acquistoDTO.getQuantita(), prodotto);
            nuovoOrdine.getAcquisti().add(nuovoAcquisto);
            daoAcquisto.makePersistent(nuovoAcquisto);     
        }
        utente.getOrdini().add(nuovoOrdine);
        daoOrdine.makePersistent(nuovoOrdine);
        return nuovoOrdine.getId();
    }

    public void aggiornaOrdine(String email, Long idOrdine, OrdineDTO ordineDTO) {
        Utente utente = daoUtente.findByEmail(email);
        if(utente == null) {
            throw new IllegalArgumentException("Utente inesistente");
        }
        if(!ordineDTO.getId().equals(idOrdine)) {
            throw new IllegalArgumentException("Gli id dell'ordine da modificare non corrispondono");
        }
        Ordine ordineDaModificare = daoOrdine.findById(idOrdine);
        if(ordineDaModificare == null) {
            throw new IllegalArgumentException("Nessun ordine trovato con id " + idOrdine);
        }
        Map<Long, Integer> mappaAcquisti = new HashMap<>();
        for (AcquistoDTO acquistoDTO : ordineDTO.getAcquisti()) {
            if(acquistoDTO.getId() != null) {
                throw new IllegalArgumentException("L'id degli acquisti deve essere nullo");
            }
            if(daoAcquisto.findById(acquistoDTO.getProdotto().getId()) == null) {
                throw new IllegalArgumentException("L'Id del prodotto non esiste");
            }
            Integer quantita = mappaAcquisti.get(acquistoDTO.getId());
            if(quantita == null) {
                mappaAcquisti.put(acquistoDTO.getId(), acquistoDTO.getQuantita());
            } else {
                mappaAcquisti.put(acquistoDTO.getId(), quantita + acquistoDTO.getQuantita());
            }          
        }
    }

}
