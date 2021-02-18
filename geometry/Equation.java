package geometry;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 *
 */

/**
 * This class represent the equation of a line.
 */
public class Equation {

    private double slope;
    private double freeVar;

    private static final double EPSILON = Math.pow(10, -10);

    /**
     * constructor.
     *
     * @param p1 - point of the edge of a line.
     * @param p2 - point of the other edge of the line.
     */
    public Equation(Point p1, Point p2) {
        if (!(Math.abs(p2.getX() - p1.getX()) < EPSILON)) {
            this.slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            this.freeVar = ((this.slope) * (-1) * p1.getX()) + p1.getY();
        }
    }

    /**
     * constructor.
     *
     * @param line - the line that we want to build for equation.
     */
    public Equation(Line line) {
        this(line.start(), line.end());
    }

    /**
     * The function checks if the equation intersect with this other equation.
     *
     * @param other - the other equation.
     * @return true if the equation are intersecting, otherwise return false.
     */
    public boolean checkIntersectingForEquations(Equation other) {
        // if the both equations have the same slope they equals or that they won't intersect.
        if ((Math.abs(this.slope - other.getSlope()) < EPSILON)
                && !(Math.abs(this.freeVar - other.getFreeVar()) < EPSILON)) {
            return false;
        }
        return true;
    }

    /**
     * The function returns the intersection point between the lines, if they don't intersect it return null.
     *
     * @param other - the other equation.
     * @return the intersection point between the lines, if they don't intersect it return null.
     */
    public Point intersectionPoint(Equation other) {
        if (!this.checkIntersectingForEquations(other)) {
            return null;
        }
        double newX = (this.freeVar - other.getFreeVar()) / (other.getSlope() - this.slope);
        double newY = this.slope * newX + this.freeVar;
        Point p = new Point(newX, newY);
        return p;
    }

    /**
     * returns the free variable of the equation.
     *
     * @return returns the free variable of the equation.
     */
    public double getFreeVar() {
        return this.freeVar;
    }

    /**
     * returns the free slope of the equation.
     *
     * @return returns the free slope of the equation.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * The function gets other equation and check if they equals.
     *
     * @param other - other equation.
     * @return true if the equations are equal. otherwise return false.
     */
    public boolean equals(Equation other) {
        return (Math.abs(this.freeVar - other.getFreeVar()) < EPSILON)
                && (Math.abs(this.slope - other.getSlope()) < EPSILON);
    }

    /**
     * The function gets a value of x and return the value of y according the equation.
     *
     * @param x - value of x.
     * @return value of y according the equation.
     */
    public double valueOfEquation(double x) {
        return (x * this.slope) + this.freeVar;
    }

}