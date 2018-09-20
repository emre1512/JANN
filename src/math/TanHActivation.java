package math;

public class TanHActivation implements IActivation{

	@Override
	public float activate(float input) {
		float f = (float) ((Math.exp(input) - Math.exp(-input)) / (0.0001f + Math.exp(input) + Math.exp(-input)));
		return f;
	}

	@Override
	public float derivative(float input) {
		float f = (float) ((Math.exp(input) - Math.exp(-input)) / (0.0001f + Math.exp(input) + Math.exp(-input)));
		return 1.0f - (f * f);
	}

}
