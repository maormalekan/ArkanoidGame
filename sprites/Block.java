package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import animation.GameLevel;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represent block that the ball can collide with it.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private boolean drawOutline;

    /**
     * constructor.
     *
     * @param rect - the rectangle of the block.
     * @param col  - the color of the block.
     * @param drawOutline - a boolean parameter that indicates if we want to draw outline for the block or not.
     */
    public Block(Rectangle rect, Color col, boolean drawOutline) {
        this.color = col;
        this.rectangle = rect;
        this.hitListeners = new LinkedList<HitListener>();
        this.drawOutline = drawOutline;
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * The function returns the list of the listeners of the sprites.Block.
     * @return - returns the list of the listeners of the sprites.Block.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * the function draws the block on the surface.
     *
     * @param surface - the surface that we draw on it the objects.
     */
    public void drawOn(DrawSurface surface) {
        if (drawOutline) {
            surface.setColor(Color.BLACK);
            surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() + 1,
                    (int) this.rectangle.getUpperLeft().getY() + 1,
                    (int) this.rectangle.getWidth() - 1, (int) this.rectangle.getHeight() - 1);
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }
    }

    /**
     * the function change the place of the block according to the pint it gets.
     *
     * @param upperLeft - the new place of the block.
     */
    public void setPlace(Point upperLeft) {
        this.rectangle.setUpperLeftPoint(upperLeft);
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  - the point that the object collided on the block.
     * @param currentVelocity - the current velocity of the object that collided this block.
     * @param hitter - the ball that hit the sprites.Block.
     * @return - new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        if ((((currentVelocity.getDx() > 0) && this.rectangle.getLeftEdge().pointOnLine(collisionPoint)))
                || (((currentVelocity.getDx() < 0) && this.rectangle.getRightEdge().pointOnLine(collisionPoint)))) {
            newDx = currentVelocity.getDx() * -1;
        }
        if ((((currentVelocity.getDy() > 0) && this.rectangle.getUpperEdge().pointOnLine(collisionPoint)))
                || (((currentVelocity.getDy() < 0) && this.rectangle.getLowerEdge().pointOnLine(collisionPoint)))) {
            newDy = currentVelocity.getDy() * -1;
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * the function notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * The function adds the object to the game.
     * It means that the block will be add to the sprites list of the game, and the collidables list of the game.
     *
     * @param g -The game that the object will be added to it.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * The function removes the object from the game.
     *
     * @param game - the game that the block is part of it.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function will be called whenever a hit occurs,
     * and will notify all of the registered interfaces.HitListener objects by calling their hitEvent method.
     *
     * @param hitter - the sprites.Ball that hit the sprites.Block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}