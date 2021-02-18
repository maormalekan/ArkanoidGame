package levels;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Background;
import sprites.Block;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that represent the Direct Hit level.
 */
public class DirectHitLevel implements LevelInformation {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 35;
    private static final int BLOCK_HEIGHT = 35;

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Background background;
    private ArrayList<Block> blocks;
    private int numberOfBlocks;

    /**
     * constructor.
     */
    public DirectHitLevel() {
        this.numberOfBalls = 1;
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities.add(new Velocity(0, 10));
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.levelName = "Direct Hit";
        this.background = new Background(WINDOW_WIDTH, WINDOW_HEIGHT, Color.black);
        this.blocks = new ArrayList<Block>();
        this.blocks.add(new Block(
                new Rectangle(new Point((WINDOW_WIDTH / 2) - BLOCK_WIDTH / 2, 100), BLOCK_WIDTH, BLOCK_HEIGHT),
                Color.red, true));
        this.numberOfBlocks = 1;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocks;
    }
}
