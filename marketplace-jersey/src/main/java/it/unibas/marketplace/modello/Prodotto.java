package it.unibas.marketplace.modello;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Prodotto {
    
    private Long id;
    private String descrizione;
    private String marca;
    private String categoria;
    private double prezzo;

    public Prodotto(String descrizione, String marca, String categoria, double prezzo) {
        this.descrizione = descrizione;
        this.marca = marca;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }
    
    

}
