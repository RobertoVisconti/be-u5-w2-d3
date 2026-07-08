package robertovisconti.be_u5_w2_d3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_u5_w2_d3.entities.Author;
import robertovisconti.be_u5_w2_d3.payloads.AuthorPayload;
import robertovisconti.be_u5_w2_d3.payloads.AuthorResponsePayload;
import robertovisconti.be_u5_w2_d3.services.AuthorService;

@RestController
@RequestMapping("/Authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // 1 POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponsePayload saveAuthor(@RequestBody AuthorPayload payload) {
        Author saved = this.authorService.save(payload);
        return new AuthorResponsePayload(saved.getId());
    }
}
