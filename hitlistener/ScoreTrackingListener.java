package hitlistener;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;
import gamesetting.Counter;

/**
 * The class is responsible to update the counter of the blocks of the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter - the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The function adds 5 points when a block is removed.
     *
      * @param beingHit - The sprites.Block that being hit.
     * @param hitter - the sprites.Ball that hit the sprites.Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}