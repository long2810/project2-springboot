package com.example.Project2.service;

import com.example.Project2.dto.BoardDto;
import com.example.Project2.model.Board;
import com.example.Project2.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BoardService {
    private final BoardRepository repository;
    private static final String[] basicBoardNames = {
            "자유 게시판",
            "개발 게시판",
            "일상 게시판",
            "사건사고 게시판"
    };

    public BoardService (BoardRepository repository) {
        this.repository = repository;
        for (String boardName : basicBoardNames) {
            if (!this.repository.existsByName(boardName)) {
                Board board = new Board();
                board.setName(boardName);
                this.repository.save(board);
            }
        }
    }

    //READ
    public List<BoardDto> readAll() {
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : repository.findAll()) {
            boardDtos.add(BoardDto.fromEntity(board));
        }
        return boardDtos;
    }

    public BoardDto read(Long boardId) {
        return BoardDto.fromEntity(repository.findById(boardId).orElseThrow());
    }

}
