package com.quizapp.quizapp.service;

import com.quizapp.quizapp.DAO.QuestionDao;
import com.quizapp.quizapp.DAO.QuizDao;
import com.quizapp.quizapp.entities.Question;
import com.quizapp.quizapp.entities.QuestionWrapper;
import com.quizapp.quizapp.entities.Quiz;
import com.quizapp.quizapp.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

         Quiz quiz = new Quiz();
         quiz.setTitle(title);
         quiz.setQuestions(questions);
         quizDao.save(quiz);

      return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
         Optional<Quiz> quiz= quizDao.findById(id);
         List<Question> questionsFromDB = quiz.get().getQuestions();
         List<QuestionWrapper> questionsForUser = new ArrayList<>();


         for(Question q: questionsFromDB){
             QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
             questionsForUser.add(qw);


         }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = new getQuestions();

        for (Response response: responses){
            if(response.getResponse().equls(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK)
    }
}
