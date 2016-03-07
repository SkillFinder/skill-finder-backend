package org.skillfinder.models;

import lombok.Data;

@Data
public class Skill {

    private String name;
    private int level;

    public Skill(String name) {
        this.name = name;
    }
}
