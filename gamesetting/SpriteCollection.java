package gamesetting;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

import interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represent the sprites thar are in the game.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * constructor - initialize the list of the sprites in the game.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * the function gets a sprite and adds it to the list of the sprites in the game.
     *
     * @param s - a sprite that we wants add the game.
     */
    public void addToSprites(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The function removes the interfaces.Sprite from the list of the sprites of the game.
     *
     * @param s - the interfaces.Sprite that will be removed from the list of the sprites.
     */
    public void removeFromSprites(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all the sprites in the game.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copyOfSprites = new LinkedList<Sprite>(this.sprites);
        for (Sprite s : copyOfSprites) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all the sprites in the game.
     *
     * @param d - the surface that we draw on it the sprite.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}