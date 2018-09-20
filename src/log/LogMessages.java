package log;

public interface LogMessages {

	public void showNoOutputLayerError();
//	public void showInitMessage();
	public void showTrainingStartMessage(int epoch, float maxError, float learningRate);
	public void showTrainingEndMessage(int iteration, float error);
	public void showTestStartMessage();
	public void showTestCaseResult(float[] input, float predictedClass, float desiredOutput);
	public void showTestEndMessage(float success);
	public void showIterationStats(int iteration, float error);
	
	
}
