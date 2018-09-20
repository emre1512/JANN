package util;

public class ArrayToClassLabel {

	public static int convert(float[] result){
		
		float[] resultDummy = result;
		
		for(int i = 1; i < result.length; i++){
			resultDummy[i] = ResultQuantizer.quantizeResult(result[i]);
		}
		
		int total = 0;
		for(int i = 0; i < result.length; i++){
			if (result[i] == 1) total++;
		}
		
		if(total != 1){
			float biggest = result[0];
			int biggestIndex = 0;
			
			for(int i = 1; i < result.length; i++){
				if(result[i] > biggest){
					biggest = result[i];
					biggestIndex = i;
				}			
			}
			
			return biggestIndex;
		}
		else{
			int index = 0;
			for(int i = 0; i < result.length; i++){
				if(((int)result[i]) == 1){
					index = i;
				}
			}
			
			return index;
		}
		
			
	}
	
}
