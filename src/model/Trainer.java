package model;

import java.util.List;

import util.ResultQuantizer;

public class Trainer {

	private NeuralNetwork nn;
	private int epoch;
	private int iteration;
	
	public Trainer(NeuralNetwork nn){
		this.nn = nn;
		this.epoch = nn.getEpoch();
	}
	
	public void train(List<float[]> trainDataset){
				
		int sampleCount = trainDataset.size();
		int inputCount = trainDataset.get(0).length - 1;
		int classLabelIndex = trainDataset.get(0).length - 1;
		
		initNeurons(inputCount);
		
		int iteration = 0;
		
		do {

        	iteration++;
        	
        	for(int i = 0; i < sampleCount; i++){
        		
        		float[] sample = trainDataset.get(i);
        		
        		float[] inputs = new float[inputCount];
        		
        		for(int j = 0; j < inputCount; j++){            		
            		inputs[j] = sample[j];
        		}
        		
        		nn.setInputs(inputs);
        		nn.setDesiredOutput(sample[classLabelIndex]);
        		
        		nn.train();
        	}
        	        	
            if (iteration % 50000 == 0) {
                System.out.println("-------------------------------");
                System.out.println("Current iteration:" + iteration);
                System.out.println("Current error:" + nn.getGlobalError());
                System.out.println("-------------------------------");
            }
        	
		} while (!nn.hasLearnt() && iteration < epoch);
		
		this.iteration = iteration;
	}
	
	public void test(List<float[]> testDataset){
      
	    System.out.println("Training has been completed.");
	    System.out.println("Total iteration: " + this.iteration + ", accepted error: " + nn.getGlobalError());
	    System.out.println("Test cases are in progress...");
	       
		int sampleCount = testDataset.size();
		int inputCount = testDataset.get(0).length - 1;
		int classLabelIndex = testDataset.get(0).length - 1;
	    
	    for(int i = 0; i < sampleCount; i++){
    		
    		float[] sample = testDataset.get(i);
    		
    		float[] inputs = new float[inputCount];
    		
    		for(int j = 0; j < inputCount; j++){            		
        		inputs[j] = sample[j];
    		}
    		
    		nn.setInputs(inputs);
    		nn.setDesiredOutput(sample[classLabelIndex]);
    		
    		nn.test();
    		
    		int outputLayerIndex = nn.getLayers().size() - 1;
    		
    		for(int j = 0; j < nn.getLayers().get(outputLayerIndex).getNeuronCount(); j++){
    	        System.out.println(testDataset.get(i)[0] + " XOR " + testDataset.get(i)[1] + " = " + 
    					ResultQuantizer.quantizeResult(nn.getLayers().get(outputLayerIndex).getNeurons().get(j).getActivationOutput()));
    		}


    	}
	    		
	}  
	
	public void initNeurons(int inputCount){

		int firstLayerIndex = 0;
		
		for(int i = 0; i < nn.getLayers().size(); i++){
		
			Layer layer = nn.getLayers().get(i);
			
			
			if(i == firstLayerIndex){
				for(int j = 0; j < layer.getNeuronCount(); j++){
					Neuron neuron = new Neuron(layer.getActivationFunction(), inputCount);				
					layer.addNeuron(neuron);
				}
			}
			else{
				for(int j = 0; j < layer.getNeuronCount(); j++){
					int weightCount = nn.getLayers().get(i-1).getNeuronCount();
					Neuron neuron = new Neuron(layer.getActivationFunction(), weightCount);
					layer.addNeuron(neuron);
				}

			}
			

			
		}
	}
}
