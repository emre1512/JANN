package util;

public class SuccessCalculator {
	
	private static int correctResults;
	
	public static void addResult(int predicted, int real){
		if(predicted == real){
			correctResults++;
		}
	}
	
	public static float getSuccessRate(int totalTestData){
		return correctResults / (float)totalTestData * 100.0f;
	}

}
