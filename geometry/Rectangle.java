package geometry;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The class represent rectangle.
 */
public class Rectangle {

    private Point upperLeftPoint;
    private double height;
    private double width;

    /**
     * constructor - creates a new rectangle with location and width/height.
     *
     * @param upperLeft - th e upper left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * the function returns a (possibly empty) List of intersection points of this rectangle with other line.
     *
     * @param line - a line that we want to check intersections with this rectangle.
     * @return - list of intersections point of this rectangle with other line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        if (line.isIntersecting(this.getLeftEdge())) {
            intersectionPoints.add(line.intersectionWith(this.getLeftEdge()));
        }
        if (line.isIntersecting(this.getRightEdge())) {
            intersectionPoints.add(line.intersectionWith(this.getRightEdge()));

        }
        if (line.isIntersecting(this.getUpperEdge())) {
            Point p = line.intersectionWith(this.getUpperEdge());
            // if the line intersect the rectangle in a upper corner it may be in the list twice.
            if (!p.existsInList(intersectionPoints)) {
                intersectionPoints.add(p);
            }
        }
        if (line.isIntersecting(this.getLowerEdge())) {
            Point p = line.intersectionWith(this.getLowerEdge());
            // if the line intersect the rectangle in a bottom corner it may be in the list twice.
            if (!p.existsInList(intersectionPoints)) {
                intersectionPoints.add(p);
            }
        }
        return intersectionPoints;
    }


    /**
     * the function returns the width of the rectangle.
     *
     * @return - returns the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the function returns the height of the rectangle.
     *
     * @return - returns the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * the function returns the upper-left point of the rectangle.
     *
     * @return - returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }

    /**
     * the function returns the upper-right point of the rectangle.
     *
     * @return - returns the upper-left point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(upperLeftPoint.getX() + width, upperLeftPoint.getY());
    }

    /**
     * the function returns the lower-left point of the rectangle.
     *
     * @return - returns the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeftPoint.getX(), this.upperLeftPoint.getY() + this.height);
    }

    /**
     * the function returns the lower-right point of the rectangle.
     *
     * @return - returns the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(upperLeftPoint.getX() + width, upperLeftPoint.getY() + this.height);
    }

    /**
     * the function returns the upper edge of the rectangle.
     *
     * @return - returns the upper edge of the rectangle.
     */
    public Line getUpperEdge() {
        return new Line(this.upperLeftPoint, this.getUpperRight());
    }

    /**
     * the function returns the lower edge of the rectangle.
     *
     * @return - returns the lower edge of the rectangle.
     */
    public Line getLowerEdge() {
        return new Line(this.getLowerLeft(), this.getLowerRight());
    }

    /**
     * the function returns the left edge of the rectangle.
     *
     * @return - returns the left edge of the rectangle.
     */
    public Line getLeftEdge() {
        return new Line(this.upperLeftPoint, this.getLowerLeft());
    }

    /**
     * the function returns the right edge of the rectangle.
     *
     * @return - returns the right edge of the rectangle.
     */
    public Line getRightEdge() {
        return new Line(this.getUpperRight(), this.getLowerRight());
    }

    /**
     * the function updates the upper left point of the rectangle.
     *
     * @param p - the new upper left point.
     */
    public void setUpperLeftPoint(Point p) {
        this.upperLeftPoint = p;
    }

    /**
     * the function checks if the rectangles are equals.
     *
     * @param other - other rectangle.
     * @return - returns true if the rectangles are equals, otherwise returns false.
     */
    public boolean equals(Rectangle other) {
        if (other.getUpperLeft().equals(this.upperLeftPoint) && this.height == other.getHeight()
                && this.width == other.getWidth()) {
            return true;
        }
        return false;
    }
}