package com.ridango.game.service;

import com.ridango.game.model.HighScore;
import com.ridango.game.repository.HighScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class HighScoreServiceTest {
    @Mock
    private HighScoreRepository highScoreRepository;

    @InjectMocks
    private HighScoreService highScoreService;


    @Test
    public void testHandleSaveNewHighScore_ScoreExistsAndUpdate() {
        Integer id = 1;
        Integer newHighScore = 200;
        HighScore existingHighScore = new HighScore();
        existingHighScore.setId(id);
        existingHighScore.setHighScore(150);
        when(highScoreRepository.existsById(id)).thenReturn(true);
        when(highScoreRepository.findById(id)).thenReturn(Optional.of(existingHighScore));

        highScoreService.handleSaveNewHighScore(id, newHighScore);

        verify(highScoreRepository, times(1)).save(existingHighScore);
        assertEquals(200, existingHighScore.getHighScore());
    }

    @Test
    public void testHandleSaveNewHighScore_ScoreDoesNotExist() {
        Integer id = 2;
        Integer newHighScore = 100;
        when(highScoreRepository.existsById(id)).thenReturn(false);

        highScoreService.handleSaveNewHighScore(id, newHighScore);

        verify(highScoreRepository, times(1)).save(any(HighScore.class));
    }

    @Test
    public void testGetHighScoreById_Found() {
        Integer id = 1;
        HighScore highScore = new HighScore();
        highScore.setId(id);
        highScore.setHighScore(100);
        when(highScoreRepository.findById(id)).thenReturn(Optional.of(highScore));

        HighScore result = highScoreService.getHighScoreById(id);

        assertNotNull(result);
        assertEquals(100, result.getHighScore());
    }

    @Test
    public void testGetHighScoreById_NotFound() {
        Integer id = 1;

        when(highScoreRepository.findById(id)).thenReturn(Optional.empty());

        HighScore result = highScoreService.getHighScoreById(id);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getHighScore());
    }
}
