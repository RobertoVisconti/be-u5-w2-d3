package robertovisconti.be_u5_w2_d3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("L'autore con ID: " + id + " non è stato trovato.");
    }
}
