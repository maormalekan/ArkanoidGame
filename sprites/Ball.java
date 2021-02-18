package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import collision.CollisionInfo;
import gamesetting.GameEnvironment;
import animation.GameLevel;
import interfaces.Sprite;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;

import java.awt.Color;
import java.util.Random;

/**
 * The class represents ball.
 */
public class Ball implements Sprite {

    private java.awt.Color color;
    private int radius;
    // the center of the ball
    private Point point;
    // the velocity that the ball bounce
    private Velocity velocity;

    // the maximum speed that the ball can get.
    private static final int MAX_SPEED = 100;
    // the distance that the ball arrive from a block before collision.
    private static final double EPSILON = Math.pow(10, -10);

    private GameEnvironment gameEnv;

    /**
     * constructor.
     *
     * @param point - the center of the ball.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(Point point, int r, java.awt.Color color) {
        this.point = point;
        this.radius = r;
        this.color = color;
        // default velocity
        this.velocity = new Velocity(5, 5);
    }

    /**
     * constructor.
     *
     * @param x     - the x value ot the center's ball.
     * @param y     - the y value ot the center's ball.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        Point p = new Point(x, y);
        this.point = p;
        this.radius = r;
        this.color = color;
        // default velocity
        this.velocity = new Velocity(5, 5);
    }

    /**
     * constructor.
     *
     * @param x     - the x value ot the center's ball.
     * @param y     - the y value ot the center's ball.
     * @param r     - the radius of the ball.
     * @param v     - the velocity of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, int r, Velocity v, java.awt.Color color) {
        Point p = new Point(x, y);
        this.point = p;
        this.radius = r;
        this.color = color;
        this.velocity = v;
    }

    /**
     * returns the x value of the center's ball.
     *
     * @return returns the x value of the center's ball.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * returns the y value of the center's ball.
     *
     * @return returns the y value of the center's ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * returns the size (radius) of the ball.
     *
     * @return returns the size (radius) of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * returns the color of the ball.
     *
     * @return returns the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * returns the velocity of the ball.
     *
     * @return returns the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * returns the point of the center of the ball.
     *
     * @return returns the point of the center of the ball.
     */
    public Point getCenter() {
        return this.point;
    }

    /**
     * the function update the game environment of the ball.
     *
     * @param newEnvironment - the new environment of the game.
     */
    public void setGameEnvironment(GameEnvironment newEnvironment) {
        this.gameEnv = newEnvironment;
    }

    /**
     * The function draws the ball on the surface.
     *
     * @param surface - the surface that the ball will be draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.point.getX(), (int) this.point.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.point.getX(), (int) this.point.getY(), this.radius);
    }

    /**
     * The function update the velocity of the ball.
     *
     * @param v - the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * The function update the velocity of the ball.
     *
     * @param dx - the velocity of the ball in x axe.
     * @param dy - the velocity of the ball in y axe.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * The function update the point of the ball.
     *
     * @param p - the point of the ball.
     */
    public void setPoint(Point p) {
        this.point = p;
    }


    /**
     * The function cares that the ball will not cross the window.
     * If the ball is going to cross the border of the window, the direction of the ball will be change.
     */
    private void checkBorders() {
        /*
        if the ball move left and going to cross the left border or
        that the ball move right and going to cross the right border, the direction of it's movement will change.
        */
        if (((this.velocity.getDx() < 0)
                && (this.point.getX() - this.radius + this.velocity.getDx() < this.gameEnv.getWindowLeftBorder()))
                || ((this.velocity.getDx() > 0)
                && (this.point.getX() + this.radius + this.velocity.getDx() > this.gameEnv.getWindowRightBorder()))) {
            double newDx = this.velocity.getDx() * -1;
            this.setVelocity(newDx, this.velocity.getDy());
        }
        // if the ball move up and going to cross the top border or
        // that the ball move down and going to cross the down border, the direction of it's movement will change.
        if (((this.velocity.getDy() < 0)
                && (this.point.getY() - this.radius + this.velocity.getDy() < this.gameEnv.getWindowTopBorder()))
                || ((this.velocity.getDy() > 0)
                && (this.point.getY() + this.radius + this.velocity.getDy() > this.gameEnv.getWindowBottomBorder()))) {
            double newDy = this.velocity.getDy() * -1;
            this.setVelocity(this.velocity.getDx(), newDy);
        }
    }

    /**
     * The function moves the ball one step according to the velocity of the ball,
     * and responsible to handle collisions with blocks or with the paddle.
     */
    public void moveOneStep() {
        Line trajectory = Line.createTrajectory(this.point, this.velocity);
        CollisionInfo collision = this.gameEnv.getClosestCollision(trajectory);
        if (collision == null) {
            this.checkBorders();
            this.point = this.velocity.applyToPoint(this.point);
        } else {
            // after collision we move the ball to "almost" the hit point, but just slightly before it.
            double newX;
            double newY;
            if (this.velocity.getDy() < 0) {
                newY = collision.collisionPoint().getY() + EPSILON;
            } else {
                newY = collision.collisionPoint().getY() - EPSILON;
            }
            if (this.velocity.getDx() > 0) {
                newX = collision.collisionPoint().getX() - EPSILON;
            } else {
                newX = collision.collisionPoint().getX() + EPSILON;
            }
            this.point = new Point(newX, newY);
            this.velocity = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);
        }
    }

    /**
     * The function check if the ball is the ball that we crate is in the window,
     * and if it not the function moves the ball to the borders.
     */
    public void movePointToBorders() {
        double newX = this.point.getX();
        double newY = this.point.getY();
        if (this.point.getX() < this.gameEnv.getWindowLeftBorder() + this.radius) {
            newX = this.gameEnv.getWindowLeftBorder() + this.radius;
        }
        if (this.point.getX() > this.gameEnv.getWindowRightBorder() - this.radius) {
            newX = this.gameEnv.getWindowRightBorder() - this.radius;
        }
        if (this.point.getY() < this.gameEnv.getWindowTopBorder() + this.radius) {
            newY = this.gameEnv.getWindowTopBorder() + this.radius;
        }
        if (this.point.getY() > this.gameEnv.getWindowBottomBorder() - this.radius) {
            newY = this.gameEnv.getWindowBottomBorder() - this.radius;
        }
        Point newP = new Point(newX, newY);
        this.point = newP;
    }

    /**
     * The function creates new random ball in the frame that it get.
     * The velocity of the frame will be according to the radius and the height of the frame.
     *
     * @param r            - the radius of the ball.
     * @param startOfFrame - the top right corner of the frame.
     * @param frameWidth   - the width of the frame.
     * @param frameHeight  - the height of the frame.
     * @return the function return new random ball that is in the frame.
     */
    public static Ball buildRandomBallInFrame(int r, Point startOfFrame, int frameWidth, int frameHeight) {
        Random rand = new Random();
        // random point in the frame.
        int x = rand.nextInt(frameWidth) + (int) startOfFrame.getX();
        int y = rand.nextInt(frameHeight) + (int) startOfFrame.getY();
        // the speed of the ball that is
        double speed = frameHeight / (double) r;
        // random angle
        double angle = rand.nextInt(360);
        // the maximum speed is 100
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
        Ball ball = new Ball(x, y, r, velocity, Color.black);
        ball.gameEnv.setWindowSizes((int) startOfFrame.getX(), (int) startOfFrame.getX() + frameWidth,
                (int) startOfFrame.getY(), (int) startOfFrame.getY() + frameHeight);
        ball.movePointToBorders();
        return ball;
    }

    /**
     * the function notify the sprite that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * The function adds the object to the game. It means that the ball will be add to the sprites list.
     *
     * @param g - the game that we want add the object to it.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function removes the object from the game.
     *
     * @param game - the game where the ball is.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}