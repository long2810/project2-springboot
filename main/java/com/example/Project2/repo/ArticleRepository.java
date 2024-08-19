package com.example.Project2.repo;

import com.example.Project2.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findFirstByIdAfter(Long Id);
    Optional<Article> findFirstByIdBeforeOrderByIdDesc(Long id);

    Optional<Article> findFirstByBoardIdAndIdAfter(Long boardId,Long id);
    Optional<Article> findFirstByBoardIdAndIdBeforeOrderByIdDesc(Long boardId, Long id);

    
}
