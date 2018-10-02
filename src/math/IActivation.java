package math;

public interface IActivation {

	public enum ActivationFunction { SIGMOID, TANH, RELU, LEAKED_RELU, LINEAR }
	
    public float activate(float input);
 
    public float derivative(float input);
	
}
