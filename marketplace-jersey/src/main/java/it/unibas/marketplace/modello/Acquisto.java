package it.unibas.marketplace.modello;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Acquisto {
    
    private Long id;
    private int quantita;
    private Prodotto prodotto;

    public Acquisto(int quantita, Prodotto prodotto) {
        this.quantita = quantita;
        this.prodotto = prodotto;
    }
    

}
