package it.unibas.codicefiscalerest.persistenza;

import it.unibas.codicefiscalerest.modello.Configurazione;
import static it.unibas.codicefiscalerest.enums.EStrategiaPersistenza.DB_HIBERNATE;
import it.unibas.codicefiscalerest.persistenza.mock.DAOPersonaMock;
import lombok.Getter;

@Getter
public class DAOFactory {

    private static final DAOFactory singleton = new DAOFactory();
    private IDAOPersona DAOPersona;

    public static DAOFactory getInstance() {
        return singleton;
    }

    private DAOFactory() {
        if (Configurazione.getInstance().getStrategiaDb().equals(DB_HIBERNATE)) {
            throw new UnsupportedOperationException("Non ancora implementato");
        } else {
            this.DAOPersona = new DAOPersonaMock();
        }
    }

}
