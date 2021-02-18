package animation;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * A class that is responsible to run the animation that it gets.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    /**
     * constructor.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", WINDOW_WIDTH, WINDOW_HEIGHT);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     *
     * @return - returns the Gui of the runner.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * The function gets a animation, and run it on it's Gui.
     *
     * @param animation - the animation that the function will run on the Gui.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long startTime = System.currentTimeMillis(); // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}