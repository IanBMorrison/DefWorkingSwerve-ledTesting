package frc.robot.subsystems.swervedrive;

import frc.robot.subsystems.Leds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class Limelight_LED_Test extends SubsystemBase {

    @Override
    public void periodic() {
        LimelightHelpers.setPipelineIndex("", 9);
        LimelightHelpers.LimelightResults results =
                LimelightHelpers.getLatestResults("");
                

        boolean seesAprilTag =
                results != null &&
                results.targets_Fiducials != null &&
                results.targets_Fiducials.length > 0;

        if (seesAprilTag) {
            LimelightHelpers.setLEDMode_ForceBlink("");
            setGreen()
        } else {
            LimelightHelpers.setLEDMode_ForceOff("");
        }
    }
}
