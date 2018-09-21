package math;

public interface IActivation {

	public enum ActivationFunction { SIGMOID, TANH, RELU, LEAKED_RELU }
	
    public float activate(float input);
 
    public float derivative(float input);
	
}
