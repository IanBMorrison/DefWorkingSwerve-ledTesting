public class TestLimelight {

    public void updateVision() {

        // Pipeline + LEDs
        LimelightHelpers.setPipelineIndex("", 0);
        LimelightHelpers.setLEDMode_PipelineControl("");

        // Send robot orientation to Limelight
        double robotYaw = m_gyro.getRotation2d().getDegrees();
        LimelightHelpers.SetRobotOrientation(
            "", robotYaw,
            0.0, 0.0,    // pitch, roll
            0.0, 0.0, 0.0
        );

        // Get MegaTag2 pose estimate (FIELD RELATIVE)
        LimelightHelpers.PoseEstimate llMeasurement =
            LimelightHelpers.getBotPoseEstimate_wpiBlue("");

        // ❗ DO NOTHING if no tags
        if (llMeasurement.tagCount == 0) {
            LimelightHelpers.setLEDMode_ForceOff("");
            return;
        }

        // Convert Pose3d → Pose2d
        Pose2d visionPose = llMeasurement.pose.toPose2d();

        // Adjust trust based on tag count
        if (llMeasurement.tagCount >= 2) {
            // High confidence
            m_poseEstimator.setVisionMeasurementStdDevs(
                VecBuilder.fill(0.5, 0.5, 9999999)
            );
            LimelightHelpers.setLEDMode_ForceOn("");
        } else { // tagCount == 1
            // Lower confidence
            m_poseEstimator.setVisionMeasurementStdDevs(
                VecBuilder.fill(1.2, 1.2, 9999999)
            );
            LimelightHelpers.setLEDMode_ForceBlink("");
        }

        // Add vision measurement
        m_poseEstimator.addVisionMeasurement(
            visionPose,
            llMeasurement.timestampSeconds
        );
    }
}
