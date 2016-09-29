import java.util.*;

public class BSTcount {
	public long howMany(long[] values){
		

		return calculator(values.length);
	}
	public long calculator(int N){
		if (N==0)return 1;
		if(N==1)return 1;
		
		
		long totalVal = 0;
		for(int k=0; k<N; k++){
			long left = calculator(k);
			long right = calculator(N-k-1);
			totalVal += left*right;
		}
		
		
		return totalVal;
	}
//	public static void main(String[] args){
//		long[] test = {10};
//		System.out.println(howMany(test));
//	}
}
