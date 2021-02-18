package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The class is represent the background of the level.
 */
public class Background implements Sprite {

    private int width;
    private int height;
    private Color color;

    /**
     * constructor.
     *
     * @param width - the width of the background.
     * @param height - the height of the background.
     * @param color - the color of the background.
     */
    public Background(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, this.width, this.height);
    }

    @Override
    public void timePassed() {
    }
}
