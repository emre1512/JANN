package math;

import java.util.List;

import model.Neuron;

public class MeanSquaredError implements IError {

	@Override
	public float error(float[] desiredOutput, List<Neuron> neurons) {
		float error = 0;    
    	for(int i = 0; i < neurons.size(); i++){
    		Neuron neuron = neurons.get(i);    		
    		error += (float) Math.pow(neuron.getActivationOutput() - desiredOutput[i], 2);
    	}
		
		return error / neurons.size();
	}

	// Derivative of global error G = sqrt(sum((ai - yi)^2))
	// with respect to the individual output neuron
	// Thus, G' = (ai - yi) for every output neuron itself.
	@Override
	public float derivative(float result, float desired, int neuronCount) {
		return 2.0f * (result - desired) / neuronCount;
	}
	
}
