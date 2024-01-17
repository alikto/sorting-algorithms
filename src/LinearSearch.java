import java.util.ArrayList;

public class LinearSearch {
    public int linearSearch(ArrayList<Integer> arr, int x){
        int size = arr.size();
        for (int i = 0;i<size;i++){
            if (arr.get(i) == x){
                return i;
            }
        }
        return -1;
    }
}
