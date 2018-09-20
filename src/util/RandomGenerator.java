package util;

import java.util.Random;

public class RandomGenerator {

	private static Random random = new Random();
	
	public static float generateRandom(int min, int max){
		return (float)(random.nextInt((max) + min));
	}
	
}
