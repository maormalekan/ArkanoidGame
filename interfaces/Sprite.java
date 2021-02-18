package interfaces;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;

/**
 * interface that represents collideable objects.
 */
public interface Sprite {


    /**
     * draw the sprite to the screen.
     *
     * @param d - the surface that we draw on it the sprite.
     */
    void drawOn(DrawSurface d);


    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}