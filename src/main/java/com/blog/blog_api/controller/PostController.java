
package com.blog.blog_api.controller;
 
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_api.model.Post;
import com.blog.blog_api.service.PostService;

@RestController
@RequestMapping("/posts")

public class  PostController{

    private final PostService service;

    public PostController(PostService service){
        this.service=service;
    }
    //POST
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Post post){
        if (post.getTitle() == null || post.getTitle().isBlank() ||
            post.getContent() == null || post.getContent().isBlank() ||
            post.getCategory() == null || post.getCategory().isBlank()) {
 
            return ResponseEntity.badRequest().body(Map.of(
                "error", "title, content and category are required"
            ));
        }    
    Post created = service.create(post);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    //GET
    @GetMapping
    public ResponseEntity<List<Post>> getALL(
        @RequestParam(required=false) String term){
            return ResponseEntity.ok(service.getAll(term));
        }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();//204
    }
}