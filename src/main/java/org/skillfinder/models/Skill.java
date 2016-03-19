package org.skillfinder.models;

import lombok.Data;
import org.skillfinder.enums.Level;

@Data
public class Skill {

    private String name;
    private Level level;

    public Skill(String name, Level level) {
        this.name = name;
        this.level = level;
    }
}
