import java.util.ArrayList;

public class BinarySearch {
    public int binarySearch(ArrayList<Integer> arr, int x){
        int low = 0;
        int high = arr.size()-1;
        while ((high-low)>1){
            int mid = (high+low)/2;
            if (arr.get(mid)<x){
                low=mid+1;
            }else {
                high = mid;
            }
        }

        if (arr.get(low)==x){
            return low;
        } else if (arr.get(high)==x) {
            return high;
        }
        return -1;
    }
}
