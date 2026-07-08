package robertovisconti.be_u5_w2_d3.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_u5_w2_d3.entities.Blog;
import robertovisconti.be_u5_w2_d3.payloads.BlogPayload;
import robertovisconti.be_u5_w2_d3.payloads.BlogUpdatePayload;
import robertovisconti.be_u5_w2_d3.services.BlogService;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    // 1 POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog saveBlog(@RequestBody BlogPayload payload) {
        return this.blogService.save(payload);
    }

    // 2 GET ALL BLOGS
    @GetMapping
    public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "titolo") String orderBy) { // Ordinamento di default per titolo
        return this.blogService.getAllBlogs(page, size, orderBy);
    }

    // 3 GET BY ID
    @GetMapping("/{blogId}")
    public Blog getById(@PathVariable UUID blogId) {
        return this.blogService.findById(blogId);
    }

    // 4 PUT (Aggiornamento pulito senza cambiare autore)
    @PutMapping("/{blogId}")
    public Blog getByIdAndUpdate(@PathVariable UUID blogId, @RequestBody BlogUpdatePayload payload) {
        return this.blogService.findByIdAndUpdate(blogId, payload);
    }

    // 5 DELETE
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable UUID blogId) {
        this.blogService.findByIdAndDelete(blogId);
    }
}
