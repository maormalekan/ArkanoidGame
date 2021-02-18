package interfaces;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;

/**
 * interface that represent a animation in the game.
 */
public interface Animation {

    /**
     * The function draw one frame of the animation.
     *
     * @param d - the surface that we draw on it the animation.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The function responsible to notify when the animation should stop.
     *
     * @return - return true if the animation should stop, otherwise returns false.
     */
    boolean shouldStop();
}