import java.util.Arrays;
import java.util.Collections;

public class QuickSort {
    private QuickSort() {}
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Collections StdRandom;
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return; // Base Case: subarray has 0 or 1 elements no
        System.out.println("Recursively sorting Subarray: " + Arrays.toString(a));
        int j = partition(a, lo, hi); // Partition the array to get pivot index
        System.out.println("Getting Pivot: " + j);
        sort(a, lo, j-1); // recursivley sort left subarray (elements smaller than pivot)
       // System.out.println("sort( " + Arrays.toString(a) + ", " + lo + ", " + j + ")");
        sort(a, j+1, hi); // recursivley sort right subarray (elements greater than pivot)
       // System.out.println("sort( " + Arrays.toString(a) + ", " + (j+1) + ", " + hi + ")");
        assert isSorted(a, lo, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo; // i points to first element in subarray 
        int j = hi + 1; // j points to element after last element in subarray
        Comparable v = a[lo]; //pivot
        System.out.println("Partitioning around Pivot: " + v);
        while (true) {

            // find item on lo to swap
            // Find an item on the left (from index i) that is greater than pivot
            while (less(a[++i], v)) {
                System.out.println("is " + a[i] + " < " + v);
                if (i == hi) break; // Break if i reaches the end of the array
            }

            // find item on hi to swap
            // find an item on the right (from index j) that is less than pivot
            while (less(v, a[--j])) {
                System.out.println("is " + v + " < " + a[j] );
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross (if they do partitioning is done)
            if (i >= j) break;

            System.out.println("Swapping: " + a[i] + " and " + a[j]);
            exch(a, i, j); // swap elements at i and j to move them to their correct side of the pivot
        }
        System.out.println("Putting pivot " + v + " in its correct position at index " + j);
        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j; // return index of pivot
    }

    /**
     * Rearranges the array so that {@code a[k]} contains the kth smallest key;
     * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]}; and
     * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
     *
     * @param  a the array
     * @param  k the rank of the key
     * @return the key of rank {@code k}
     * @throws IllegalArgumentException unless {@code 0 <= k < a.length}
     */
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        //StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }



    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            //StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] array = {"a","f","e","d","c","l"};
        System.out.println("Original array: " + Arrays.toString(array));
        // Sort the array
        QuickSort.sort(array);
        // Show Sorted Array:
        // Show sorted array
        System.out.println("Sorted array:");
        for (String s : array) {
            System.out.print(s);
        }
        /*
        //String[] a = StdIn.readAllStrings();
        QuickSort.sort(a);
        show(a);
        assert isSorted(a);

        // shuffle
        StdRandom.shuffle(a);

        // display results again using select
        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a, i);
            StdOut.println(ith);
        }
    }
    */


}
}
