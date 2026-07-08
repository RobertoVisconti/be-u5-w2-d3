package robertovisconti.be_u5_w2_d3.exceptions;

import java.util.UUID;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(UUID id) {
        super("Blog con ID " + id + " non trovato!");
    }
}
