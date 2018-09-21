package main;

import java.util.List;
import io.DataLoader;
import io.DataLoader.Seperator;
import math.IActivation.ActivationFunction;
import math.SquaredError;
import model.HiddenLayer;
import model.NeuralNetwork;
import model.OutputLayer;
import model.NetworkController;

public class Main {
	
	public static void main(String[] args){

		// Load data
		List<float[]> traindata = DataLoader.loadData("C:\\Users\\user\\Documents\\AI Projects\\JANN\\traindata.txt", Seperator.COMMA);
		List<float[]> testdata = DataLoader.loadData("C:\\Users\\user\\Documents\\AI Projects\\JANN\\testdata.txt", Seperator.COMMA);
		
		// Create neural network
		NeuralNetwork nn = new NeuralNetwork(0.1f, 5E-3f, 3000000, new SquaredError());
		nn.addLayer(new HiddenLayer(2, ActivationFunction.SIGMOID));
		nn.addLayer(new HiddenLayer(3, ActivationFunction.SIGMOID));
		nn.addLayer(new OutputLayer(2, ActivationFunction.SIGMOID));
	
		// Train and test
		NetworkController nc = new NetworkController(nn);
		nc.showIterations(250000);
		nc.trainNetwork(traindata);	
		nc.testNetwork(testdata);
		
	}

}
