package animation;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesetting.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * The class is responsible to display the given gameScreen, for numOfSeconds seconds,
 * and on top of them it will show a countdown from countFrom back to 1.
 */
public class CountdownAnimation implements Animation {

    private long milliSecondToSleep;
    private int countFrom;
    private SpriteCollection sprites;
    private boolean stop;
    private boolean firstCount;

    /**
     * constructor.
     *
     * @param numOfSeconds - the number of the seconds that the gameScreen will be displayed.
     * @param countFrom - the number that the animation count from back to 1.
     * @param gameScreen - the game screen that the animation displays.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.milliSecondToSleep = (long) (((numOfSeconds / (double) countFrom)) * 1000);
        this.countFrom = countFrom;
        this.sprites = gameScreen;
        this.stop = false;
        this.firstCount = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.countFrom >= 0) {
            Sleeper sleeper = new Sleeper();
            if (this.firstCount) {
                this.firstCount = false;
            } else {
                sleeper.sleepFor(this.milliSecondToSleep);
            }
            this.sprites.drawAllOn(d);
            d.setColor(Color.orange);
            d.drawText(400, 300, "" + this.countFrom, 100);
            this.countFrom--;
        } else {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

