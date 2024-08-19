package com.example.Project2.service;

import com.example.Project2.dto.CommentDto;
import com.example.Project2.model.Article;
import com.example.Project2.model.Comment;
import com.example.Project2.repo.ArticleRepository;
import com.example.Project2.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElseThrow();

        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setContent(dto.getContent());
        comment.setPassword(dto.getPassword());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    public void deleteComment(Long id, String password) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        if (comment.getPassword().equals(password)) {
            commentRepository.delete(comment);
        }
    }
}
