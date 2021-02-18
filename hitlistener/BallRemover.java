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
 * The class responsible to remove balls, and update an availabe-balls counter.
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           - our game.
     * @param remainingBalls - the counter that count the remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The function removes the sprites.Ball when it hit the "death region"(the bottom sprites.Block in the screen).
     *
     * @param beingHit - the bottom sprites.Block in the screen.
     * @param hitter   - the sprites.Ball that should be removed.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
