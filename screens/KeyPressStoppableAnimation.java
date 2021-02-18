package screens;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import interfaces.Animation;

/**
 * The class is represent a screen that wait for the user to press a specific key.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     *
     * @param sensor - the KeyboardSensor that we use in the game.
     * @param key - the specific key that we want the user will press.
     * @param animation - the animation that the class display until the user will press the key.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
