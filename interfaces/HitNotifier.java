package interfaces;
/**
 * @author: Maor Malekan <maorsdf@gmail.com>
 * @id: 321202962
 */

/**
 * Interface that represents hit notifiers.
 */
public interface HitNotifier {

    /**
     * The function adds object as a listener to hit events.
     *
     * @param hl - the hit listener that will be added to the listeners.
     */
    void addHitListener(HitListener hl);

    /**
     * The function removes hit listener from the list of listeners.
     *
     * @param hl - the hit listener that will be removes from the listeners.
     */
    void removeHitListener(HitListener hl);
}
