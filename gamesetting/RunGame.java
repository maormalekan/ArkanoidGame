package gamesetting;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import animation.AnimationRunner;
import interfaces.LevelInformation;
import levels.LevelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible to build all the levels and run the whole game according to the levels that we want.
 */
public class RunGame {

    /**
     * The main function that we use to run the game.
     * The function builds all the levels and run the whole game according to the arguments that it gets.
     *
     * @param args - The arguments that the user gives. Each string represent a level that we want to run.
     *             if there is no argument, the function run the game by default order.
     */
    public static void play(String[] args) {
        // list of the levels that we want to run.
        List<LevelInformation> levelsToRun = new ArrayList<LevelInformation>();
        LevelFactory levelFactory = new LevelFactory();
        for (String str : args) {
            try {
                int l = Integer.parseInt(str);
                if (l > 0 && l <= levelFactory.getNumOfLevels()) {
                    levelsToRun.add(levelFactory.createLevel(l));
                }
            } catch (Exception e) {
                // do nothing.
                str = str;
            }
        }
        if (levelsToRun.isEmpty()) {
            for (int i = 1; i <= levelFactory.getNumOfLevels(); i++) {
                levelsToRun.add(levelFactory.createLevel(i));
            }
        }
        System.out.println(levelsToRun);
        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        gameFlow.runLevels(levelsToRun);
    }
}