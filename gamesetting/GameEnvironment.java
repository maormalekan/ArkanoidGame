package gamesetting;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import collision.CollisionInfo;
import interfaces.Collidable;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The class holds the collidables of the game and the sizes of the window.
 */
public class GameEnvironment {

    // the sizes of the window
    private int windowLeftBorder;
    private int windowRightBorder;
    private int windowTopBorder;
    private int windowBottomBorder;

    private List<Collidable> collidables;

    /**
     * constructor.
     *
     * @param leftB - the left edge of the window.
     * @param rightB - the right edge of the window.
     * @param topB - the top edge of the window.
     * @param bottomB - the bottom edge of the window.
     */
    public GameEnvironment(int leftB, int rightB, int topB, int bottomB) {
        this.collidables = new ArrayList<Collidable>();
        this.windowLeftBorder = leftB;
        this.windowRightBorder = rightB;
        this.windowTopBorder = topB;
        this.windowBottomBorder = bottomB;
    }

    /**
     * @return - returns the collideables of the game.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * returns the left edge of the window.
     *
     * @return returns the edge of the window.
     */
    public int getWindowLeftBorder() {
        return this.windowLeftBorder;
    }

    /**
     * returns the right edge of the window.
     *
     * @return returns the right edge of the window.
     */
    public int getWindowRightBorder() {
        return this.windowRightBorder;
    }

    /**
     * returns the top edge of the window.
     *
     * @return returns the top edge of the window.
     */
    public int getWindowTopBorder() {
        return this.windowTopBorder;
    }

    /**
     * returns the bottom edge of the window.
     *
     * @return returns the bottom edge of the window.
     */
    public int getWindowBottomBorder() {
        return this.windowBottomBorder;
    }

    /**
     * The function updates the edges of the window.
     *
     * @param leftB   - the left border that the ball can
     * @param rightB  - the right border that the ball can
     * @param topB    - the top border that the ball can
     * @param bottomB - the down border that the ball can
     */
    public void setWindowSizes(int leftB, int rightB, int topB, int bottomB) {
        this.windowLeftBorder = leftB;
        this.windowRightBorder = rightB;
        this.windowTopBorder = topB;
        this.windowBottomBorder = bottomB;
    }

    /**
     * the function adds a collideable to the collideable list.
     * @param c - collideable object.
     */
    public void addToCollidables(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The function removes the interfaces.Collidable from the list of the Collidables.
     *
     * @param c -the interfaces.Collidable that will be removed from the list of the Collidables.
     */
    public void removeFromCollidables(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - a trajectory of a object that moves.
     * @return - returns the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }
        CollisionInfo closestCollision = null;
        for (Collidable c : this.collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point p = trajectory.closestIntersectionToStartOfLine(rect);
            if (p != null) {
                if (closestCollision == null) {
                    closestCollision = new CollisionInfo(p, c);
                } else {
                    Point collisionPoint = closestCollision.collisionPoint();
                    if (p.distance(trajectory.start()) < collisionPoint.distance(trajectory.start())) {
                        closestCollision = new CollisionInfo(p, c);
                    }
                }
            }
        }
        return closestCollision;
    }
}