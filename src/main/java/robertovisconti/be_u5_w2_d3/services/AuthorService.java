package robertovisconti.be_u5_w2_d3.services;

import org.springframework.stereotype.Service;
import robertovisconti.be_u5_w2_d3.entities.Author;
import robertovisconti.be_u5_w2_d3.exceptions.BadRequestException;
import robertovisconti.be_u5_w2_d3.payloads.AuthorPayload;
import robertovisconti.be_u5_w2_d3.repositories.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(AuthorPayload payload) {
        if (this.authorRepository.existsByEmail(payload.getEmail()))
            throw new BadRequestException("L'indirizzo e-mail " + payload.getEmail() + " è già associata ad un account.");
        Author newAuthor = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getDateOfBirth());
        Author saveAuthor = this.authorRepository.save(newAuthor);
        return saveAuthor;
    }
}
