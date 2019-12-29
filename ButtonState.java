/**
 * A ButtonState represents one of the four states of the button
 *
 * Pressed:  The button has just turned from false to true
 * Released: The button has just turned from true to false
 * HeldDown: The button was already true and stays true
 * None:     The button was already false and stays false
 */
public enum ButtonState {
    Pressed, Released, HeldDown, None;

    /**
     * Update a ButtonState based on an old state and a new value
     *
     * @param oldState the old button state
     * @param newState the new button value
     * @return the appropriate new ButtonState given the inputs
     */
    public static ButtonState update(ButtonState oldState, boolean newState) {
        return newState ?
                (oldState == Pressed || oldState == HeldDown) ? HeldDown : Pressed :
                (oldState == Released || oldState == None) ? None : Released;
    }
}
