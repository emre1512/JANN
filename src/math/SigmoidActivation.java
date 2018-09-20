package math;

public class SigmoidActivation implements IActivation {

	@Override
	public float activate(float input) {
		return (float) (1f / (1f + Math.exp(-input)));
	}

	@Override
	public float derivative(float input) {
		return input * (1.0f - input);
	}

}
