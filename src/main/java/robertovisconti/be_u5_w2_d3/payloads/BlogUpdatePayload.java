package robertovisconti.be_u5_w2_d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlogUpdatePayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
}
