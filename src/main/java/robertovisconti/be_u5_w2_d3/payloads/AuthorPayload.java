package robertovisconti.be_u5_w2_d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AuthorPayload {

    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;

}
