package frc.robot.subsystems;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {

    private static final String LIMELIGHT_NAME = "limelight";

    private final SwerveDrivePoseEstimator m_poseEstimator;
    private final Gyro m_gyro;

    public LimelightSubsystem(SwerveDrivePoseEstimator poseEstimator, Gyro gyro) {
        m_poseEstimator = poseEstimator;
        m_gyro = gyro;
    }

    
    public void updat_vision() {
        updateVisionEstimate();
    }

    private void updateVisionEstimate() {

        LimelightHelpers.setPipelineIndex(LIMELIGHT_NAME, 9);

        double robotYaw = -m_gyro.getYaw();
        LimelightHelpers.SetRobotOrientation(
            LIMELIGHT_NAME,
            robotYaw,
            0, 0,
            0, 0, 0
        );

        LimelightHelpers.PoseEstimate est =
            LimelightHelpers.getBotPoseEstimate_wpiBlue(LIMELIGHT_NAME);

        if (est == null || est.tagCount == 0 || est.avgTagAmbiguity > 0.2) {
            LimelightHelpers.setLEDMode_ForceOff(LIMELIGHT_NAME);
            return;
        }

        double xyStdDev = est.tagCount > 1 ? 0.3 : 0.8;
        double thetaStdDev = est.tagCount > 1
            ? Math.toRadians(5)
            : Math.toRadians(15);

        m_poseEstimator.setVisionMeasurementStdDevs(
            VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
        );

        m_poseEstimator.addVisionMeasurement(
            est.pose,
            est.timestampSeconds
        );

        if (est.tagCount > 1) {
            LimelightHelpers.setLEDMode_ForceOn(LIMELIGHT_NAME);
        } else {
            LimelightHelpers.setLEDMode_ForceBlink(LIMELIGHT_NAME);
        }
    }

    public Pose2d getCurrentPose() {
        return m_poseEstimator.getEstimatedPosition();
    }
}
