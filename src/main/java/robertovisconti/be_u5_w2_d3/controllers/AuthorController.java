package robertovisconti.be_u5_w2_d3.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_u5_w2_d3.entities.Author;
import robertovisconti.be_u5_w2_d3.payloads.AuthorPayload;
import robertovisconti.be_u5_w2_d3.payloads.AuthorResponsePayload;
import robertovisconti.be_u5_w2_d3.services.AuthorService;

import java.util.UUID;

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

    // 2 GET ALL AUTHORS
    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "surname") String ordeBy) {
        return this.authorService.getAllAuthors(page, size, ordeBy);
    }

    // 3 GET BY ID
    @GetMapping("/{authorId}")
    public Author getById(@PathVariable UUID authorId) {
        return this.authorService.findById(authorId);
    }

    // 4 PUT
    @PutMapping("/{authorId}")
    public Author getByIdAndUpdate(@PathVariable UUID authorId, @RequestBody AuthorPayload payload) {
        return this.authorService.findByIdAndUpdate(authorId, payload);
    }

    // 5 DELETE
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable UUID authorId) {
        this.authorService.findByIdAndDelete(authorId);
    }
}
