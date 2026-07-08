package robertovisconti.be_u5_w2_d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Setter
public class BlogPayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private UUID authorId;
}
