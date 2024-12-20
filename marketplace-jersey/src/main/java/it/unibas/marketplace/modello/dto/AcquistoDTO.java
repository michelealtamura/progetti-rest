package it.unibas.marketplace.modello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcquistoDTO {
    
    private Long id;
    @Min(0)
    private int quantita;
    @NotNull
    private ProdottoDTO prodotto;

}
