package math;

public class SquaredError implements IError {

	@Override
	public float error(float value) {
//		System.out.println(Math.pow(result - desired, 2));
		return (float) Math.pow(value, 2);
	}

//	@Override
//	public float derivative(float result, float desired) {
//		return 2 * (result - desired);
//	}

	// Derivative of global error G = sqrt(sum((ai - yi)^2))
	// with respect to the individual output neuron
	// Thus, G' = 2 * (ai - yi) for every output neuron itself.
	@Override
	public float derivative(float result, float desired) {
		return 2 * (result - desired);
	}
	
}
