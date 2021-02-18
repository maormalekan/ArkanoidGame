package interfaces;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import sprites.Block;
import sprites.Velocity;
import java.util.List;

/**
 * Interface that represents each level with it's information.
 */
public interface LevelInformation {

    /**
     *
     * @return - returns the number of the balls in the level.
     */
    int numberOfBalls();

    /**
     *
     * @return - returns list of the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return - returns the speed of the paddle.
     */
    int paddleSpeed();

    /**
     *
     * @return - returns the width of the paddle.
     */
    int paddleWidth();

    /**
     *
     * @return - returns the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     *
     * @return - returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     *
     * @return - returns the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     *
     * @return - returns the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
