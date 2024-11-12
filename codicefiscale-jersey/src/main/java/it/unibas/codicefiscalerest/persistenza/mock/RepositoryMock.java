package it.unibas.codicefiscalerest.persistenza.mock;

import it.unibas.codicefiscalerest.modello.Persona;
import java.time.LocalDate;
import java.time.Month;

public class RepositoryMock extends RepositoryGenericoMock {

    private static final RepositoryMock singleton = new RepositoryMock();

    private RepositoryMock() {
        Persona p1 = new Persona("Mario", "Rossi", "M", LocalDate.of(2003, Month.MAY, 13), "g942");
        saveOrUpdate(p1);
    }

    public static RepositoryMock getInstance() {
        return singleton;
    }

}
