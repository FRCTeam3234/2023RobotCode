package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PixyTracker {
    
    public ArcadeDriveInputs getMotorInputs(SensorInputs sensorInputs)
    {
        ArcadeDriveInputs result = new ArcadeDriveInputs();
        if (sensorInputs.objectDetected)
        {
            int objectHorizontalCenterCoordinate =
                sensorInputs.objectXCoordinate +
                (sensorInputs.objectWidth / 2);
            SmartDashboard.putNumber("x calc middle", objectHorizontalCenterCoordinate);
            if (objectHorizontalCenterCoordinate < 145)
            {
                SmartDashboard.putBoolean("turn left", true);
                SmartDashboard.putBoolean("turn right", false);
                result.rotation = (float)-.1 - ( (145 - sensorInputs.objectXCoordinate) / 435);
            }
            else
            {
                if (objectHorizontalCenterCoordinate > 169)
                {
                    SmartDashboard.putBoolean("turn left", false);
                    SmartDashboard.putBoolean("turn right", true);
                    result.rotation = (float).1 + ( (315 - sensorInputs.objectXCoordinate) / 438);
                }
                else
                {
                    SmartDashboard.putBoolean("turn left", false);
                    SmartDashboard.putBoolean("turn right", false);
                }
            }
        }
        return result;
    }
}
