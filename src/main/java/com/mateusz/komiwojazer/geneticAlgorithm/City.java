package com.mateusz.komiwojazer.geneticAlgorithm;

import java.util.concurrent.ThreadLocalRandom;

public class City{

	private final double x;
	private final double y;
	public static final double radius = 0.025;

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
		return new City(ThreadLocalRandom.current().nextDouble(radius,1-radius),ThreadLocalRandom.current().nextDouble(radius*2,1-(radius*4)));
	}
	
	@Override
	public String toString() {
		return String.format("(%f;%f)", x, y);
	}
	
}
