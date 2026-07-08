package robertovisconti.be_u5_w2_d3.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import robertovisconti.be_u5_w2_d3.entities.Author;
import robertovisconti.be_u5_w2_d3.entities.Blog;
import robertovisconti.be_u5_w2_d3.exceptions.BadRequestException;
import robertovisconti.be_u5_w2_d3.exceptions.BlogNotFoundException;
import robertovisconti.be_u5_w2_d3.payloads.BlogPayload;
import robertovisconti.be_u5_w2_d3.payloads.BlogUpdatePayload;
import robertovisconti.be_u5_w2_d3.repositories.AuthorRepository;
import robertovisconti.be_u5_w2_d3.repositories.BlogRepository;

import java.util.UUID;

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

    // PAGE
    public Page<Blog> getAllBlogs(int page, int size, String orderBy) {
        if (size > 50) size = 50;
        if (size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.blogRepository.findAll(pageable);
    }

    // BYID
    public Blog findById(UUID blogId) {
        return this.blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException(blogId));
    }

    // BY ID AND UPDATE
    public Blog findByIdAndUpdate(UUID blogId, BlogUpdatePayload payload) {

        Blog found = this.findById(blogId);

        found.setTitolo(payload.getTitolo());
        found.setCategoria(payload.getCategoria());
        found.setContenuto(payload.getContenuto());
        found.setTempoDiLettura(payload.getTempoDiLettura());

        Blog updateBlog = this.blogRepository.save(found);

        return updateBlog;
    }

    // DELETE
    public void findByIdAndDelete(UUID blogId) {
        Blog found = this.findById(blogId);
        this.blogRepository.delete(found);
    }
}
