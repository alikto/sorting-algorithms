import java.util.ArrayList;

public class QuickSort {
    public ArrayList<Integer> quickSort(ArrayList<Integer> arr, int low, int high) {
        int stackSize = high - low + 1;
        int[] stack = new int[stackSize];

        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];
            int pivot = partition(arr, low, high);

            if ((pivot - 1) > low) {
                stack[++top] = low;
                stack[++top] = pivot - 1;
            }

            if ((pivot + 1) < high) {
                stack[++top] = pivot + 1;
                stack[++top] = high;
            }

        }
        return arr;
    }

    public int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr.get(j) <= pivot) {
                i++;

                Integer temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        Integer tempH = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, tempH);

        return i + 1;
    }

}
