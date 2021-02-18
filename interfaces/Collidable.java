package interfaces;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import sprites.Velocity;
import sprites.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * interface that represents collideable objects.
 */
public interface Collidable {
    /**
     * @return - Returns the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint - the point that the object collided this collideable.
     * @param currentVelocity - the current velocity of the object that collided this collideable.
     * @param hitter - the ball that hit the object.
     * @return - new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}