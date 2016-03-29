package com.nutrons.camera;

public interface GripCamera {
	public void updateData();
	
	public double getFOV();
	public double getVerticalFOV();
	
	public int getPixelHeight();
	public int getPixelWidth();
	
	public double getCenterX();
	public double getCenterY();
	public double getHeight();
	public double getWidth();
}
