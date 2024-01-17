import java.util.ArrayList;

public class SelectionSort {
    public ArrayList<Integer> selectionSort(ArrayList<Integer> arr, int n){
        for (int i = 1; i < n-1;i++){
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (arr.get(j) < arr.get(i)){
                    min = j;
                }
            }
            if (min!=i){
                int temp = arr.get(min);
                arr.set(min, arr.get(i));
                arr.set(i, temp);
            }
        }
        return arr;
    }
}
