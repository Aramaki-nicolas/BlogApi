package com.blog.blog_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blog_api.model.Post;
import com.blog.blog_api.repository.PostRepository;

@Service
public class PostService{
    private final PostRepository repository;
    
    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    //CRUD
    //Create

    public Post create(Post post){
        return repository.save(post);
    }

    //Read

    public List<Post> getAll(String term){
        if(term!=null && !term.isBlank()){
            return repository.searchByTerm(term);
        }
        return repository.findAll();
    }
    public Post getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    //Update
    public Post update(Long id, Post updatedPost) {
        Post existing = getById(id); // lança 404 se não existir
 
        existing.setTitle(updatedPost.getTitle());
        existing.setContent(updatedPost.getContent());
        existing.setCategory(updatedPost.getCategory());
        existing.setTags(updatedPost.getTags());
 
        return repository.save(existing);
    }
    //Delete

    public void delete(Long id) {
        getById(id); // lança 404 se não existir
        repository.deleteById(id);
    }
}    