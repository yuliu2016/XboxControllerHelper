import edu.wpi.first.wpilibj.DriverStation;

import static ButtonState.*;

/**
 * Handle input from Xbox 360 or Xbox One controllers connected to the Driver Station.
 *
 * This class handles Xbox input that comes from the Driver Station. There is a single
 * class instance for each controller.
 *
 * The state of the controller is updated through the collectControllerData method
 */
public class XboxControllerHelper {

    public ButtonState aButton          = None;
    public ButtonState bButton          = None;
    public ButtonState xButton          = None;
    public ButtonState yButton          = None;

    public ButtonState leftBumper       = None;
    public ButtonState rightBumper      = None;

    public ButtonState leftStickButton  = None;
    public ButtonState rightStickButton = None;

    public ButtonState backButton       = None;
    public ButtonState startButton      = None;

    public double leftTrigger           = 0.0;
    public double rightTrigger          = 0.0;

    public double leftX                 = 0.0;
    public double leftY                 = 0.0;

    public double rightX                = 0.0;
    public double rightY                = 0.0;

    private int port;
    private DriverStation ds = DriverStation.getInstance();
    private boolean unplugReported = false;

    /**
     * Create a new controller helper
     *
     * @param port the port on the Driver Station
     */
    public XboxControllerHelper(int port) {
        this.port = port;
    }

    /**
     * Update the controller data. This should be done once in each periodic function
     * of the robot. If the controller is not found, this method reports a warning
     * to the Driver Station once, then never again until reset is called
     */
    public void collectControllerData() {

        int buttonCount = ds.getStickButtonCount(port);
        int axisCount = ds.getStickAxisCount(port);

        if (buttonCount >= 10 && axisCount > 5) {
            int buttons      = ds.getStickButtons(port);

            aButton          = update(aButton,         (buttons & 1     ) != 0);
            bButton          = update(bButton,         (buttons & 1 << 1) != 0);
            xButton          = update(xButton,         (buttons & 1 << 2) != 0);
            yButton          = update(yButton,         (buttons & 1 << 3) != 0);

            leftBumper       = update(leftBumper,      (buttons & 1 << 4) != 0);
            rightBumper      = update(rightBumper,     (buttons & 1 << 5) != 0);

            backButton       = update(backButton,      (buttons & 1 << 6) != 0);
            startButton      = update(startButton,     (buttons & 1 << 7) != 0);

            leftStickButton  = update(leftStickButton, (buttons & 1 << 8) != 0);
            rightStickButton = update(rightStickButton,(buttons & 1 << 9) != 0);

            leftX            = ds.getStickAxis(port, 0);
            rightX           = ds.getStickAxis(port, 1);

            leftTrigger      = ds.getStickAxis(port, 2);
            rightTrigger     = ds.getStickAxis(port, 3);

            leftY            = ds.getStickAxis(port, 4);
            rightY           = ds.getStickAxis(port, 5);

        } else {
            if (!unplugReported) {
                unplugReported = true;
                DriverStation.reportWarning("The controller on port " + port +
                        "is not plugged in", false);
            }
        }
    }

    /**
     * Reset all the controller data
     */
    public void reset() {
        aButton          = None;
        bButton          = None;
        xButton          = None;
        yButton          = None;

        leftBumper       = None;
        rightBumper      = None;

        leftStickButton  = None;
        rightStickButton = None;

        backButton       = None;
        startButton      = None;

        leftTrigger      = 0.0;
        rightTrigger     = 0.0;

        leftX            = 0.0;
        leftY            = 0.0;

        rightX           = 0.0;
        rightY           = 0.0;

        unplugReported   = false;
    }
}
