package pl.lenistwo.dragonfox.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenistwo.dragonfox.document.Post;

import java.util.Collection;

@Repository
public interface PostsRepository extends MongoRepository<Post, String> {

    Collection<Post> findByAuthor(String author);
}
