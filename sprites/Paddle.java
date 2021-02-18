package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import collision.CollisionInfo;
import gamesetting.GameEnvironment;
import animation.GameLevel;
import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.List;

/**
 * The class represent a paddle in the game that move.
 */
public class Paddle implements Sprite, Collidable {

    private KeyboardSensor keyboard;
    private Block paddle;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private int paddleWidth;

    private static final int HEIGHT_PADDLE = 6;
    private static final Color COLOR_PADDLE = Color.ORANGE;

    private static final int NUM_OF_REGIONS = 5;

    /**
     * constructor.
     *
     * @param gui - the gui that we use to crate a keyboard.
     * @param environment - the environment of the game.
     * @param velocityPaddle - the velocity of the paddle.
     * @param paddleWidth - the width of the paddle.
     * @param startPoint - the starting point of the paddle.
     */
    public Paddle(GUI gui, GameEnvironment environment, Velocity velocityPaddle, int paddleWidth, Point startPoint) {
        this.keyboard = gui.getKeyboardSensor();
        this.paddleWidth = paddleWidth;
        startPoint.setY(startPoint.getY() - HEIGHT_PADDLE);
        Rectangle rectOfBlock = new Rectangle(startPoint, paddleWidth, HEIGHT_PADDLE);
        this.paddle = new Block(rectOfBlock, COLOR_PADDLE, true);
        this.velocity = velocityPaddle;
        this.gameEnvironment = environment;
    }

    /**
     *
     * @return - returns the width of the paddle
     */
    public int getPaddleWidth() {
        return this.paddleWidth;
    }

    /**
     *
     * @return - returns the height of the paddle;
     */
    public int getHeightPaddle() {
        return HEIGHT_PADDLE;
    }

    /**
     *
     * @return - returns the upper legt point of ths paddle.
     */
    public Point getUpperLeftPint() {
        return this.paddle.getCollisionRectangle().getUpperLeft();
    }

    /**
     * The function check if the paddle will collide with a block when it moves left.
     * the function assume that the paddle will not collide with other block and it's top or bottom edge not.
     *
     * @return - if the paddle collide with a block the function returns the collision point. otherwise it returns null.
     */
    public Point collisionWithBlockFromLeft() {
        List<Collidable> collidables = this.gameEnvironment.getCollidables();
        if (collidables.isEmpty()) {
            return null;
        }
        CollisionInfo collision = null;
        Rectangle rectOfPaddle = this.paddle.getCollisionRectangle();
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            if (!rect.equals(rectOfPaddle)) {
                // trajectory of the top edge (check if the top edge will collide with other block)
                Line upperTrajectory = Line.createTrajectory(rectOfPaddle.getUpperLeft(), this.velocity);
                // trajectory of the bottom edge (check if the bottom edge will collide with other block)
                Line bottomTrajectory = Line.createTrajectory(rectOfPaddle.getLowerLeft(), this.velocity);
                Point upperCollisionPoint = upperTrajectory.closestIntersectionToStartOfLine(rect);
                Point bottomCollisionPoint = bottomTrajectory.closestIntersectionToStartOfLine(rect);
                if (upperCollisionPoint != null) {
                    return upperCollisionPoint;
                }
                if (bottomCollisionPoint != null) {
                    return bottomCollisionPoint;
                }
            }
        }
        return null;
    }

    /**
     * The function check if the paddle will collide with a block when it moves right.
     * the function assume that the paddle will not collide with other block and it's top or bottom edge not.
     *
     * @return - if the paddle collide with a block the function returns the collision point. otherwise it returns null.
     */
    public Point collisionWithBlockFromRight() {
        List<Collidable> collidables = this.gameEnvironment.getCollidables();
        if (collidables.isEmpty()) {
            return null;
        }
        CollisionInfo collision = null;
        Rectangle rectOfPaddle = this.paddle.getCollisionRectangle();
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            if (!rect.equals(rectOfPaddle)) {
                // trajectory of the top edge (check if the top edge will collide with other block)
                Line upperTrajectory = Line.createTrajectory(rectOfPaddle.getUpperRight(), this.velocity);
                // trajectory of the bottom edge (check if the bottom edge will collide with other block)
                Line bottomTrajectory = Line.createTrajectory(rectOfPaddle.getLowerRight(), this.velocity);
                Point upperCollisionPoint = upperTrajectory.closestIntersectionToStartOfLine(rect);
                Point bottomCollisionPoint = bottomTrajectory.closestIntersectionToStartOfLine(rect);
                if (upperCollisionPoint != null) {
                    return upperCollisionPoint;
                }
                if (bottomCollisionPoint != null) {
                    return bottomCollisionPoint;
                }
            }
        }
        return null;
    }

    /**
     * the function moves the paddle to left.
     */
    public void moveLeft() {
        if (this.velocity.getDx() > 0) {
            this.velocity.setDx(this.velocity.getDx() * -1);
        }
        Rectangle thisRect = this.paddle.getCollisionRectangle();
        Point collisionPoint = this.collisionWithBlockFromLeft();
        // check if the paddle collides with other block and cares that the paddle will not cross the block.
        if (collisionPoint != null && (collisionPoint.getX() <= thisRect.getUpperLeft().getX())) {
            Point newUpperLeftPoint = new Point(collisionPoint.getX(), collisionPoint.getY());
            this.paddle.setPlace(newUpperLeftPoint);
        } else {
            this.paddle.setPlace(this.velocity.applyToPoint(thisRect.getUpperLeft()));
        }
    }

    /**
     * the function moves the paddle to right.
     */
    public void moveRight() {
        if (this.velocity.getDx() < 0) {
            this.velocity.setDx(this.velocity.getDx() * -1);
        }
        Rectangle thisRect = this.paddle.getCollisionRectangle();
        Point collisionPoint = this.collisionWithBlockFromRight();
        // check if the paddle collides with other block and cares that the paddle will not cross the block.
        if (collisionPoint != null && collisionPoint.getX() >= thisRect.getUpperRight().getX()) {
            Point newUpperLeftPoint = new Point(collisionPoint.getX() - thisRect.getWidth(),
                    thisRect.getUpperLeft().getY());
            this.paddle.setPlace(newUpperLeftPoint);
        } else {
            this.paddle.setPlace(this.velocity.applyToPoint(thisRect.getUpperLeft()));
        }
    }

    /**
     * the function notify the sprite that time has passed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    /**
     * the function draws the paddle on the surface.
     *
     * @param surface - the surface that we draw on it the objects.
     */
    public void drawOn(DrawSurface surface) {
        this.paddle.drawOn(surface);
    }

    /**
     * the function returns the rectangle of the paddle.
     *
     * @return - the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit.
     * each region of the paddle returns a different velocity (different angle but same speed).
     *
     * @param collisionPoint - the collision point with the paddle
     * @param currentVelocity - the current velocity of the object that collided the paddle.
     * @param hitter - the ball that hit the sprites.Paddle.
     * @return - new velocity according the collision point on the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.paddle.getCollisionRectangle().getUpperEdge().pointOnLine(collisionPoint)) {
            double distance = this.paddleWidth / NUM_OF_REGIONS;
            double xOfUpperLeft = this.paddle.getCollisionRectangle().getUpperLeft().getX();
            double speed = currentVelocity.getSpeed();
            for (int i = 0; i < NUM_OF_REGIONS; i++) {
                if (collisionPoint.getX() >= xOfUpperLeft + (i * distance)
                        && collisionPoint.getX() < xOfUpperLeft + ((i + 1) * distance)) {
                    return Velocity.fromAngleAndSpeed(300 + (i * 30), speed);
                    // return changeVelocityAfterHit(currentVelocity, 300 + (i * 30));
                }
            }
            // if the collision is on the top of the line and the function didn't end,
            // it means that the collision is on the right upper corner
            return Velocity.fromAngleAndSpeed(420, speed);
        }
        // it means that the ball doesn't hit the paddle on top edge.
        return this.paddle.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * The function adds the object to the game.
     * It means that the paddle will be add to the sprites list and the collidables list.
     *
     * @param g - the game that we want to add the paddle into it.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}