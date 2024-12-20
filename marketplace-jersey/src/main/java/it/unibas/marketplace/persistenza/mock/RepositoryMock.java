package it.unibas.marketplace.persistenza.mock;

import it.unibas.marketplace.modello.Acquisto;
import it.unibas.marketplace.modello.Ordine;
import it.unibas.marketplace.modello.Prodotto;
import it.unibas.marketplace.modello.Utente;
import java.time.LocalDate;

public class RepositoryMock extends RepositoryGenericoMock {

    private static final RepositoryMock singleton = new RepositoryMock();

    private RepositoryMock() {
        Prodotto p1 = new Prodotto("MacBook air", "Apple", "informatica", 1800);
        Prodotto p2 = new Prodotto("Licenza office", "Microsoft", "informatica", 59);
        Prodotto p3 = new Prodotto("Acqua", "San Benedetto", "cibo", 1.20);
        Prodotto p4 = new Prodotto("Alimentatore", "Samsung", "elettronica", 45.30);
        this.saveOrUpdate(p1);
        this.saveOrUpdate(p2);
        this.saveOrUpdate(p3);
        this.saveOrUpdate(p4);

        Utente u1 = new Utente("michele.altamura@unibas.it", "leclerc");
        Acquisto a1 = new Acquisto(2, p1);
        Acquisto a2 = new Acquisto(1, p2);
        Acquisto a3 = new Acquisto(3, p3);
        Ordine o1 = new Ordine(LocalDate.now());
        o1.getAcquisti().add(a1);
        o1.getAcquisti().add(a2);
        o1.getAcquisti().add(a3);
        u1.getOrdini().add(o1);
        this.saveOrUpdate(u1);

        Utente u2 = new Utente("mauro.moccia@unibas.it", "melfi");
        Acquisto a4 = new Acquisto(2, p4);
        Ordine o2 = new Ordine(LocalDate.now());
        o2.getAcquisti().add(a4);
        u2.getOrdini().add(o2);
        this.saveOrUpdate(u2);

        Utente u3 = new Utente("mattia.lomuto@unibas.it", "fiat");
        Acquisto a5 = new Acquisto(1, p1);
        Acquisto a6 = new Acquisto(4, p4);
        Acquisto a7 = new Acquisto(3, p2);
        Acquisto a8 = new Acquisto(1, p3);
        Ordine o3 = new Ordine(LocalDate.now());
        o3.getAcquisti().add(a5);
        o3.getAcquisti().add(a6);
        Ordine o4 = new Ordine(LocalDate.now());
        o4.getAcquisti().add(a7);
        o4.getAcquisti().add(a8);
        u3.getOrdini().add(o3);
        u3.getOrdini().add(o4);
        this.saveOrUpdate(u3);
        
        this.saveOrUpdate(o1);
        this.saveOrUpdate(o2);
        this.saveOrUpdate(o3);
        this.saveOrUpdate(o4);
        
        this.saveOrUpdate(a1);
        this.saveOrUpdate(a2);
        this.saveOrUpdate(a3);
        this.saveOrUpdate(a4);
        this.saveOrUpdate(a5);
        this.saveOrUpdate(a6);
        this.saveOrUpdate(a7);
        this.saveOrUpdate(a8);
        
    }

    public static RepositoryMock getInstance() {
        return singleton;
    }

}
