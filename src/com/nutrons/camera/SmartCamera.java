package com.nutrons.camera;

import com.nutrons.stronghold.RobotMap;

public class SmartCamera {
	
	private GripCamera camera;
	private int updateFrequency;
	private long nextUpdateTime = 0;
	private boolean updating = false;
	
	public SmartCamera(GripCamera camera, int updateFrequency){
		this.camera = camera;
		this.updateFrequency = updateFrequency;
	}
	
	public double getCameraHorizontalAngle(){
		boolean isUpdating = update();
		double slope = camera.getFOV()/camera.getPixelWidth();
	    double intercept = -camera.getFOV()/2;
	    double toRet = camera.getCenterX()*slope+intercept;
	    if(isUpdating)updating=false;
	    return toRet;
	}
	
	public double getCameraVerticalAngle(){
		boolean isUpdating = update();
		double slope = camera.getVerticalFOV()/camera.getPixelHeight();
	    double intercept = -camera.getVerticalFOV()/2;
	    double toRet = camera.getCenterY()*slope+intercept;
	    if(isUpdating)updating=false;
	    return toRet;
	}
	
	public double getCameraDistance(){
		boolean isUpdating = update();
		double toRet = (RobotMap.TARGET_HEIGHT_ON_TOWER-camera.getMountZOffset())/Math.tan(Math.toRadians(this.getCameraVerticalAngle()+camera.getMountAngle()));
		if(isUpdating)updating=false;
		return toRet;
	}
	
	public double getRobotDistance(){
		boolean isUpdating = update();
		double angle = 90 + ((camera.getMountXOffset() > 0) ? this.getCameraHorizontalAngle() : -this.getCameraHorizontalAngle());
    	if(camera.getMountXOffset() == 0.0){
    		return this.getCameraDistance();
    	}
    	double toRet = Math.sqrt(Math.pow(camera.getMountXOffset(),2)+Math.pow(this.getCameraDistance(),2)-(2*Math.abs(camera.getMountXOffset())*this.getCameraDistance()*Math.cos(Math.toRadians(angle))));
    	if(isUpdating)updating=false;
    	return toRet;
	}
	
	public double getRobotHorizontalAngle(){
		boolean isUpdating = update();
		double cameraDistance = this.getCameraDistance();
    	double distance = this.getRobotDistance();
    	double toRet = Math.toDegrees(Math.acos((Math.pow(camera.getMountXOffset(), 2) - Math.pow(cameraDistance, 2)
        		+ Math.pow(distance, 2)) / (2*distance*Math.abs(camera.getMountXOffset()))))-90.0;
    	if(isUpdating)updating=false;
    	return toRet;
	}
	
	private boolean update(){
		if(!this.updating){
			if(this.nextUpdateTime<=System.currentTimeMillis()){
				this.updating = true;
				camera.updateData();
				this.nextUpdateTime = System.currentTimeMillis() + this.updateFrequency;
				return true;
			}
		}
		return false;
	}
	
}
