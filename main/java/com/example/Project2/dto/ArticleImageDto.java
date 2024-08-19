package com.example.Project2.dto;


import com.example.Project2.model.ArticleImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ArticleImageDto {
    private Long id;
    private String link;

    public static ArticleImageDto fromEntity(ArticleImage entity) {
        ArticleImageDto dto = new ArticleImageDto();
        dto.id = entity.getId();
        dto.link = entity.getLink();
        return dto;
    }


}
