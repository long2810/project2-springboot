package com.example.Project2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String tag;

    @OneToMany(mappedBy = "hashTag")
    private List<ArticleHashtag> articleHashtag;

    public HashTag(Long id) {this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return Objects.equals(id, hashTag.id) && Objects.equals(tag, hashTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag);
    }
}
