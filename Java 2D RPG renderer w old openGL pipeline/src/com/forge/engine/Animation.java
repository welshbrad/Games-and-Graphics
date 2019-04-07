package com.forge.engine;

import java.util.ArrayList;

public class Animation {

	private ArrayList<Frame> frames; 
	private int currentFrame;
	
	public Animation(){
		frames = new ArrayList<Frame>();
	}
	
	public void render(){
		Frame temp = frames.get(currentFrame);
		if(temp.render()){
			
			currentFrame++;
			currentFrame %= frames.size();
		}
	}
	
}
