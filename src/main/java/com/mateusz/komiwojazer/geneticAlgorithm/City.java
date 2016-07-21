package com.mateusz.komiwojazer.geneticAlgorithm;

public class City {

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
}
