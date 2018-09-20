package model;

import math.IActivation;
import math.IError;

public class OutputLayer extends Layer{
	
	public OutputLayer(int neuronCount, IActivation activationFunc) {
		super(neuronCount, activationFunc);
	}

}
