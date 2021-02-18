package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The class represent the sprite on the screen that holds the details of the game like the score of the player
 * and the level name.
 */
public class LevelDetails implements Sprite {

    // the place that all the details will be displayed there.
    private Rectangle rectangle;
    private Color color;

    /**
     * constructor.
     *
     * @param startPoint - the point that we want to display the sprite that holds the details.
     * @param width - the width of the sprite.
     * @param height - the height of the sprite.
     */
    public LevelDetails(Point startPoint, double width, double height) {
        this.color = Color.white;
        this.rectangle = new Rectangle(startPoint, width, height);
    }

    /**
     *
     * @return - returns a rectangle that represent the "block" that holds the details.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY() + 1,
                (int) this.rectangle.getWidth() - 1, (int) this.rectangle.getHeight() - 1);
        d.setColor(Color.BLACK);
    }

    @Override
    public void timePassed() {
    }
}
