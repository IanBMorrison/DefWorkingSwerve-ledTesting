package frc.robot.subsystems.swervedrive.LED;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
public interface LEDIO {
    public static class LEDIOInputs {}

    public default void updateInput(LEDIOInputs inputs) {}

    public default void start() {}

    public default void setData(AddressableLEDBuffer buffer) {}

    public default void setAll(int r, int g, int b) {}

    public default void setPattern(LEDPattern pattern) {}

    public default void setSplitPatterns(LEDPattern left, LEDPattern right) {}

    public default void set(double value) {}

    public default void periodic() {}
}