import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {

    private static final String LIMELIGHT_NAME = "limelight";
    
    public void Liams_brain() {
      Pose2d currentPose = m_poseEstimator.getEstimatedPosition();

      def estamater(int tagcout, double accuracy){

        LimelightHelpers.setPipelineIndex("", 9);

        double robotYaw = -m_gyro.getYaw();  
        LimelightHelpers.SetRobotOrientation("", robotYaw, 0.0, 0.0, 0.0, 0.0, 0.0);


        LimelightHelpers.PoseEstimate limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue("");

  
        if (limelightMeasurement == null){
          return;
        }
        double ambiguity = limelightMeasurement.avgTagAmbiguity;
        double xyStdDev = 0.5 + ambiguity * 2.0;
        double thetaStdDev = Math.toRadians(5 + ambiguity * 20);
        if (limelightMeasurement != null && limelightMeasurement.tagCount > 0 && limelightMeasurement.avgTagAmbiguity < 0.2){
            if (limelightMeasurement.tagCount == 1){
              m_poseEstimator.setVisionMeasurementStdDevs(
                  VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
              );
              m_poseEstimator.addVisionMeasurement(
                  limelightMeasurement.pose,
                  limelightMeasurement.timestampSeconds
              );
              LimelightHelpers.setLEDMode_ForceBlink("");
            }

            if (limelightMeasurement.tagCount > 1){
                m_poseEstimator.setVisionMeasurementStdDevs(
                    VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
                );
                m_poseEstimator.addVisionMeasurement(
                    limelightMeasurement.pose,
                    limelightMeasurement.timestampSeconds
                );
                LimelightHelpers.setLEDMode_ForceOn("");
            }
            if (limelightMeasurement.tagCount == 0){
                  m_poseEstimator.setVisionMeasurementStdDevs(
                      VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
                  );
                  m_poseEstimator.addVisionMeasurement(
                    limelightMeasurement.pose,
                    limelightMeasurement.timestampSeconds
                  );
                  LimelightHelpers.setLEDMode_ForceOff("");
            }
        }
    }
  }
}
