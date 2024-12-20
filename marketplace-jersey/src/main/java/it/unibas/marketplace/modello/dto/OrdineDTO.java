package it.unibas.marketplace.modello.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdineDTO {
    
    private Long id;
    @NotEmpty
    private List<AcquistoDTO> acquisti = new ArrayList<>();
    @NotNull
    private LocalDate dataOrdine;
    private Double totaleOrdine;

}
