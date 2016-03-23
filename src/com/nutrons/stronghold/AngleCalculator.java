package com.nutrons.stronghold;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Asher Gottlieb
 *
 */
public class AngleCalculator {
	/**
	 * This method, in general, should be the only one accessed in order to obtain the correct angle
	 * @param pixelHeight the height of the target
	 * @param centerX the x position of target on screen
	 * @return the horizontal angle to the target from the robot's center
	 */
	// TODO calculate height from arm angle instead of using constant
	public static double getHorizontalAngle(double pixelHeight, double centerX){
		double measuredDistance = getCameraDist(pixelHeight);
		SmartDashboard.putNumber("cameraMeasuredDistance", measuredDistance);
    	double distance = getRobotDist(measuredDistance, getHorizontalCameraAngle(centerX));
    	SmartDashboard.putNumber("robotPredictedDistance", distance);
    	return Math.toDegrees(Math.acos((Math.pow(RobotMap.CAMERA_X_OFFSET, 2) - Math.pow(measuredDistance, 2)
    		+ Math.pow(distance, 2)) / (2*distance*Math.abs(RobotMap.CAMERA_X_OFFSET))))-90.0;
    }
	/**
	 * In the event that the camera is centered on the robot, this method can be used
	 * @param x horizontal position of target, in pixels, on the image
	 * @return horizontal position of target in degrees
	 */
	public static double getHorizontalCameraAngle(double x){
		double slope = RobotMap.CAMERA_FOV/RobotMap.CAMERA_PIXEL_WIDTH;
	    double intercept = -RobotMap.CAMERA_FOV/2;
	    return x*slope+intercept;
	}
	/**
	 * @param y vertical position of the target, in pixels, on the image
	 * @return vertical position of target in degrees
	 */
	public static double getVerticalCameraAngle(double y){
    	double slope = -RobotMap.CAMERA_VERTICAL_FOV/RobotMap.CAMERA_PIXEL_HEIGHT;
        double intercept = RobotMap.CAMERA_VERTICAL_FOV/2;
        return y*slope+intercept;
    }
	
	/**
	 * @param x x position of target
	 * @return false if the target isn't actually detected
	 */
	public static boolean isTargetSeen(double x) {
		return Math.abs(getHorizontalCameraAngle(x)) != RobotMap.GRIP_IGNORE_VALUE;
	}
	
	
	/**
	 * NOT TESTED
	 * getCameraDist is the currently implemented method to obtain distance
     * @param yAngle angle from the horizontal plane to the target
     * @return the camera's distance to the target
     */
    public static double getCameraDistWithAngle(double yAngle){
    	return (RobotMap.TARGET_HEIGHT_ON_TOWER-RobotMap.CAMERA_Z_OFFSET)/Math.tan(Math.toRadians(yAngle));
    }
    
    /** 
     * @param targetPixelHeight height of the target in pixels
     * @return the camera's distance to the target
     */
	public static double getCameraDist(double targetPixelHeight){
		double targetAngularWidth = targetPixelHeight*RobotMap.CAMERA_FOV/RobotMap.CAMERA_PIXEL_WIDTH; 
		return (RobotMap.TARGET_HEIGHT/2.0)/Math.tan(Math.toRadians(targetAngularWidth/2.0));
	}
    /**
     * @param cameraDistance the distance from the camera to the target
     * @param cameraXAngle horizontal angle to the target from the camera
     * @return the robot's distance to the target
     */
	@SuppressWarnings("unused")
	private static double getRobotDist(double cameraDistance, double cameraXAngle){
		double angle = 90 + ((RobotMap.CAMERA_X_OFFSET > 0) ? cameraXAngle : -cameraXAngle);
    	if(RobotMap.CAMERA_X_OFFSET == 0.0){
    		return cameraDistance;
    	}
    	return Math.sqrt(Math.pow(RobotMap.CAMERA_X_OFFSET,2)+Math.pow(cameraDistance,2)-(2*Math.abs(RobotMap.CAMERA_X_OFFSET)*cameraDistance*Math.cos(Math.toRadians(angle))));
    }
}