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
 * The class that represent the Wide Easy level.
 */
public class WideEasyLevel implements LevelInformation {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final double BORDER_SIZE = 25;

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
    public WideEasyLevel() {
        this.numberOfBalls = 10;
        this.initialBallVelocities = new ArrayList<Velocity>();
        double angleBetweenBalls = 180 / (this.numberOfBalls + 1);
        double currentAngle = 270 + angleBetweenBalls;
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(currentAngle, 7));
            currentAngle += angleBetweenBalls;
        }
        this.paddleSpeed = 6;
        this.paddleWidth = 450;
        this.levelName = "Wide Easy";
        this.background = new Background(WINDOW_WIDTH, WINDOW_HEIGHT, Color.white);
        this.numberOfBlocks = 15;
        this.blocks = new ArrayList<Block>();
        Color[] colors = {Color.red, Color.ORANGE, Color.yellow, Color.green, Color.blue, Color.pink, Color.CYAN};
        double blockWidth = (WINDOW_WIDTH - BORDER_SIZE * 2) / numberOfBlocks;
        for (int i = 0; i < this.numberOfBlocks; i++) {
                Rectangle rectangle = new Rectangle(
                        new Point(BORDER_SIZE + i * blockWidth - 1, WINDOW_HEIGHT / 3),
                        blockWidth, BLOCK_HEIGHT);
                Block block = new Block(rectangle, colors[(i - 1) / 2], true);
                this.blocks.add(block);
        }
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
