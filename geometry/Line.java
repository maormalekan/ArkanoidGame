package geometry;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import sprites.Velocity;

import java.util.List;

/**
 * The class represent a line.
 */
public class Line {

    private static final double EPSILON = Math.pow(10, -10);
    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start - the start of the line.
     * @param end   - the end of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 - x value of the start of the line.
     * @param y1 - y value of the start of the line.
     * @param x2 - x value of the end of the line.
     * @param y2 - y value of the end of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * returns the length of the line.
     *
     * @return returns the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * returns the middle of the line.
     *
     * @return returns the middle of the line.
     */
    public Point middle() {
        double newX = (start.getX() + end.getX()) / 2;
        double newY = (start.getY() + end.getY()) / 2;
        Point p = new Point(newX, newY);
        return p;
    }

    /**
     * returns the start point of the line.
     *
     * @return returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the end point of the line.
     *
     * @return returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * returns the higher point of the line.
     *
     * @return returns the higher point of the line.
     */
    private Point topOfLine() {
        if (this.start.getY() <= this.end.getY()) {
            return this.start;
        }
        return this.end;
    }

    /**
     * returns the lower point of the line.
     *
     * @return returns the lower point of the line.
     */
    private Point bottomOfLine() {
        if (this.start.getY() >= this.end.getY()) {
            return this.start;
        }
        return this.end;
    }

    /**
     * returns the left point of the line.
     *
     * @return returns the left point of the line.
     */
    private Point leftPointOfLine() {
        if (this.start.getX() <= this.end.getX()) {
            return this.start;
        }
        return this.end;
    }

    /**
     * returns the right point of the line.
     *
     * @return returns the right point of the line
     */
    private Point rightPointOfLine() {
        if (this.start.getX() >= this.end.getX()) {
            return this.start;
        }
        return this.end;
    }

    /**
     * the function gets point and checks if the point is on the line.
     *
     * @param p - point that the function check if it's on the line.
     * @return returns true if the point is on the line, otherwise returns false.
     */
    public boolean pointOnLine(Point p) {
        // if the line is vertical
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
            if (Math.abs(p.getX() - this.start.getX()) < EPSILON && p.getY() >= this.topOfLine().getY()
                    && p.getY() <= this.bottomOfLine().getY()) {
                return true;
            }
            return false;
        }
        if (Math.abs(this.start.getY() - this.end.getY()) < EPSILON) {
            if (Math.abs(p.getY() - this.start.getY()) < EPSILON && p.getX() >= this.leftPointOfLine().getX()
                    && p.getX() <= this.rightPointOfLine().getX()) {
                return true;
            }
            return false;
        }
        Equation equ = new Equation(this);
        double y = equ.valueOfEquation(p.getX());
        return (y >= this.topOfLine().getY() && y <= this.bottomOfLine().getY()
                && p.getX() >= this.leftPointOfLine().getX() && p.getX() <= this.rightPointOfLine().getX());
    }

    /**
     * the function gets two lines (this line and another line) withe same slope
     * and checks if they intersect each other.
     *
     * @param other - another line in addition to this line.
     * @return returns true if the lines intersect each other, otherwise returns false.
     */
    private boolean checkIntersectingForLinesWithSameSlope(Line other) {
        /*
        when both lines are have same slope they intersect each other
        only if top of one line is equal to the bottom of the other line.
         */
        return (this.topOfLine().equals(other.bottomOfLine()) || this.bottomOfLine().equals(other.topOfLine()));
    }

    /**
     * the function gets two lines (this line and another line) that one of them is vertical ans the other not,
     * and checks if they intersect each other.
     *
     * @param vertical - vertical line in addition to this line that is not vertical.
     * @return returns true if the lines intersect each other, otherwise returns false.
     */
    private boolean verticalLineIntersectWithOtherLine(Line vertical) {
        Equation equ = new Equation(this);
        double y = equ.valueOfEquation(vertical.start().getX());
        Point intersectPoint = new Point(vertical.start().getX(), y);
        // if the intersect point is on the both lines. it means that the lines intersect each other.
        return (this.pointOnLine(intersectPoint) && vertical.pointOnLine(intersectPoint));
    }

    /**
     * the function gets two lines and checks if they intersect each other.
     *
     * @param other - another line in addition to this line.
     * @return returns true if the lines intersect each other, otherwise returns false.
     */
    public boolean isIntersecting(Line other) {
        // if the two lines are vertical.
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON
                && Math.abs(other.start().getX() - other.end().getX()) < EPSILON) {
            return this.checkIntersectingForLinesWithSameSlope(other);
        }
        // if this line is vertical and the other is not.
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
            return other.verticalLineIntersectWithOtherLine(this);
        }
        // if this line is not vertical and the other is.
        if (Math.abs(other.start().getX() - other.end().getX()) < EPSILON) {
            return this.verticalLineIntersectWithOtherLine(other);
        }
        // if the lines are not vertical.
        Equation equ1 = new Equation(this.start, this.end);
        Equation equ2 = new Equation(other);
        if (equ1.equals(equ2)) {
            return this.checkIntersectingForLinesWithSameSlope(other);
        }
        if (!equ1.checkIntersectingForEquations(equ2)) {
            return false;
        }
        Point intersectionPoint = equ1.intersectionPoint(equ2);
        return (this.pointOnLine(intersectionPoint) && other.pointOnLine(intersectionPoint));
    }

    /**
     * the function gets two lines with same slope and returns the intersection point.
     * the function assume that the lines are intersect (it has been tested in another function).
     *
     * @param other - another line in addition to this line.
     * @return returns the intersection point.
     */
    private Point intersectionPointLinesWithSameSLope(Line other) {
        if (this.topOfLine().equals(other.bottomOfLine())) {
            return this.topOfLine();
        }
        return this.bottomOfLine();
    }

    /**
     * the function get vertical line and other line (not vertical) and returns the intersection point.
     * the function assume that the lines are intersect (it has been tested in another function).
     *
     * @param vertical - vertical line in addition to this line that is not vertical.
     * @return returns the intersection point.
     */
    private Point intersectionOfVerticalLineAndOther(Line vertical) {
        Equation equ = new Equation(this);
        double y = equ.valueOfEquation(vertical.start().getX());
        Point intersectPoint = new Point(vertical.start().getX(), y);
        return intersectPoint;
    }

    /**
     * the function get two lines and if they intersect it returns the intersection point.
     * if they don't intersect it returns null.
     *
     * @param other - another line in addition to this line.
     * @return if the lines are intersect the function returns the intersection point, otherwise it returns null.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // if both lines are vertical
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON
                && Math.abs(other.start().getX() - other.end().getX()) < EPSILON) {
            return this.intersectionPointLinesWithSameSLope(other);
        }
        // if this line is vertical and the other is not.
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
            return other.intersectionOfVerticalLineAndOther(this);
        }
        // if this line is not vertical and the other is.
        if (Math.abs(other.start().getX() - other.end().getX()) < EPSILON) {
            return this.intersectionOfVerticalLineAndOther(other);
        }
        Equation equ1 = new Equation(this.start, this.end);
        Equation equ2 = new Equation(other);
        if (equ1.equals(equ2)) {
            return this.intersectionPointLinesWithSameSLope(other);
        }
        return equ1.intersectionPoint(equ2);
    }

    /**
     * the function get two lines and check if they equals.
     *
     * @param other - another line in addition to this line.
     * @return if the lines are equals the function returns true, otherwise it returns false.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start())) && (this.end.equals(other.end()));
    }

    /**
     * the function gets rectangle and check if the this line intersect with it.
     * if this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of this line.
     *
     * @param rect - rectangle that we check a collision with it.
     * @return - returns the closet collision point if there is a collision, otherwise return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closest = intersectionPoints.get(0);
        double closestDis = this.start.distance(closest);
        int size = intersectionPoints.size();
        for (int i = 1; i < size; i++) {
            double distance = this.start.distance(intersectionPoints.get(i));
            if (distance < closestDis) {
                closestDis = distance;
                closest = intersectionPoints.get(i);
            }
        }
        return closest;
    }

    /**
     * the function gets start point of a object and the velocity of the object,
     * and returns the line trajectory oj the object.
     *
     * @param startPoint - start point of the object.
     * @param velocity - the velocity of the object.
     * @return - returns the line trajectory oj the object according to the start point and the velocity.
     */
    public static Line createTrajectory(Point startPoint, Velocity velocity) {
        Point endPoint = new Point(startPoint.getX() + velocity.getDx(), startPoint.getY() + velocity.getDy());
        return new Line(startPoint, endPoint);
    }
}