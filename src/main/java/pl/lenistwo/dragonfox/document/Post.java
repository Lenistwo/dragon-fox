package pl.lenistwo.dragonfox.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "collection.post")
public class Post {
    @Id
    private String id;
    private String title;
    private String author;
    private String image;
    private String content;
    private Integer likeCount;
}
