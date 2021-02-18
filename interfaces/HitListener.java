package interfaces;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import sprites.Ball;
import sprites.Block;

/**
 * Interface that represents hit listeners.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.Ball that's doing the hitting.
     *
     * @param beingHit - The sprites.Block that being hit.
     * @param hitter - the sprites.Ball that hit the sprites.Block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
