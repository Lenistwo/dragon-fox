package pl.lenistwo.dragonfox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lenistwo.dragonfox.document.Post;
import pl.lenistwo.dragonfox.exceptions.PostNotFoundException;
import pl.lenistwo.dragonfox.repository.PostsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public Flux<Post> getAllPosts() {
        return Flux.
                fromIterable(postsRepository.findAll())
                .sort((post1, post2) -> Integer.compare(post2.getLikeCount(), post1.getLikeCount()));
    }

    @GetMapping("/{id}")
    public Mono<Post> getPostById(@PathVariable String id) {
        return Mono.
                just(postsRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody Post post){
        postsRepository.save(post);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updatePost(@RequestBody Post post) {
        postsRepository.save(post);
        return ResponseEntity.ok().build();
    }
}
