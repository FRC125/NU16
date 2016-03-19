package com.nutrons.stronghold;

/**
 * 
 * @author Asher Gottlieb
 *
 */
public class AngleCalculator {
	/**
	 * @param mountAngle angle the camera is mounted at
	 * @param cameraXAngle horizontal angle to the target from the camera
	 * @param cameraYAngle vertical angle to the target from the camera
	 * @return the horizontal angle to the target from the robot's center
	 */
	// TODO calculate height from arm angle instead of using constant
	public static double getHorizontalAngle(double mountAngle, double cameraXAngle, double cameraYAngle){
		double measuredDistance = getCameraDist(mountAngle + cameraYAngle);
    	double distance = getRobotDist(measuredDistance,cameraXAngle);
    	return Math.toDegrees(Math.acos((Math.pow(RobotMap.CAMERA_X_OFFSET, 2) + Math.pow(measuredDistance, 2)
    		- Math.pow(distance, 2)) / (2*distance*measuredDistance))) - 90.0;
    }
	/**
	 * @param x horizontal position of target, in pixels, on the image
	 * @return horizontal position of target in degrees
	 */
	public static double getHorizontalCameraAngle(double x){
		double slope = RobotMap.CAMERA_FOV/RobotMap.CAMERA_PIXEL_WIDTH;
	    double intercept = -RobotMap.CAMERA_FOV/2;
	    return x*slope+intercept;
	}
	/**
	 * @param y vertical position of target, in pixels, on the image
	 * @return vertical position of target in degrees
	 */
	public static double getVerticalCameraAngle(double y){
    	double slope = RobotMap.CAMERA_VERTICAL_FOV/RobotMap.CAMERA_PIXEL_HEIGHT;
        double intercept = -RobotMap.CAMERA_VERTICAL_FOV/2;
        return y*slope+intercept;
    }
	
	public static boolean isTargetSeen(double x) {
		return Math.abs(getHorizontalCameraAngle(x)) != RobotMap.GRIP_IGNORE_VALUE;
	}
	

    /**
     * @param yAngle angle from the horizontal plane to the target
     * @return the distance to the target
     */
    private static double getCameraDist(double yAngle){
    	return (RobotMap.TARGET_HEIGHT-RobotMap.CAMERA_Z_OFFSET)/Math.tan(Math.toRadians(yAngle));
    }
    /**
     * @param cameraDistance the distance from the camera to the target
     * @param cameraXAngle horizontal angle to the target from the camera
     * @return the Robot's distance to the target
     */
    private static double getRobotDist(double cameraDistance, double cameraXAngle){
    	double angle = 90 + cameraXAngle;
    	return Math.sqrt(Math.pow(RobotMap.CAMERA_X_OFFSET,2)+Math.pow(cameraDistance,2)-(2*RobotMap.CAMERA_X_OFFSET*cameraDistance*Math.cos(Math.toRadians(angle))));
    }
    
    public static void main(String[] args){
    	double yAngle = 45;
    	double cameraXAngle = 10;
    	double cameraDistance  = getCameraDist(yAngle);
    	System.out.println(cameraDistance);
    	System.out.println(getRobotDist(cameraDistance, cameraXAngle));
		System.out.println(getHorizontalAngle(0, cameraXAngle, yAngle));
    }
    
}