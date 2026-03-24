package com.blog.blog_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.blog_api.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    //Search by title content or category
    @Query("SELECT DISTINCT p FROM Post p WHERE " +
        "LOWER(p.title)    LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(p.content)  LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(p.category) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Post> searchByTerm(@Param("term") String term);
}