import java.util.*;
public class CommonAPT8 {
    public String[] sort(String[] arr){
        Arrays.sort(arr, (String a, String b) -> { //lambda expression, The body of it is what u would out in your comparator?
        		return a.compareTo(b);
        });
        return arr;
    }
}
