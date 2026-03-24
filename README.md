# 📝 Blog API

A simple RESTful API for a personal blogging platform built with Spring Boot. Supports full CRUD operations and search filtering.



---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (in-memory, for development)

---

## 📁 Project Structure

```
src/main/java/com/blog/blogapi/
├── model/
│   └── Post.java                   # Data model / entity
├── repository/
│   └── PostRepository.java         # Database access
├── service/
│   └── PostService.java            # Business logic
├── controller/
│   └── PostController.java         # REST endpoints
├── exception/
│   └── GlobalExceptionHandler.java # Error handling
└── BlogApiApplication.java         # Entry point

src/main/resources/
└── application.properties          # Configuration
```

---

## 🚀 Getting Started

**1. Clone the repository**
```bash
git clone https://github.com/your-username/blog-api.git
cd blog-api
```

**2. Run the application**
```bash
./mvnw spring-boot:run
```

**3. API is available at**
```
http://localhost:8080
```

**4. H2 Database console (browser)**
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:blogdb
Username: sa
Password: (leave empty)
```

---

## 📖 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/posts` | Create a new post |
| `GET` | `/posts` | Get all posts |
| `GET` | `/posts?term=java` | Search posts by term |
| `GET` | `/posts/{id}` | Get a single post |
| `PUT` | `/posts/{id}` | Update a post |
| `DELETE` | `/posts/{id}` | Delete a post |

---

## 💡 Examples

**Create a post**
```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Post",
    "content": "This is the content of my first post.",
    "category": "Technology",
    "tags": ["Java", "Spring Boot"]
  }'
```

Response `201 Created`:
```json
{
  "id": 1,
  "title": "My First Post",
  "content": "This is the content of my first post.",
  "category": "Technology",
  "tags": ["Java", "Spring Boot"],
  "createdAt": "2026-03-20T12:00:00",
  "updatedAt": "2026-03-20T12:00:00"
}
```

---

**Get all posts**
```bash
curl http://localhost:8080/posts
```

---

**Search posts by term**
```bash
curl http://localhost:8080/posts?term=java
```

Searches across `title`, `content` and `category` fields (case insensitive).

---

**Get a single post**
```bash
curl http://localhost:8080/posts/1
```

---

**Update a post**
```bash
curl -X PUT http://localhost:8080/posts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My Updated Post",
    "content": "This is the updated content.",
    "category": "Technology",
    "tags": ["Java"]
  }'
```

---

**Delete a post**
```bash
curl -X DELETE http://localhost:8080/posts/1
```

Response: `204 No Content`

---

##  Post Structure

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Auto-generated unique identifier |
| `title` | String | Title of the post (required) |
| `content` | String | Content of the post (required) |
| `category` | String | Category of the post (required) |
| `tags` | List\<String\> | List of tags |
| `createdAt` | LocalDateTime | Set automatically on creation |
| `updatedAt` | LocalDateTime | Updated automatically on every update |

---

## Error Handling

| Status Code | Meaning |
|-------------|---------|
| `201 Created` | Post created successfully |
| `200 OK` | Request successful |
| `204 No Content` | Post deleted successfully |
| `400 Bad Request` | Missing or invalid fields |
| `404 Not Found` | Post not found |

Error response example:
```json
{
  "error": "Post not found with id: 99",
  "status": 404,
  "timestamp": "2026-03-20T12:00:00"
}
```

---

## Configuration

For production, never hardcode credentials. Use environment variables instead:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```
