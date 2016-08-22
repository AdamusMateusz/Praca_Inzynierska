package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.concurrent.ThreadLocalRandom;

public class City{

	private final double x;
	private final double y;
	public static final double RADIUS = 0.02;
	public static final double DIAMETER = RADIUS * 2;
	
	public City(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public City withX(double x){
		return new City(x, this.y);
	}
	
	public City withY(double y){
		return new City(x,this.y);
	}
	
	public static City randomCity(){
		return new City(ThreadLocalRandom.current().nextDouble(RADIUS + 0.0005,1-(RADIUS + 0.0005)),ThreadLocalRandom.current().nextDouble((RADIUS + 0.0005)*2,1-((RADIUS + 0.0005)*4)));
	}
	
	@Override
	public String toString() {
		return String.format("(%f;%f)", x, y);
	}
	
}
