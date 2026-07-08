package robertovisconti.be_u5_w2_d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
public class AuthorResponsePayload {
    private UUID authorId;
}
