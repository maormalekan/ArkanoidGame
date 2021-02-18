package gamesetting;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

/**
 * A class that responsible to counting things.
 */
public class Counter {

    private int count;

    /**
     * constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * The function adds number to current count.
     *
     * @param number - the number that will be added to the current count.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * The function subtract number from current count.
     *
     * @param number - the number that will be subtracted from the current count.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * The function returns the current count.
     *
     * @return - the current count.
     */
    public int getValue() {
        return this.count;
    }
}