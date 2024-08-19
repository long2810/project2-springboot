package com.example.Project2.dto;

import com.example.Project2.model.Article;
import com.example.Project2.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Long id;

    @Setter
    private String name;
    private final List<ArticleDto> articles = new ArrayList<>();

    public BoardDto(String name) {
        this.name = name;
    }

    public static BoardDto fromEntity(Board entity) {
        BoardDto dto = new BoardDto();
        dto.id = entity.getId();
        dto.name = entity.getName();

        for(Article article : entity.getArticles()) {
            dto.articles.add(ArticleDto.fromEntity(article));
        }
        return dto;
    }
}
