package math;

import java.util.List;

import model.Neuron;

public interface IError {

	public enum ErrorFunction { MSE }
	
	public float error(float[] desiredOutput, List<Neuron> neurons);
	public float derivative(float result, float desired, int neuronCount);

	
}
