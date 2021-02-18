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
 * The class that represent the Final Four level.
 */
public class FinalFourLevel implements LevelInformation {

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
    public FinalFourLevel() {
        this.numberOfBalls = 3;
        this.initialBallVelocities = new ArrayList<Velocity>();
        double angleBetweenBalls = 180 / (this.numberOfBalls + 1);
        double currentAngle = 270 + angleBetweenBalls;
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(currentAngle, 8));
            currentAngle += angleBetweenBalls;
        }
        this.paddleSpeed = 10;
        this.paddleWidth = 120;
        this.levelName = "Final Four";
        this.background = new Background(WINDOW_WIDTH, WINDOW_HEIGHT, new Color(0, 150, 250));
        int numOfRows = 7;
        int numOfBlocksEachRow = 15;
        this.numberOfBlocks = numOfRows * numOfBlocksEachRow;
        double blockWidth = (WINDOW_WIDTH - BORDER_SIZE * 2) / numOfBlocksEachRow;
        this.blocks = new ArrayList<Block>();
        Color[] colors = {Color.darkGray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.CYAN};
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfBlocksEachRow; j++) {
                Rectangle rectangle = new Rectangle(
                        new Point(BORDER_SIZE + (blockWidth * j) - 1,
                                100 + (BLOCK_HEIGHT * i)),
                        blockWidth, BLOCK_HEIGHT);
                Block block = new Block(rectangle, colors[i], true);
                this.blocks.add(block);
            }
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
