package screens;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesetting.Counter;
import interfaces.Animation;

import java.awt.Color;

/**
 * The class is responsible to display the end screen of the game.
 * The screen will be displayed when we win or when we lose.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean win;
    private Counter score;

    /**
     * constructor.
     *
     * @param k - the KeyboardSensor that we use in the game.
     * @param winOrLose - a boolean parameter that indicates if we won or lost,
     *                  and according this we display the appropriate screen.
     * @param score - our score in the game.
     */
    public EndScreen(KeyboardSensor k, boolean winOrLose, Counter score) {
        this.keyboard = k;
        this.win = winOrLose;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (win) {
            d.drawText(160, d.getHeight() / 2, "You Win!. Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(160, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}