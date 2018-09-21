package main;

import java.util.List;
import io.DataLoader;
import io.DataLoader.Seperator;
import math.IActivation.ActivationFunction;
import math.SquaredError;
import model.HiddenLayer;
import model.NeuralNetwork;
import model.OutputLayer;
import model.Trainer;

public class XORTest {
	
	public static void main(String[] args){

		// Create neural network
		NeuralNetwork nn = new NeuralNetwork(0.1f, 5E-3f, 3000000, new SquaredError());
		nn.addLayer(new HiddenLayer(2, ActivationFunction.SIGMOID));
		nn.addLayer(new HiddenLayer(3, ActivationFunction.SIGMOID));
		nn.addLayer(new OutputLayer(2, ActivationFunction.SIGMOID));
		
		// Load data
		List<float[]> traindata = DataLoader.loadData("C:\\Users\\user\\Documents\\AI Projects\\JANN\\traindata.txt", Seperator.COMMA);
		List<float[]> testdata = DataLoader.loadData("C:\\Users\\user\\Documents\\AI Projects\\JANN\\testdata.txt", Seperator.COMMA);
		
		// Train and test
		Trainer trainer = new Trainer(nn);
		trainer.showIterations(150000);
		trainer.train(traindata);	
		trainer.test(testdata);
		
	}

}
