package it.unibas.codicefiscalerest.modello;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {

    private Long id;
    @NotBlank
    @Size(min = 3)
    private String nome;
    @NotBlank
    @Size(min = 3)
    private String cognome;
    @Size(min = 1, max = 1)
    private String sesso;
    @PastOrPresent
    private LocalDate dataNascita;
    @NotBlank
    @Size(min = 4, max = 4)
    private String codiceComune;

    public Persona(String nome, String cognome, String sesso, LocalDate data, String codiceComune) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.dataNascita = data;
        this.codiceComune = codiceComune;
    }

    @JsonIgnore
    public String getCodiceFiscale() {
        if (this.nome.length() < 3) {
            throw new IllegalArgumentException("Il nome deve essere almeno di 3 caratteri");
        }
        if (this.cognome.length() < 3) {
            throw new IllegalArgumentException("Il cognome deve essere almeno di 3 caratteri");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.nome.substring(0, 3));
        sb.append(this.cognome.substring(0, 3));
        if (this.sesso.equalsIgnoreCase("M")) {
            sb.append(this.dataNascita.getDayOfMonth());
        } else {
            sb.append(this.dataNascita.getDayOfMonth() + 40);
        }
        sb.append(this.dataNascita.getYear());
        sb.append(this.codiceComune);

        return sb.toString().toUpperCase();
    }

}
