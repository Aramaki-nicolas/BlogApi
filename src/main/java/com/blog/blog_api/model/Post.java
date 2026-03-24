package  com.blog.blog_api.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Post{
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable = false, columnDefinition ="TEXT")
    private String content;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(name ="post_tags",joinColumns = @JoinColumn(name="post_id"))
    @Column(name="tag")
    private List<String> tags;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //Constructors;

    public Post(){}
    public Post(String title,String content,String category, List<String> tags){
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

    //Lifecycle hooks;
    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    //Getter Setters

    public Long getId()                        { return id; }
    public String getTitle()                   { return title; }
    public void setTitle(String title)         { this.title = title; }
    public String getContent()                 { return content; }
    public void setContent(String content)     { this.content = content; }
    public String getCategory()                { return category; }
    public void setCategory(String category)   { this.category = category; }
    public List<String> getTags()              { return tags; }
    public void setTags(List<String> tags)     { this.tags = tags; }
    public LocalDateTime getCreatedAt()        { return createdAt; }
    public LocalDateTime getUpdatedAt()        { return updatedAt; }


}