package com.ridango.game.service;

import com.ridango.game.model.HighScore;
import com.ridango.game.repository.HighScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HighScoreService {

    @Autowired
    private HighScoreRepository highScoreRepository;

    public void handleSaveNewHighScore(Integer id, Integer newHighScore) {
        boolean doesHighScoreExist = highScoreRepository.existsById(id);
        if (doesHighScoreExist) {
            updateHighScore(newHighScore, id);
            return;
        }
        saveHighScore(newHighScore);
    }

    private void saveHighScore(Integer newHighScore) {
        HighScore highScore = new HighScore();
        highScore.setHighScore(newHighScore);
        highScoreRepository.save(highScore);
    }

    private void updateHighScore(Integer newHighScore, Integer id) {
        Optional<HighScore> highScoreOptional = highScoreRepository.findById(id);
        if (highScoreOptional.isPresent() && highScoreOptional.get().getHighScore() < newHighScore) {
            HighScore highScore = highScoreOptional.get();
            highScore.setHighScore(newHighScore);
            highScoreRepository.save(highScore);
        }
    }

    public HighScore getHighScoreById(Integer id) {
        return highScoreRepository.findById(id).orElse(new HighScore());
    }

}
