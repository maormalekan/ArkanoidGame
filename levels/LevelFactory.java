package levels;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import interfaces.LevelInformation;

/**
 * The class is responsible to create levels according to the index of the level.
 */
public class LevelFactory {

    private int numOfLevels;

    /**
     * constructor.
     */
    public LevelFactory() {
        this.numOfLevels = 4;
    }

    /**
     *
     * @return - returns the number of the levels that exists.
     */
    public int getNumOfLevels() {
        return this.numOfLevels;
    }

    /**
     * The function creates level according to the index that it gets.
     * @param l - the index of the level.
     * @return - returns the level that have been created.
     */
    public LevelInformation createLevel(int l) {
        if (l == 1) {
            return (new DirectHitLevel());
        } else if (l == 2) {
            return (new WideEasyLevel());
        } else if (l == 3) {
            return (new Green3Level());
        } else if (l == 4) {
            return (new FinalFourLevel());
        } else {
            return null;
        }
    }
}
