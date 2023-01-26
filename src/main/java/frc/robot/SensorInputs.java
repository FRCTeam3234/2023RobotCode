package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class SensorInputs {
    
    //Sensor Definitions
    private DigitalInput intakeLimitSwitchDown = new DigitalInput(0);
    private DigitalInput intakeLimitSwitchUp = new DigitalInput(1);
    private final SerialPort serialPort = new SerialPort(9600, Port.kUSB1);

    //Variable Defintions
    public boolean intakeLimitDown = false;
    public boolean intakeLimitUp = false;
    public int objectXCoordinate = 0;
    public int objectWidth = 0;
    public boolean objectDetected = false;
    
    //Reading the sensors
    public final void readSensors() {
        intakeLimitDown = intakeLimitSwitchDown.get();
        intakeLimitUp = intakeLimitSwitchUp.get();

        byte[] arduinoResponse = new byte[1];
        serialPort.write(new byte[]{0x61}, 1);
        arduinoResponse = serialPort.read(1);

        if (arduinoResponse[0] == 0x01)
        {
            SmartDashboard.putBoolean("Detected", true);
            byte[] objectData = new byte[8];
            objectData = serialPort.read(8);
            objectXCoordinate = (int)(objectData[0] & 0xFF) * 256 + (int)(objectData[1] & 0xFF);
            objectWidth = (int)(objectData[4] & 0xFF) * 256 + (int)(objectData[5] & 0xFF);
            SmartDashboard.putNumber("Pixy X", objectXCoordinate);
            SmartDashboard.putNumber("Pixy Width", objectWidth);
            objectDetected = true;
        }
        else
        {
           SmartDashboard.putBoolean("Detected", false);
            objectDetected = false;
        }       
    }
}