package sprites;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import geometry.Point;

/**
 * The class represent velocity. sprites.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx - the change in position on the `x` axe.
     * @param dy - the change in position on the `y` axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * returns the dx - the change in position on the `x` axe.
     *
     * @return returns the dx - the change in position on the `x` axe.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * returns the dy - the change in position on the `y` axe.
     *
     * @return returns the dy - the change in position on the `y` axe.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the function updates the dx of the velocity.
     *
     * @param newDx - new dx for the velocity.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * the function updates the dy of the velocity.
     *
     * @param newDy - new dx for the velocity.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * the function takes a point with position (x,y)
     * and returns a new point with new position according to this velocity.
     *
     * @param p - point that we want to promote.
     * @return returns the new point according to this velocity.
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point newPoint = new Point(newX, newY);
        return newPoint;
    }

    /**
     * the function convert angle and speed to new velocity.
     *
     * @param angle - angle of the object that move.
     * @param speed - speed of the object that move.
     * @return the function returns a new velocity according to the angle and the speed that it gets.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newRadAngle = Math.toRadians(angle);
        double dx = Math.sin(newRadAngle) * speed;
        double dy = Math.cos(Math.PI - newRadAngle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * the function calculate the current speed and returns it.
     *
     * @return - the current speed of the velocity.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}