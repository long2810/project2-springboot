package com.example.Project2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Setter
    private String name;

    @Setter
    @OneToMany(mappedBy = "board")
    private List<Article> articles = new ArrayList<>();

   public Board(String name) {
       this.name = name;
   }

    @Override
    public String toString() {
        return "Board{" +
                "articles=" + articles +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(name, board.name) && Objects.equals(articles, board.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, articles);
    }
}
