package com.mateusz.komiwojazer.geneticAlgorithm;

public class City implements Cloneable{

	private final double x;
	private final double y;

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
		return new City(Math.random(), Math.random());
	}
	
	@Override
	protected City clone(){
		return new City(x,y);
	}
	
	@Override
	public String toString() {
		return String.format("(%f;%f)", x, y);
	}
	
}
