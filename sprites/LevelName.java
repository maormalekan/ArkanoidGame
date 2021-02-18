package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The class represent the sprite that displays the name of the level on the screen.
 */
public class LevelName implements Sprite {

    private String levelName;
    private Rectangle rectangle;

    /**
     * constructor.
     *
     * @param name - the name of the level.
     * @param rectangle - the place that the name will be displayed on the screen.
     */
    public LevelName(String name, Rectangle rectangle) {
        this.levelName = name;
        this.rectangle = rectangle;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) (this.rectangle.getUpperRight().getX() - 250), (int) (this.rectangle.getHeight() - 4),
                "Level Name: " + this.levelName, 12);
    }

    @Override
    public void timePassed() {
    }
}
