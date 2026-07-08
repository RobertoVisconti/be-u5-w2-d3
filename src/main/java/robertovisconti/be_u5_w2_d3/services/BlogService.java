package robertovisconti.be_u5_w2_d3.services;

import org.springframework.stereotype.Service;
import robertovisconti.be_u5_w2_d3.entities.Author;
import robertovisconti.be_u5_w2_d3.entities.Blog;
import robertovisconti.be_u5_w2_d3.exceptions.BadRequestException;
import robertovisconti.be_u5_w2_d3.payloads.BlogPayload;
import robertovisconti.be_u5_w2_d3.repositories.AuthorRepository;
import robertovisconti.be_u5_w2_d3.repositories.BlogRepository;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;

    public BlogService(BlogRepository blogRepository, AuthorRepository authorRepository) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
    }


    // SAVE
    public Blog save(BlogPayload payload) {
        Author author = authorRepository.findById(payload.getAuthorId())
                .orElseThrow(() -> new BadRequestException("Autore con ID " + payload.getAuthorId() + " non trovato!"));
        Blog newBlog = new Blog(payload.getTitolo(), payload.getCategoria(), payload.getContenuto(), payload.getTempoDiLettura());

        newBlog.setAuthor(author);

        Blog saveBlog = this.blogRepository.save(newBlog);
        
        return saveBlog;
    }
}
