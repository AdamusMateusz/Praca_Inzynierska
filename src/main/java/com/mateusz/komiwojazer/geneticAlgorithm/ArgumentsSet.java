package com.mateusz.komiwojazer.geneticAlgorithm;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArgumentsSet {

	private final int quantity;
	private final int parents;
	private final int kids;
	private final byte crossingGene;
	private final byte crossingChromosome;
	private final float mutationGene;
	private final float mutationChromosome;
	private final byte change;
	private final boolean useParents;
	private final String password;

	public ArgumentsSet(int quantity, int parents, int kids, byte crossingGene, byte crossingChromosome,
			float mutationGene, float mutationChromosome, byte change, boolean useParents, String password) {
		this.quantity = quantity;
		this.parents = parents;
		this.kids = kids;
		this.crossingGene = crossingGene;
		this.crossingChromosome = crossingChromosome;
		this.mutationGene = mutationGene;
		this.mutationChromosome = mutationChromosome;
		this.change = change;
		this.useParents = useParents;
		this.password = password;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getParents() {
		return parents;
	}

	public int getKids() {
		return kids;
	}

	public byte getCrossingGene() {
		return crossingGene;
	}

	public byte getCrossingChromosome() {
		return crossingChromosome;
	}

	public float getMutationGene() {
		return mutationGene;
	}

	public float getMutationChromosome() {
		return mutationChromosome;
	}

	public byte getChange() {
		return change;
	}

	public boolean isUseParents() {
		return useParents;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

}
