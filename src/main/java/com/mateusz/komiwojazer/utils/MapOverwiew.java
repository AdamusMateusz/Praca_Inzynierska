package com.mateusz.komiwojazer.utils;

import java.util.List;

import com.mateusz.komiwojazer.geneticAlgorithm.City;

public class MapOverwiew {

	private final int id;
	private final int parents;
	private final boolean heuristic;
	private final boolean saveFittingFunctionValue;
	private final boolean password;
	private final boolean complete;
	private final boolean running;
	private final List<City> cities;
	private final double quality;

	public MapOverwiew(Request req, boolean complete, boolean running, List<City> cities,double quality) {
		this.id = req.getId();
		this.parents = req.getParents();
		this.heuristic = req.isHeuristic();
		this.saveFittingFunctionValue = req.isSaveFittingFunctionValue();
		this.password = req.isProtected();
		this.complete = complete;
		this.running = running;
		this.cities = cities;
		this.quality = quality;
	}

	public int getId() {
		return id;
	}

	public int getParents() {
		return parents;
	}

	public boolean isHeuristic() {
		return heuristic;
	}

	public boolean isSaveFittingFunctionValue() {
		return saveFittingFunctionValue;
	}

	public boolean isProtected() {
		return password;
	}

	public boolean isComplete() {
		return complete;
	}

	public List<City> getCities() {
		return cities;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public double getQuality() {
		return quality;
	}
}
