package com.example.Project2.repo;

import com.example.Project2.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByName(String name);
}
