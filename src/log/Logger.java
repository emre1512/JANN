package log;

public class Logger implements LogMessages{

	private static Logger instance;
	
	public static Logger getInstance(){
		if(instance == null){
			instance = new Logger();
			return instance;
		}else{
			return instance;
		}
	}
	
	@Override
	public void showNoOutputLayerError() {
		System.out.println("No output layer! Please add an output layer.");
		System.out.println();
	}

//	@Override
//	public void showInitMessage(int hiddenLayerCount, int classCount, float learningRate) {
//		System.out.println("Network has been initiated with:" + hiddenLayerCount + " hidden layers, " +
//							);
//		System.out.println("                                ");
//		
//	}

	@Override
	public void showTrainingStartMessage(int epoch, float maxError, float learningRate) {
		System.out.println("======= Training Starts =======");
		System.out.println("Max Epochs        : " + epoch);
		System.out.println("Max Error         : " + maxError);
		System.out.println("Learning Rate     : " + learningRate);
		System.out.println("===============================");	
	}

	@Override
	public void showTrainingEndMessage(int iteration, float error) {
		System.out.println();
		System.out.println("======== Training Ends ========");
		System.out.println("Epochs : " + iteration);
		System.out.println("Error  : " + error);
		System.out.println("===============================");
		System.out.println();	
	}

	@Override
	public void showTestStartMessage() {
		System.out.println("========= Test Starts =========");	
	}
	
	@Override
	public void showTestCaseResult(float[] input, float predictedClass, float desiredOutput) {
		System.out.print("Input: |");
		for(int i = 0; i < input.length; i++){			
			System.out.printf("%.2f", input[i]);
			System.out.print(" ");
		}
		
		System.out.print("| Result: ");
		System.out.print((int)predictedClass + " |");
		System.out.print(" Real Result: " + (int)desiredOutput);
		System.out.println();
	}

	@Override
	public void showTestEndMessage(float success) {
		System.out.println();
		System.out.println("========== Test Ends ==========");
		System.out.println("Success: %" + success);
	}

	@Override
	public void showIterationStats(int iteration, float error) {
		System.out.println();
		System.out.println("===============================");
        System.out.println("Current iteration :" + iteration);
        System.out.println("Current error     :" + error);
		System.out.println("===============================");
	}


}
