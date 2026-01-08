package frc.robot.subsystems.swervedrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LimelightHelpers;

public class Limelight_LED_Test extends SubsystemBase {

    private int counter = 0;

    public Limelight_LED_Test() {
        // Set AprilTag pipeline once
        LimelightHelpers.setPipelineIndex("limelight-a", 9);
        LimelightHelpers.setLEDMode_PipelineControl("limelight-a");
    }

    @Override
    public void periodic() {
        LimelightHelpers.LimelightResults results =
                LimelightHelpers.getLatestResults("limelight-a");

        boolean seesAprilTag =
                results != null &&
                results.valid &&
                results.targets_Fiducials != null &&
                results.targets_Fiducials.length > 0;

        // LED control
        if (seesAprilTag) {
            LimelightHelpers.setLEDMode_ForceOn("limelight-a");
        } 
        else {
            LimelightHelpers.setLEDMode_ForceOff("limelight-a");
        }

        //Debug Output

        // Dashboard (updates every loop)
        SmartDashboard.putBoolean("Sees AprilTag", seesAprilTag);
        SmartDashboard.putNumber(
                "AprilTag Count",
                results != null && results.targets_Fiducials != null
                        ? results.targets_Fiducials.length
                        : 0
        );

        // Console print ~2 times per second
        counter++;
        if (counter % 25 == 0) {
            int count = (results != null && results.targets_Fiducials != null)
                    ? results.targets_Fiducials.length
                    : 0;

            System.out.println("AprilTags detected: " + count);
        }
    }
}
