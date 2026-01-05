public class Main {
    // Set a custom crop window for improved performance (-1 to 1 for each value)
LimelightHelpers.setCropWindow("", -0.5, 0.5, -0.5, 0.5);


// Change the camera pose relative to robot center (x forward, y left, z up, degrees)
LimelightHelpers.setCameraPose_RobotSpace("", 
    0.5,    // Forward offset (meters)
    0.0,    // Side offset (meters)
    0.5,    // Height offset (meters)
    0.0,    // Roll (degrees)
    30.0,   // Pitch (degrees)
    0.0     // Yaw (degrees)
);

// Set AprilTag offset tracking point (meters)
LimelightHelpers.setFiducial3DOffset("", 
    0.0,    // Forward offset
    0.0,    // Side offset  
    0.5     // Height offset
);

// Configure AprilTag detection
LimelightHelpers.SetFiducialIDFiltersOverride("", new int[]{1, 2, 3, 4}); // Only track these tag IDs
LimelightHelpers.SetFiducialDownscalingOverride("", 2.0f); // Process at half resolution for improved framerate and reduced range
    
    
    
    LimelightHelpers.setPipelineIndex("",0)
    // Basic targeting data
    if (LimelightHelpers.getTV("")) {
    double tx = LimelightHelpers.getTX("");  // Horizontal offset from crosshair to target in degrees
    double ty = LimelightHelpers.getTY("");  // Vertical offset from crosshair to target in degrees
    double ta = LimelightHelpers.getTA("");  // Target area (0% to 100% of image)
    boolean hasTarget = LimelightHelpers.getTV(""); // Do you have a valid target?

    double txnc = LimelightHelpers.getTXNC("");  // Horizontal offset from principal pixel/point to target in degrees
    double tync = LimelightHelpers.getTYNC("");  // Vertical  offset from principal pixel/point to target in degrees

    }

    double robotYaw = m_gyro.getYaw();  
    LimelightHelpers.SetRobotOrientation("", robotYaw, 0.0, 0.0, 0.0, 0.0, 0.0);

    LimelightHelpers.PoseEstimate limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue("");
    if ( limelightMeasurement.tagCount >= 2){

        m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.5, .5, 9999999));
        m_poseEstimator.addVisionMeasurement(
            limelightMeasurement.pose,
            limelightMeasurement.timestampSeconds

        );
        Pose2d finalPose3 = m_poseEstimator.getEstimatedPosition();
    }
    if ( limelightMeasurement.tagCount = 1){

        m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.5, .5, 9999999));
        m_poseEstimator.addVisionMeasurement(
            limelightMeasurement.pose,
            limelightMeasurement.timestampSeconds

        );
        Pose2d finalPose2 = m_poseEstimator.getEstimatedPosition();
    }
    if ( limelightMeasurement.tagCount = 0p){

        m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.5, .5, 9999999));
        m_poseEstimator.addVisionMeasurement(
            limelightMeasurement.pose,
            limelightMeasurement.timestampSeconds

        );
        Pose2d finalPose1 = m_poseEstimator.getEstimatedPosition();
    }
}

