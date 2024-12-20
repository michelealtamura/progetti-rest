package it.unibas.marketplace.persistenza;

import it.unibas.marketplace.modello.Configurazione;
import static it.unibas.marketplace.enums.EStrategiaPersistenza.DB_HIBERNATE;
import it.unibas.marketplace.persistenza.mock.DAOAcquistoMock;
import it.unibas.marketplace.persistenza.mock.DAOOrdineMock;
import it.unibas.marketplace.persistenza.mock.DAOProdottoMock;
import it.unibas.marketplace.persistenza.mock.DAOUtenteMock;

public class DAOFactory {

    private static final DAOFactory singleton = new DAOFactory();
    private IDAOUtente daoUtente;
    private IDAOAcquisto daoAcquisto;
    private IDAOOrdine daoOrdine;
    private IDAOProdotto daoProdotto;

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public IDAOAcquisto getDaoAcquisto() {
        return daoAcquisto;
    }

    public IDAOOrdine getDaoOrdine() {
        return daoOrdine;
    }

    public IDAOProdotto getDaoProdotto() {
        return daoProdotto;
    }

    public static DAOFactory getInstance() {
        return singleton;
    }

    private DAOFactory() {
        if (Configurazione.getInstance().getStrategiaDb().equals(DB_HIBERNATE)) {
            throw new UnsupportedOperationException("Operazione non supportata");
        } else {
            daoAcquisto = new DAOAcquistoMock();
            daoOrdine = new DAOOrdineMock();
            daoProdotto = new DAOProdottoMock();
            daoUtente = new DAOUtenteMock();
        }
    }

}
