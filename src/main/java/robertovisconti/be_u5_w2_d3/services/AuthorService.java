package robertovisconti.be_u5_w2_d3.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import robertovisconti.be_u5_w2_d3.entities.Author;
import robertovisconti.be_u5_w2_d3.exceptions.BadRequestException;
import robertovisconti.be_u5_w2_d3.exceptions.NotFoundException;
import robertovisconti.be_u5_w2_d3.payloads.AuthorPayload;
import robertovisconti.be_u5_w2_d3.repositories.AuthorRepository;

import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    // SAVE
    public Author save(AuthorPayload payload) {
        if (this.authorRepository.existsByEmail(payload.getEmail()))
            throw new BadRequestException("L'indirizzo e-mail " + payload.getEmail() + " è già associata ad un account.");
        Author newAuthor = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getDateOfBirth());
        Author saveAuthor = this.authorRepository.save(newAuthor);
        return saveAuthor;
    }


    // PAGE
    public Page<Author> getAllAuthors(int page, int size, String orderBy) {
        if (size > 50) size = 50;
        if (size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.authorRepository.findAll(pageable);

    }

    // BYID
    public Author findById(UUID authorId) {
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    // BY ID AND UPDATE
    public Author findByIdAndUpdate(UUID authorId, AuthorPayload payload) {
        Author found = this.findById(authorId);

        if (!found.getEmail().equals(payload.getEmail()))
            if (this.authorRepository.existsByEmail(payload.getEmail()))
                throw new BadRequestException("L'indirizzo e-mail " + payload.getEmail() + " è giù utilizzata.");
        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setDateOfBirth(payload.getDateOfBirth());

        Author updateAuthor = this.authorRepository.save(found);

        return updateAuthor;
    }

    // DELETE
    public void findByIdAndDelete(UUID authorId) {
        Author found = this.findById(authorId);
        this.authorRepository.delete(found);
    }
}
