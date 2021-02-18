package screens;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

import java.awt.Color;

 /**
  * The class is responsible to display the pause screen of the game.
  * The screen will be displayed when the user press the "p" key.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;

    /**
     * constructor.
     *
     * @param k - the KeyboardSensor that we use in the game.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(160, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
