package org.skillfinder.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String locality;

    public User(String name, String email, String locality) {
        this.name =  name;
        this.email = email;
        this.locality = locality;
    }

    private List<String> imageIDs;

    private String profileImageID;

    private List<Skill> skills;

}
