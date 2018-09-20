package util;

public class ClassLabelToArray {

	public static float[] convert(int label, int classCount){
		float[] array = new float[classCount];
		for(int i = 0; i < classCount; i++){
			array[i] = 0;
		}
		
		array[label] = 1;
		
		return array;
	}
	
}
