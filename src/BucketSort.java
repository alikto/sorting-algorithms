import java.util.*;


public class BucketSort {
    public int hash(int i, int max, int numberOfBuckets){
        return (int) ((double) i / max * (numberOfBuckets-1));
    }
    public ArrayList<Integer> bucketSort(ArrayList<Integer> arr){
        int numberOfBuckets = (int)Math.sqrt(arr.size());
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(numberOfBuckets);

        for (int i = 0; i<numberOfBuckets; i++){
            buckets.add(new ArrayList<>());
        }

        int max = Collections.max(arr);

        for (int i: arr){
            buckets.get(hash(i,max,numberOfBuckets)).add(i);
        }

        Comparator<Integer> comparator = Comparator.naturalOrder();
        for (ArrayList<Integer> bucket : buckets){
            bucket.sort(comparator);
        }

        ArrayList<Integer> sortedArray = new ArrayList<>();
        for (ArrayList<Integer> bucket : buckets){
            sortedArray.addAll(bucket);
        }

        return sortedArray;

    }
}
