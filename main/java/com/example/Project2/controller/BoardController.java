package com.example.Project2.controller;

import com.example.Project2.dto.ArticleDto;
import com.example.Project2.dto.BoardDto;
import com.example.Project2.service.ArticleService;
import com.example.Project2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

@Controller
@RequestMapping("boards")
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

   @GetMapping
    public String listAllBoards(Model model) {
       model.addAttribute("boards",boardService.readAll());
       model.addAttribute("selected",null);
       List<ArticleDto> articleDtoList = articleService.realAll();
       Collections.reverse(articleDtoList);
       model.addAttribute("articles",articleDtoList);
       return "boards";
   }

   @GetMapping("{boardId}")
    public String listOneBoard(
            @PathVariable
            Long boardId,
            Model model
   ){
       BoardDto boardDto = boardService.read(boardId);
       model.addAttribute("boards",boardService.readAll());
       model.addAttribute("selected",boardDto);
       List<ArticleDto> articles = boardDto.getArticles();
       Collections.reverse(articles);
       model.addAttribute("articles",articles);
       return "boards";
   }
}
