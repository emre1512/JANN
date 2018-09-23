package model;

import java.util.ArrayList;
import java.util.List;
import constant.Constants;
import log.Logger;
import math.IError;
import math.MeanSquaredError;
import math.IError.ErrorFunction;

public class NeuralNetwork {

	private List<Layer> layers;
	private int epoch;
	private float[] inputs;
	private float[] desiredOutput;
    private float nu;
    private float desiredError;
    private boolean hasOutputLayer = false;
    private float globalError;
    private IError errorFunction;
    
	public List<Layer> getLayers() {
		return layers;
	}

	public int getEpoch() {
		return epoch;
	} 

	public void setInputs(float[] inputs){
		this.inputs = inputs;
	}
	
	public float[] getDesiredOutput(){
		return this.desiredOutput;
	}
	
	public float getLearningRate(){
		return this.nu;
	}
	
	public void setDesiredOutput(float[] desiredOutput){
		this.desiredOutput = desiredOutput;
	}
	
	public void setErrorFunction(IError errorFunction){
		this.errorFunction = errorFunction;
	}

	public float getDesiredError(){
		return this.desiredError;
	}
	
    public NeuralNetwork(float nu, float desiredError, int epoch, ErrorFunction errorFunction){
        this.nu = nu;
        this.desiredError = desiredError;       
        this.epoch = epoch;
        this.layers = new ArrayList<>();

        if(errorFunction == ErrorFunction.MSE){
            this.errorFunction = new MeanSquaredError();
        }

    }
    
    public void addLayer(Layer layer){
    	if(layer.getClass().getSimpleName().equals(Constants.OUTPUT_LAYER_NAME)){
    		hasOutputLayer = true;
    		layers.add(layer);
    	}
    	else if(!hasOutputLayer){
        	layers.add(layer);
    	}
    }
           
    public void train() {
    	if(!hasOutputLayer){
    		Logger.getInstance().showNoOutputLayerError();
    	}else{
            feedForward();
            calculateError();
            backPropagation();
    	}
    }
    
    public void test() {
        feedForward();
    }
    
    private void feedForward(){
    	
    	int layerCount = layers.size();
    	int firstLayerIndex = 0;
    	
    	for(int i = 0; i < layerCount; i++){
    		
    		Layer currentLayer = layers.get(i);
    		   			
			for(int j = 0; j < currentLayer.getNeuronCount(); j++){
	    		
	    		Neuron currentNeuron = currentLayer.getNeurons().get(j);   
	    		
	    		float totalInput = 0;
	    		
	    		for(int k = 0; k < currentNeuron.getWeightCount(); k++){
	    			if(i == firstLayerIndex){
	    				totalInput += inputs[k] * currentNeuron.getWeight(k);
	    			}
	    			else{
	    				Neuron previousNeuron = layers.get(i-1).getNeurons().get(k);
	    				totalInput += previousNeuron.getActivationOutput() * currentNeuron.getWeight(k);
	    			}
	    		}			
	    		
	    		totalInput += currentNeuron.getBias();
			
	    		currentNeuron.setNeuronOutput(totalInput);
	    		currentNeuron.setActivationOutput(totalInput);
	    		
			}									
    	}   	
    }
      
    private void calculateError() {
        
    	resetGlobalError();
        
    	int outputLayerIndex = layers.size() - 1;
//    
//    	for(int i = 0; i < layers.get(outputLayerIndex).getNeuronCount(); i++){
//    		Neuron neuron = layers.get(outputLayerIndex).getNeurons().get(i);    		
//    		globalError += errorFunction.error(neuron.getActivationOutput(), desiredOutput[i]);
//    	}
//    	
//    	globalError /= (float)layers.get(outputLayerIndex).getNeuronCount();
    	
    	globalError = errorFunction.error(desiredOutput, layers.get(outputLayerIndex).getNeurons());
    	
    }
    
    private void backPropagation() {
    	
    	int outputLayerIndex = layers.size() - 1;
    	int firstLayerIndex = 0;
    	
    	for(int i = outputLayerIndex; i > -1; i--){

    		Layer currentLayer = layers.get(i);
    		
    		for(int j = 0; j < currentLayer.getNeuronCount(); j++){
    			
    			Neuron currentNeuron = currentLayer.getNeurons().get(j);  
    			float delta = 0;
    			
    			if(i == outputLayerIndex){   		
                    float derivativeOfError = errorFunction.derivative(currentNeuron.getActivationOutput(), desiredOutput[j], layers.get(i).getNeuronCount());
                    delta = derivativeOfError * currentNeuron.getActivationDerivative(currentNeuron.getActivationOutput());                
    			}			
    			else{
    				// Previous neuron is output layer for hidden layer!
        			Layer previousLayer = layers.get(i+1);  

    				for(int k = 0; k < previousLayer.getNeuronCount(); k++){
    					Neuron previousNeuron = previousLayer.getNeurons().get(k);
    	        		delta += previousNeuron.getDelta() * previousNeuron.getWeight(j) * 
    	        				currentNeuron.getActivationDerivative(currentNeuron.getActivationOutput());     
    				}
    			}

    			currentNeuron.setDelta(delta);   			
    			
    			if(i == firstLayerIndex){
    				for (int k = 0; k < inputs.length; k++) {
                		float weightDiff = nu * currentNeuron.getDelta() * inputs[k];
                        float biasDiff = nu * currentNeuron.getDelta();
                        currentNeuron.setWeight(k, currentNeuron.getWeight(k) - weightDiff);
                        currentNeuron.setBias(currentNeuron.getBias() - biasDiff);
                    }
    			}
    			else{
    				// Previous neuron is hidden layer for output layer!
    				Layer previousLayer = layers.get(i-1); 
        			for (int k = 0; k < previousLayer.getNeurons().size(); k++) {
        				
        				Neuron previousNeuron = previousLayer.getNeurons().get(k);
        				
                		float weightDiff = nu * currentNeuron.getDelta() * previousNeuron.getActivationOutput();
                        float biasDiff = nu * currentNeuron.getDelta();

                        currentNeuron.setWeight(k, currentNeuron.getWeight(k) - weightDiff);
                        currentNeuron.setBias(currentNeuron.getBias() - biasDiff);
                    }
    			}
		
    		}
    			
    	}   	   
        
    }
    	
    public float getGlobalError() {
        return this.globalError;
    }
    
    public void resetGlobalError() {
        this.globalError = 0;
    }
     
    public boolean hasLearnt() {
        return (globalError < desiredError);
    }

}
