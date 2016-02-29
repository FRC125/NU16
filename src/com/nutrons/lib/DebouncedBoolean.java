package com.nutrons.lib;

/**
 * 
 * @author John Zhang
 *
 */
public class DebouncedBoolean {
	
	private int length;
	private int count;
	private boolean state = false;
	
	public DebouncedBoolean(int length) {
		this.length = length;
		count = 0;
	}
	
	/**
	 * Adds value to DebouncedBoolean
	 * @param in New value
	 */
	public void feed(boolean in) {
		if(state) {
			state = in;
		}else if(in) {
			if(count > length) {
				state = true;
			}else {
				count++;
			}
		}
		
		if(!in) {
			reset();
		}
	}
	
	/**
	 * Returns calculated boolean average
	 * @return Boolean average
	 */
	public boolean get() {
		return state;
	}
	
	/**
	 * Resets average
	 */
	public void reset() {
		count = 0;
	}
}