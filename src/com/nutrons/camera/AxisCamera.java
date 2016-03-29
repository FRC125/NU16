package com.nutrons.camera;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AxisCamera implements GripCamera {

	private int retryAttempts;
	private NetworkTable contourReport;
	private double[] empty = {0.0};
	private double FOV;
	private int pixelHeight;
	private int pixelWidth;

	private double centerX;
	private double centerY;
	private double height;
	private double width;
	
	public AxisCamera(NetworkTable contourReport, int retryAttempts, int height, int width, double FOV){
		this.retryAttempts = retryAttempts;
		this.contourReport = contourReport;
		this.FOV = FOV;
		this.pixelHeight = height;
		this.pixelWidth = width;
		
		this.centerX = 0.0;
		this.centerY = 0.0;
		this.height = 0.0;
		this.width = 0.0;
	}
	
	public void updateData() {
		this.updateData(this.retryAttempts);
	}
	
	private void updateData(int attempts){
		if(attempts<=0)
			return;
		double[] tempCenterX = this.contourReport.getNumberArray("centerX", this.empty);
		double[] tempCenterY = this.contourReport.getNumberArray("centerY", this.empty);
		double[] tempHeight = this.contourReport.getNumberArray("height", this.empty);
		double[] tempWidth = this.contourReport.getNumberArray("width", this.empty);
		if(sameSize(new double[][]{tempCenterX, tempCenterY, tempHeight, tempWidth})){
			int max = 0;
			double maxsize = 0.0;
			for(int i = 0; i < tempCenterX.length; i++){
				if(tempCenterX[i]>maxsize){
					maxsize = tempCenterX[i];
					max = i;
				}
			}
			this.centerX = tempCenterX[max];
			this.centerY = tempCenterY[max];
			this.height = tempHeight[max];
			this.width = tempWidth[max];
		}else
			this.updateData(attempts-1);
	}
	
	public double getCenterX() {
		return this.centerX;
	}

	public double getHeight() {
		return this.height;
	}

	public double getWidth() {
		return this.width;
	}
	
	public double getFOV() {
		return this.FOV;
	}

	public double getVerticalFOV() {
		return this.FOV*this.pixelHeight/this.pixelWidth;
	}

	public int getPixelHeight() {
		return this.pixelHeight;
	}

	public int getPixelWidth() {
		return this.pixelWidth;
	}
	
	private boolean sameSize(double[][] arrays){
    	if(arrays.length==0||arrays[0].length==0)
    		return false;
    	int length = arrays[0].length;
    	for(double array[] : arrays){
    		if(array.length!=length)
    			return false;
    	}
    	return true;
    }

}