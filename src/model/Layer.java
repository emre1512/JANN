package model;

import java.util.ArrayList;
import java.util.List;

import math.IActivation;
import math.ReluActivation;
import math.IActivation.ActivationFunction;
import math.LeakedReluActivation;
import math.LinearActivation;
import math.SigmoidActivation;
import math.TanHActivation;

public abstract class Layer {

	private List<Neuron> neurons;
	private IActivation activationFunction;
	private int neuronCount;
	
	public Layer(int neuronCount, ActivationFunction activationFunction){
		this.neurons = new ArrayList<>();
		this.neuronCount = neuronCount;
		
		setActivationFunction(activationFunction);
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
		
	private void setActivationFunction(ActivationFunction af){
		if(af == ActivationFunction.SIGMOID){
			this.activationFunction = new SigmoidActivation();
		}
		else if(af == ActivationFunction.TANH){
			this.activationFunction = new TanHActivation();
		}
		else if(af == ActivationFunction.RELU){
			this.activationFunction = new ReluActivation();
		}
		else if(af == ActivationFunction.LEAKED_RELU){
			this.activationFunction = new LeakedReluActivation();
		}
		else if(af == ActivationFunction.LINEAR){
			this.activationFunction = new LinearActivation();
		}
	}
	
}
