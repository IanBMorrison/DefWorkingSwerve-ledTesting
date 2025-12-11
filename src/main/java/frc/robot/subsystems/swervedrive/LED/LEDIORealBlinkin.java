package frc.robot.subsystems.swervedrive.LED;

import static frc.robot.subsystems.swervedrive.LED.LEDConstants.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class LEDIORealBlinkin implements LEDIO {
    private final Spark leds;

    public LEDIORealBlinkin() {
        leds = new Spark(pwmPort);
    }

    @Override
    public void set(double value) {
        leds.set(value);
    }
}