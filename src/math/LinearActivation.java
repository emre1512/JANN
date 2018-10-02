package math;

public class LinearActivation implements IActivation{

	@Override
	public float activate(float input) {		
		return input;
	}

	@Override
	public float derivative(float input) {
		return 1;
	}

}
