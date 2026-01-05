LimelightResults results = LimelightHelpers.getLatestResults("");
if (results.valid) {
    // Color/Retroreflective targets
    if (results.targets_Retro.length > 0) {
        LimelightTarget_Retro target = results.targets_Retro[0];
        double skew = target.ts;             // Target skew/rotation
        double shortSide = target.short_side; // Shortest side in pixels
        double longSide = target.long_side;   // Longest side in pixels
        Pose3d targetPose = target.getCameraPose_TargetSpace();
    }
    
    // AprilTags/Fiducials
    if (results.targets_Fiducials.length > 0) {
        LimelightTarget_Fiducial tag = results.targets_Fiducials[0];
        double id = tag.fiducialID;          // Tag ID
        String family = tag.fiducialFamily;   // Tag family (e.g., "16h5")
        
        // 3D Pose Data
        Pose3d robotPoseField = tag.getRobotPose_FieldSpace();    // Robot's pose in field space
        Pose3d cameraPoseTag = tag.getCameraPose_TargetSpace();   // Camera's pose relative to tag
        Pose3d robotPoseTag = tag.getRobotPose_TargetSpace();     // Robot's pose relative to tag
        Pose3d tagPoseCamera = tag.getTargetPose_CameraSpace();   // Tag's pose relative to camera
        Pose3d tagPoseRobot = tag.getTargetPose_RobotSpace();     // Tag's pose relative to robot
        
        // 2D targeting data
        double tx = tag.tx;                  // Horizontal offset from crosshair
        double ty = tag.ty;                  // Vertical offset from crosshair
        double ta = tag.ta;                  // Target area (0-100% of image)
    }

    // Neural network detections
    if (results.targets_Detector.length > 0) {
        LimelightTarget_Detector detection = results.targets_Detector[0];
        String className = detection.className;
        double confidence = detection.confidence;
        double area = detection.ta;
    }
    
    // Classifier results
    if (results.targets_Classifier.length > 0) {
        LimelightTarget_Classifier result = results.targets_Classifier[0];
        String class_name = result.className;
        double confidence = result.confidence;
        int classID = (int)result.classID;
    }
    
    // Barcode/QR code results
    if (results.targets_Barcode.length > 0) {
        LimelightTarget_Barcode barcode = results.targets_Barcode[0];
        String data = barcode.data;
        String family = barcode.family;
    }
}