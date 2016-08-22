package com.mateusz.komiwojazer.utils;

import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Request {

	private int id;
	private final int citiesQuantity;
	private final int parents;
	private final int kids;
	private final byte crossingGene;
	private final byte crossingChromosome;
	private final float mutationGene;
	private final float mutationChromosome;
	private final byte change;
	private final boolean useParents;
	private final boolean heuristic;
	private final boolean saveFittingFunctionValue;
	private final String password;

	public Request(int id, int quantity, int parents, int kids, byte crossingGene, byte crossingChromosome,
			float mutationGene, float mutationChromosome, byte change, boolean useParents, boolean heuristic, boolean saveFittingFunctionValue, String password) {
		this.id=id;
		this.citiesQuantity = (quantity < 5 || quantity >100) ? 5 : quantity;
		this.parents = parents < 2 ? 2 : parents;
		this.kids = kids < 2 ? 2 : parents;
		this.crossingGene = (crossingGene < 0 || crossingGene > 100) ? -1 : crossingGene;
		this.crossingChromosome = (crossingChromosome < 0 || crossingChromosome > 100) ? -1 : crossingChromosome;
		this.mutationGene = (mutationGene < 0 || mutationGene > 10) ? -1 : mutationGene;
		this.mutationChromosome = (mutationChromosome < 0 || mutationChromosome > 10) ? -1 : mutationChromosome;
		this.change = (change < 0 || change > 100) ? -1 : change;
		this.useParents = useParents;
		this.heuristic = heuristic;
		this.saveFittingFunctionValue = saveFittingFunctionValue;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getCitiesQuantity() {
		return citiesQuantity;
	}

	public int getParents() {
		return parents;
	}

	public int getKids() {
		return kids;
	}

	public int getCrossingGene() {
		return crossingGene != -1 ? crossingGene :  ThreadLocalRandom.current().nextInt(0,101);
	}
	public boolean hasCrossingGenePossibility(){
		return ThreadLocalRandom.current().nextInt(0,101) <= getCrossingGene();
	}

	public int getCrossingChromosome() {
		return crossingChromosome != -1 ? crossingChromosome : ThreadLocalRandom.current().nextInt(0,101);
	}

	public boolean hasCrossingChromosomePossibility(){
		return ThreadLocalRandom.current().nextInt(0,101) <= getCrossingChromosome();
	}
	
	public float getMutationGene() {
		return mutationGene != -1 ? mutationGene : (float) ThreadLocalRandom.current().nextDouble(0,10);
	}
	
	public boolean hasMutationGenePossibility(){
		return ThreadLocalRandom.current().nextDouble(0,10) <= getMutationGene();
	}

	public float getMutationChromosome() {
		return mutationChromosome != -1 ? mutationChromosome : (float) ThreadLocalRandom.current().nextDouble(0,1);
	}
	
	public boolean hasMutationChromosomePossibility(){
		return ThreadLocalRandom.current().nextDouble(0,10) <= getMutationChromosome();
	}

	public int getChange() {
		return change != -1 ? change :  ThreadLocalRandom.current().nextInt(0,101);
	}

	public boolean isUseParents() {
		return useParents;
	}
	
	public boolean isHeuristic(){
		return heuristic;
	}
	
	public boolean isProtected(){
		return !(password == null || password.isEmpty());
	}

	public boolean isSaveFittingFunctionValue(){
		return saveFittingFunctionValue;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonIgnore
	public boolean isChangeCritical(Request set) {
		return citiesQuantity != set.getCitiesQuantity();
	}

	public static Request fakeSet() {
		return new Request(0,9, 5, 5, (byte)50, (byte)50, 5, 5, (byte) 50, true,false,true, "Haslo");
	}

	public static Request randomFakeSet(int i) {
		String password = Math.random() > 0.5 ? "" : "Haslo"; 
		boolean saveFittingFunctionValue = Math.random() > 0.5 ? true : false;
		boolean heuristic = Math.random() > 0.5 ? true : false;
		return new Request(0,i, 5, 5, (byte)50, (byte)50, 5, 5, (byte) 50, true,heuristic,saveFittingFunctionValue, password);
	}

}
