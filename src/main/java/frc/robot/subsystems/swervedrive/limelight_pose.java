
def estamater(int tagcout, double accuracy){
  
  double tx = LimelightHelpers.getTX("");  // Horizontal offset from crosshair to target in degrees
  double ty = LimelightHelpers.getTY("");  // Vertical offset from crosshair to target in degrees
  double ta = LimelightHelpers.getTA("");  // Target area (0% to 100% of image)
  boolean hasTarget = LimelightHelpers.getTV(""); // Do you have a valid target?

  double txnc = LimelightHelpers.getTXNC("");  // Horizontal offset from principal pixel/point to target in degrees
  double tync = LimelightHelpers.getTYNC("");  // Vertical  offset from principal pixel/point to target in degrees
  // First, tell Limelight your robot's current orientation
  LimelightHelpers.setPipelineIndex("", 9);

  double robotYaw = -m_gyro.getYaw();  
  LimelightHelpers.SetRobotOrientation("", robotYaw, 0.0, 0.0, 0.0, 0.0, 0.0);

  // Get the pose estimate
  LimelightHelpers.PoseEstimate limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue("");

// Add it to your pose estimator
  double ambiguity = limelightMeasurement.avgTagAmbiguity;
  double xyStdDev = 0.5 + ambiguity * 2.0;
  double thetaStdDev = Math.toRadians(5 + ambiguity * 20);
  if (limelightMeasurement != null && limelightMeasurement.tagCount > 0 && limelightMeasurement.avgTagAmbiguity < 0.2){
  
      m_poseEstimator.setVisionMeasurementStdDevs(
          VecBuilder.fill(xyStdDev, xyStdDev, thetaStdDev)
      );
      m_poseEstimator.addVisionMeasurement(
          limelightMeasurement.pose,
          limelightMeasurement.timestampSeconds
      );
      Pose2d pose = m_poseEstimator.getEstimatedPosition();

  }
}
estamate(limelightMeasurement.tagCount, limelightMeasurement.avgTagAmbiguity);
