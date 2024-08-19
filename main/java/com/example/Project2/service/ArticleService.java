package com.example.Project2.service;

import com.example.Project2.dto.ArticleDto;
import com.example.Project2.model.Article;
import com.example.Project2.model.Board;
import com.example.Project2.repo.ArticleRepository;
import com.example.Project2.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleDto create(Long boardId, ArticleDto dto) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Article article = new Article();
        article.setBoard(board);
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setPassword(dto.getPassword());
        article = articleRepository.save(article);

        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> realAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            articleDtos.add(ArticleDto.fromEntity(article));
        }
        return articleDtos;
    }

    public ArticleDto readArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return ArticleDto.fromEntity(article);
    }

    public ArticleDto update(Long id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id).orElseThrow();
        if (article.getPassword().equals(articleDto.getPassword())) {
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
        }
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id, String password) {
         Article article = articleRepository.findById(id).orElseThrow();
         if (article.getPassword().equals(password)) {
             articleRepository.delete(article);
         }
    }

    public Long getFront(Long boardId, Long articleId) {
        Optional<Article> target;
        if (boardId == 0L) {
            target = articleRepository.findFirstByIdAfter(articleId);
        }else {
            target = articleRepository.findFirstByBoardIdAndIdAfter(boardId,articleId);
        }
        return target.map(Article::getId).orElse(null);
    }
    public Long getBack(Long boardId, Long articleId) {
        Optional<Article> target;
        if (boardId == 0L) {
            target = articleRepository.findFirstByIdBeforeOrderByIdDesc(articleId);
        }else {
            target = articleRepository.findFirstByBoardIdAndIdBeforeOrderByIdDesc(boardId,articleId);
        }
        return target.map(Article :: getId).orElse(null);
    }
    public List<ArticleDto> search(Long boardId, String criteria,String query) {
        List<ArticleDto> results = new ArrayList<>();
        List<Article> articles;
        if (boardId == 0L) {
/*
            articles = criteria.equals("title")
*/

        }
        return results;
    }
}
