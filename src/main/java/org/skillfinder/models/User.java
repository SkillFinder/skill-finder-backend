package org.skillfinder.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private Locality locality;

    public User() {

    }

    public User(String name, String email, Locality locality) {
        this.name =  name;
        this.email = email;
        this.locality = locality;
        this.imageIDs = new HashSet<>();
        this.skills = new ArrayList<>();
    }

    private Set<String> imageIDs;

    private String profileImageID;

    private List<Skill> skills;

}
