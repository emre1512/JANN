package model;

import java.util.ArrayList;
import java.util.List;

import math.IActivation;

public abstract class Layer {

	private List<Neuron> neurons;
	private IActivation activationFunction;
	private int neuronCount;
	
	public Layer(int neuronCount, IActivation activationFunction){
		this.neurons = new ArrayList<>();
		this.activationFunction = activationFunction;
		this.neuronCount = neuronCount;
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public int getNeuronCount() {
		return neuronCount;
	}

	public IActivation getActivationFunction() {
		return activationFunction;
	}
		
	public void addNeuron(Neuron neuron) {
		this.neurons.add(neuron);
	}	
	
//	private void generateNeurons(int neuronCount){
//		for(int i = 0; i < neuronCount; i++){
//			
//		}
//	}

	
			
	
	
}
