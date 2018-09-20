package util;

public class ResultQuantizer {

	public static String quantizeResult(float result) {
        return Integer.toString(Math.round(result)) + " (" + Float.toString(result) + ")";
    }
	
}
