
def estamater(int tagcout, double accuracy){

  LimelightHelpers.setPipelineIndex("", 9);

  double robotYaw = -m_gyro.getYaw();  
  LimelightHelpers.SetRobotOrientation("", robotYaw, 0.0, 0.0, 0.0, 0.0, 0.0);

  // Get the pose estimate
  LimelightHelpers.PoseEstimate limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue("");

// Add it to your pose estimator
  if (limelightMeasurement == null){
    return;
  }
  double ambiguity = limelightMeasurement.avgTagAmbiguity;
  double xyStdDev = 0.5 + ambiguity * 2.0;
  double thetaStdDev = Math.toRadians(5 + ambiguity * 20);
  if (limelightMeasurement != null && limelightMeasurement.tagCount > 0 && limelightMeasurement.avgTagAmbiguity < 0.2){
      if (limelightMeasurement.tagCount > 0){
        m_poseEstimator.setVisionMeasurementStdDevs(
            VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
        );
        m_poseEstimator.addVisionMeasurement(
            limelightMeasurement.pose,
            limelightMeasurement.timestampSeconds
        );
        // put Led change
      }

      if (limelightMeasurement.tagCount > 0){
          m_poseEstimator.setVisionMeasurementStdDevs(
              VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
          );
          m_poseEstimator.addVisionMeasurement(
              limelightMeasurement.pose,
              limelightMeasurement.timestampSeconds
          );
        // put Led change
      }
  }
}
