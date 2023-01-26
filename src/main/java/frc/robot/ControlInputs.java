package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControlInputs {
    
    //Joysticks IDs
    private final int driveStickDeviceId = 3;

    //Joystick Definitions
    private final Joystick driveStick = new Joystick(driveStickDeviceId);
    
    //Variable Defintions
    public double driveStickX = 0.0;
    public double driveStickY = 0.0;
    public boolean speedButton = false;
    public boolean pixyDrive = false;

    //Reading the controls
    public final void readControls() {
        
        driveStickX = driveStick.getX();
        driveStickY = driveStick.getY();
        speedButton = driveStick.getRawButton(11);
        pixyDrive = driveStick.getRawButton(3);
    }
}