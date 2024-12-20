package it.unibas.marketplace.modello.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdottoDTO {
    
    private Long id;
    private String descrizione;
    private String marca;
    private String categoria;
    private Double prezzo;

}
