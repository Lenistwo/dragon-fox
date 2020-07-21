package pl.lenistwo.dragonfox.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "collection.user")
public class User {
    @Id
    private ObjectId id;
    @Indexed(name = "username_index", unique = true)
    private String username;
    private String password;
    private Role role;
}
