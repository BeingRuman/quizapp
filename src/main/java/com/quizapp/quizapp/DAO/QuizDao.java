package com.quizapp.quizapp.DAO;

import com.quizapp.quizapp.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
