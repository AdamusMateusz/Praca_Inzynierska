package com.mateusz.komiwojazer.geneticAlgorithm;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArgumentsSet {

	private final int citiesQuantity;
	private final int parents;
	private final int kids;
	private final float crossingGene;
	private final float crossingChromosome;
	private final float mutationGene;
	private final float mutationChromosome;
	private final byte change;
	private final boolean useParents;
	private final String password;

	public ArgumentsSet(int quantity, int parents, int kids, float crossingGene, float crossingChromosome,
			float mutationGene, float mutationChromosome, byte change, boolean useParents, String password) {
		this.citiesQuantity = (quantity < 5 && quantity <100) ? 5 : quantity;
		this.parents = parents < 2 ? 2 : parents;
		this.kids = kids < 2 ? 2 : parents;
		this.crossingGene = (crossingGene < 0 && crossingGene > 100) ? -1 : crossingGene;
		this.crossingChromosome = (crossingChromosome < 0 && crossingChromosome > 100) ? -1 : crossingChromosome;
		this.mutationGene = (mutationGene < 0 && mutationGene > 10) ? -1 : mutationGene;
		this.mutationChromosome = (mutationChromosome < 0 && mutationChromosome > 10) ? -1 : mutationChromosome;
		this.change = (change < 0 && change > 100) ? -1 : change;
		this.useParents = useParents;
		this.password = password;
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

	public float getCrossingGene() {
		return crossingGene != -1 ? crossingGene : (float) (Math.random() * 100);
	}

	public float getCrossingChromosome() {
		return crossingChromosome != -1 ? crossingChromosome : (float) (Math.random() * 100);
	}

	public float getMutationGene() {
		return mutationGene != -1 ? mutationGene : (float) (Math.random() * 10);
	}

	public float getMutationChromosome() {
		return mutationChromosome != -1 ? mutationChromosome : (float) (Math.random() * 10);
	}

	public byte getChange() {
		return change != -1 ? change : (byte) (Math.random() * 100);
	}

	public boolean isUseParents() {
		return useParents;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public boolean isChangeCritical(ArgumentsSet set) {
		return citiesQuantity != set.getCitiesQuantity();
	}

	public static ArgumentsSet fakeSet() {
		return new ArgumentsSet(9, 5, 5, 50, 50, 5, 5, (byte) 75, true, "Haslo");
	}

}
