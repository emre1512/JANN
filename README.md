# JANN

JANN is an easy to use artificial neural network (ANN) library implemented in Java. 


## Example Usage

```java
// Load data
List<float[]> traindata = DataLoader.loadData("C:\\JANN\\traindata.txt", Seperator.COMMA);
List<float[]> testdata = DataLoader.loadData("C:\\JANN\\testdata.txt", Seperator.COMMA);
	
// Create neural network
NeuralNetwork nn = new NeuralNetwork(0.1f, 5E-3f, 3000000, new SquaredError());
nn.addLayer(new HiddenLayer(2, ActivationFunction.SIGMOID));
nn.addLayer(new HiddenLayer(3, ActivationFunction.SIGMOID));
nn.addLayer(new OutputLayer(2, ActivationFunction.SIGMOID)); // Neuron count must be same with class count at OutpuLayer!

// Train and test
NetworkController nc = new NetworkController(nn);
nc.showIterations(50000);
nc.trainNetwork(traindata);	
nc.testNetwork(testdata);
```

## Sample Output

======= Training Starts =======
Max Epochs        : 3000000
Max Error         : 0.005
Learning Rate     : 0.1
===============================

===============================
Current iteration :250000
Current error     :0.008027799
===============================

======== Training Ends ========
Epochs : 481179
Error  : 0.004999999
===============================

========= Test Starts =========
Input: |0,00 0,00 | Result: 0 | Real Result: 0
Input: |0,00 1,00 | Result: 1 | Real Result: 1
Input: |1,00 0,00 | Result: 1 | Real Result: 1
Input: |1,00 1,00 | Result: 0 | Real Result: 0

========== Test Ends ==========
Success: %100.0

## Usage

```java
// Load data
List<float[]> traindata = DataLoader.loadData(path, Seperator.COMMA);	// Read data which is seperated by COMMA, SPACE or TAB.
List<float[]> testdata = DataLoader.loadData(path, Seperator.COMMA);

// Create neural network
NeuralNetwork nn = new NeuralNetwork(learningRate, maxError, maxEpoch, new SquaredError());	// Set learning rate, desired max error and epoch count.
nn.addLayer(new HiddenLayer(neuronCount, activationFunction));	// Set neuron count in layer and activation functions of the neurons.
...
...
...
nn.addLayer(new HiddenLayer(neuronCount, activationFunction));
nn.addLayer(new OutputLayer(neuronCount, activationFunction));	// Every network have to have an OutputLayer and the neuron count in OutpuLayer must be same with class count!

// Train and test
NetworkController nc = new NetworkController(nn);
nc.showIterations(iterationLogStepCount);	// Shows the iteration log. Not necessary.
nc.trainNetwork(traindata);	
nc.testNetwork(testdata);
```

## Data

The training data and test data should be in the form of:
```
0.5, 0.7, 2.0, 1
0.5, 0.7, 2.0, 0
0.5, 0.7, 2.0, 2
0.5, 0.7, 2.0, 3
0.5, 0.7, 2.0, 1
0.5, 0.7, 2.0, 0
...
...
...
```

**Note 1: The last column is class label. The class labels should start form "0" and increment as "1, 2, 3, 4, 5, 6..."**
**Note 2: Data columns can be seperated by space or tab too.**

Space/tab seperated data:
```
0.5 0.7 2.0 1
0.5 0.7 2.0 0
0.5 0.7 2.0 2
0.5 0.7 2.0 3
0.5 0.7 2.0 1
0.5 0.7 2.0 0
...
...
...
```

## Contribution

This library is still under development. You can open issues for the bugs you found. Also you can send pull requests for enhancements/bug fixes.

## License

See more at [LICENSE](https://github.com/emre1512/JANN/blob/master/LICENSE) page.