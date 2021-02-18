package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import gamesetting.Counter;
import interfaces.Sprite;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The class responsible to display the current score.
 */
public class ScoreIndicator implements Sprite {

    private Counter score;
    private Rectangle rectangle;

    /**
     * constructor.
     *
     * @param score - the current score of the game.
     * @param rectangle - the place that the score will be displayed on the screen.
     */
    public ScoreIndicator(Counter score, Rectangle rectangle) {
        this.score = score;
        this.rectangle = rectangle;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) (this.rectangle.getUpperLeft().getX() + 200), (int) (this.rectangle.getHeight() - 4),
                "Score: " + this.score.getValue(), 12);
    }

    @Override
    public void timePassed() {
    }
}
