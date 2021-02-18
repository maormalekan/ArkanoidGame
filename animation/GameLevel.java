package animation;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.KeyboardSensor;
import gamesetting.Counter;
import gamesetting.GameEnvironment;
import gamesetting.SpriteCollection;
import interfaces.Animation;
import interfaces.LevelInformation;
import screens.KeyPressStoppableAnimation;
import screens.PauseScreen;
import sprites.Velocity;
import sprites.Paddle;
import sprites.LevelDetails;
import sprites.LevelName;
import sprites.Ball;
import sprites.Block;
import sprites.ScoreIndicator;
import hitlistener.BallRemover;
import hitlistener.BlockRemover;
import hitlistener.ScoreTrackingListener;
import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class is responsible to set the animation of the level.
 */
public class GameLevel implements Animation {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final double BORDER_SIZE = 25;
    private static final double BLOCK_DETAILS_HEIGHT = 17;

    private static final int BALL_RADIUS = 5;

    private SpriteCollection sprites;
    private GameEnvironment environment;

    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    private LevelInformation levelInformation;

    /**
     *
     * constructor.
     *
     * @param level - the information of the level.
     * @param ks - the KeyboardSensor that we use in the level.
     * @param ar - the animationruuner that runs the anumation of the level.
     * @param score - the starting score of the level ( we are keep the score when we end each level).
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(0, WINDOW_WIDTH, 0, WINDOW_HEIGHT);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = score;
        this.keyboard = ks;
        this.runner = ar;

        this.levelInformation = level;
    }

    /**
     *
     * @return - returns the Counter of the remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     *
     * @return - returns the Counter of the remaining balls.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     *
     * @return - returns the score you get in ths game.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * the function add a collideable object to the collidables list.
     *
     * @param c - collideable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addToCollidables(c);
    }

    /**
     * The function removes the interfaces.Collidable from the game.
     *
     * @param c - the interfaces.Collidable that will be removed from the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeFromCollidables(c);
    }

    /**
     * The function removes the interfaces.Sprite from the game.
     *
     * @param s - the interfaces.Sprite that will be removed from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeFromSprites(s);
    }

    /**
     * the function add a sprite to the sprites list.
     *
     * @param s - sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addToSprites(s);
    }

    /**
     * The function builds the block that are on the borders of the window and adds them to the game.
     */
    public void addBorders() {
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        Rectangle rectOfDeathRegion = new Rectangle(
                new Point(0, WINDOW_HEIGHT - BORDER_SIZE), WINDOW_WIDTH, BORDER_SIZE);
        Block deathRegion = new Block(rectOfDeathRegion, Color.lightGray, false);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        List<Rectangle> borders = new LinkedList<>();
        borders.add(new Rectangle(new Point(0, BLOCK_DETAILS_HEIGHT), WINDOW_WIDTH, BORDER_SIZE));
        borders.add(new Rectangle(new Point(0, BORDER_SIZE), BORDER_SIZE, WINDOW_HEIGHT - BORDER_SIZE));
        borders.add(new Rectangle(new Point(WINDOW_WIDTH - BORDER_SIZE, 0),
                BORDER_SIZE, WINDOW_HEIGHT - BORDER_SIZE));
        for (Rectangle rect : borders) {
            Block block = new Block(rect, Color.lightGray, false);
            block.addToGame(this);
        }
    }
    /**
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        this.addSprite(this.levelInformation.getBackground());
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        // adding the borders.
        this.addBorders();

        // adding the paddle to the game.
        Velocity velocityPaddle = new Velocity(this.levelInformation.paddleSpeed(), 0);
        Paddle paddle = new Paddle(this.runner.getGui(), this.environment, velocityPaddle,
                levelInformation.paddleWidth(), new Point(BORDER_SIZE, WINDOW_HEIGHT - BORDER_SIZE));
        paddle.addToGame(this);

        // adding the blocks to ths game.
        for (Block block: this.levelInformation.blocks()) {
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListener);
                block.addToGame(this);
        }
        this.remainingBlocks.increase(this.levelInformation.numberOfBlocksToRemove());

        // adding the balls to the game
        int radius = BALL_RADIUS;
        List<Velocity> ballVelocities = this.levelInformation.initialBallVelocities();
        Point paddleUpperLeft = paddle.getUpperLeftPint();
        int x = (int) paddleUpperLeft.getX();
        int y = (int) paddleUpperLeft.getY();
        for (Velocity velocity: ballVelocities) {
            Ball ball = new Ball(x + (paddle.getPaddleWidth() / 2), y - radius - 1, radius, Color.white);
            ball.setVelocity(velocity);
            ball.setGameEnvironment(this.environment);
            ball.movePointToBorders();
            ball.addToGame(this);
        }
        this.remainingBalls.increase(this.levelInformation.numberOfBalls());

        LevelDetails levelDetails = new LevelDetails(new Point(0, 0), WINDOW_WIDTH, BLOCK_DETAILS_HEIGHT);
        this.addSprite(levelDetails);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, levelDetails.getRectangle());
        this.addSprite(scoreIndicator);
        LevelName levelName = new LevelName(this.levelInformation.levelName(), levelDetails.getRectangle());
        this.addSprite(levelName);
    }

    /**
     * The function run the game - starts the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor(500);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen(this.keyboard)));
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.sprites.drawAllOn(d);
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

}