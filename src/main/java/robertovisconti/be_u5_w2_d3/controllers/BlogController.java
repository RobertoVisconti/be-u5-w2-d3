package robertovisconti.be_u5_w2_d3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robertovisconti.be_u5_w2_d3.entities.Blog;
import robertovisconti.be_u5_w2_d3.payloads.BlogPayload;
import robertovisconti.be_u5_w2_d3.services.BlogService;

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
}
