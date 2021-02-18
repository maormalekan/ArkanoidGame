package gamesetting;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.KeyboardSensor;
import animation.AnimationRunner;
import animation.GameLevel;
import interfaces.LevelInformation;
import screens.EndScreen;
import screens.KeyPressStoppableAnimation;

import java.util.List;

/**
 * The class is responsible to run all the levels in flow and save the score after each level.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    /**
     * constructor.
     *
     * @param ar - the animation runner of the game.
     * @param ks - the KeyboardSensor that we use in the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * The function is responsible to run all the levels in flow and save the score after each level.
     *
     * @param levels - the levels that we want to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        Counter score = new Counter();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score);
            level.initialize();
            level.run();
            if (level.getRemainingBalls().getValue() == 0) {
                win = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreen(this.keyboardSensor, win, score)));
        this.animationRunner.getGui().close();
    }
}
