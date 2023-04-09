package org.ed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song {

    private Long id;
    private String name;
    private String cover;
    private Integer year;
    private Double duration;
    private String url;
    private Gender gender;

    public Song() {}

    public boolean compareId(Long id) {
        return this.id.compareTo(id) == 0;
    }
}
