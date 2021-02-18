package collision;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import interfaces.Collidable;
import geometry.Point;

/**
 * The class holds the information of a collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     *
     * @param collisionPoint - the collision point.
     * @param collisionObject - the object that the object that we collided with in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the function return the point at which the collision occurs.
     *
     * @return - the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the function return the collidable object involved in the collision.
     *
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}