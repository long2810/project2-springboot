package com.example.Project2.controller;

import com.example.Project2.dto.ArticleDto;
import com.example.Project2.model.Article;
import com.example.Project2.service.ArticleService;
//import com.example.Project2.service.BoardService;
import com.example.Project2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;

    @GetMapping("articles/new")
    public String newArticle(
            Model model
    ) {
        model.addAttribute("boards",boardService.readAll());
        return "articles/new";
    }


    @PostMapping("articles")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("board-id")
            Long boardId
    ) {
        ArticleDto dto = new ArticleDto(title,content,password);
        Long newId = articleService.create(boardId,dto).getId();
        return String.format("redirect:/articles/%d",newId);
    }

        // article/{id}
    @GetMapping("articles/{id}")
    public String readArticle(
            @PathVariable("id")
            Long id,
            @RequestParam(value = "board",defaultValue = "0")
            Long boardId,
            Model model
    ) {
        ArticleDto dto = articleService.readArticle(id);
        model.addAttribute("article",dto);
        model.addAttribute("board",boardId);
        model.addAttribute("before",articleService.getBack(id,boardId));
        model.addAttribute("after",articleService.getBack(id,boardId));
        return "articles/read";
    }
}
