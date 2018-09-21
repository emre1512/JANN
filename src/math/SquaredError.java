package math;

public class SquaredError implements IError {

	@Override
	public float error(float result, float desired) {
		return (float) Math.pow(result - desired, 2);
	}

	// Derivative of global error G = sqrt(sum((ai - yi)^2))
	// with respect to the individual output neuron
	// Thus, G' = (ai - yi) for every output neuron itself.
	@Override
	public float derivative(float result, float desired) {
		return (result - desired);
	}
	
}
