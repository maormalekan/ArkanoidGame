package hitlistener;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import gamesetting.Counter;
import animation.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The class responsible to remove blocks from the game, as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game           - our game.
     * @param remainingBlocks - the counter that count the remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The function removes the sprites.Block that the sprites.Ball hits.
     *
     * @param beingHit - The sprites.Block that being hit.
     * @param hitter   - The sprites.Ball that hit the sprites.Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}