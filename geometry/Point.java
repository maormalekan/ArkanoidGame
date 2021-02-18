package geometry;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import java.util.List;

/**
 * The class represent point.
 */
public class Point {

    private double x;
    private double y;


    private static final double EPSILON = Math.pow(10, -10);

    /**
     * constructor.
     *
     * @param x - x value of the point.
     * @param y - y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function calculate the distance of this point to the other point and return it.
     *
     * @param other - other point.
     * @return returns the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((Math.pow((other.getX() - this.x), 2) + Math.pow((other.getY() - this.y), 2)));
    }

    /**
     * the function checks if the points are equal.
     *
     * @param other - other point
     * @return returns true if the points are equal, otherwise returns false.
     */
    public boolean equals(Point other) {
        return (Math.abs(other.getX() - this.x) < EPSILON && Math.abs(other.getY() - this.y) < EPSILON);
    }

    /**
     * returns the x value of this point.
     *
     * @return returns the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the y value of this point.
     *
     * @return returns the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * the function updates the x value of this point.
     *
     * @param newX - new x value for this point.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * the function updates the y value of this point.
     *
     * @param newY - new y value for this point.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * the function gets a list of points and checks if there is equal point in the list.
     *
     * @param list -  list of points.
     * @return - returns yes if there is equal point in the list, otherwise returns false.
     */
    public boolean existsInList(List<Point> list) {
        for (Point p : list) {
            if (this.equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * the function returns a string representation of a point.
     *
     * @return - returns a string representation of a point.
     */
    public String toString() {
        return "x = " + this.x + ", y = " + this.y;
    }
}