package it.unibas.marketplace.modello;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ordine {

    private Long id;
    private List<Acquisto> acquisti = new ArrayList<>();
    private LocalDate dataOrdine;

    public Ordine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
    
    public double calcolaTotaleOrdine() {
        double totale = 0;
        for (Acquisto acquisto : acquisti) {
            totale += acquisto.getProdotto().getPrezzo() * acquisto.getQuantita();          
        }
        return totale;
    }
 
}
