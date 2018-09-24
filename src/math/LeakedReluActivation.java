package math;

public class LeakedReluActivation implements IActivation {

	@Override
	public float activate(float input) {
		if (input > 0) return input;
		else return -input / 100.0f;
	}

	@Override
	public float derivative(float input) {
		if (input <= 0) return input / 100.0f;
		else return 1;
	}

}
