//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Merge {


        // This class should not be instantiated.
        private Merge() { }

        /**
         * Merges two sorted subarrays a[lo..mid] and a[mid+1..hi] into a single sorted array.
         * @param a the array to be sorted
         * @param aux auxiliary array used for merging
         * @param lo the starting index of the left subarray
         * @param mid the middle index dividing the two subarrays
         * @param hi the ending index of the right subarray
         */
        private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            // Precondition: a[lo..mid] and a[mid+1..hi] are sorted subarrays
            assert isSorted(a, lo, mid);   // Check if the left subarray is sorted
            assert isSorted(a, mid + 1, hi); // Check if the right subarray is sorted

            // Copy elements to the auxiliary array
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];  // Copy current element from a[] to aux[]
            }

            // Merge the two sorted subarrays back into a[]
            int i = lo, j = mid + 1;  // i is the pointer for the left subarray, j for the right subarray
            for (int k = lo; k <= hi; k++) {  // k is the pointer for the merged subarray
                if (i > mid) {  // If we've exhausted the left subarray
                    a[k] = aux[j++];  // Take elements from the right subarray
                } else if (j > hi) {  // If we've exhausted the right subarray
                    a[k] = aux[i++];  // Take elements from the left subarray
                } else if (less(aux[j], aux[i])) {  // If the element in the right subarray is smaller
                    a[k] = aux[j++];  // Take the element from the right subarray
                } else {  // Otherwise, take the element from the left subarray
                    a[k] = aux[i++];
                }
            }

            // Postcondition: a[lo..hi] is now sorted
            assert isSorted(a, lo, hi);  // Ensure the merged subarray is sorted
        }

        /**
         * Sorts the array a[lo..hi] using an auxiliary array aux[lo..hi].
         * @param a the array to be sorted
         * @param aux the auxiliary array
         * @param lo the starting index of the array to be sorted
         * @param hi the ending index of the array to be sorted
         */
        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            if (hi <= lo) return;  // Base case: if the subarray has 1 or fewer elements, it's already sorted
            int mid = lo + (hi - lo) / 2;  // Find the midpoint

            // Recursively sort the left half
            sort(a, aux, lo, mid);

            // Recursively sort the right half
            sort(a, aux, mid + 1, hi);

            // Merge the sorted halves
            merge(a, aux, lo, mid, hi);
        }

        /**
         * Helper method to check if the array a[lo..hi] is sorted.
         * @param a the array to be checked
         * @param lo the starting index of the range to be checked
         * @param hi the ending index of the range to be checked
         * @return true if a[lo..hi] is sorted, false otherwise
         */
        private static boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++) {
                if (less(a[i], a[i - 1])) {  // If an element is smaller than its previous element
                    return false;  // The array is not sorted
                }
            }
            return true;  // The array is sorted
        }

        /**
         * Helper method to compare two elements.
         * @param v the first element
         * @param w the second element
         * @return true if v < w, false otherwise
         */
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;  // Compare using the natural ordering of the elements
        }

        /**
         * Sorts the entire array a using merge sort.
         * @param a the array to be sorted
         */
        public static void sort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];  // Create an auxiliary array
            sort(a, aux, 0, a.length - 1);  // Call the recursive sort function
        }
    // Helper method to print the array
    private static void printArray(Comparable[] array) {
        for (Comparable element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
            // Create Array of Comparable elements:
            Integer[] array = {5,2,9,1,5,6};
        // Print the original array
        System.out.println("Original array:");
        printArray(array);

        // Sort the array using the Merge class
        Merge.sort(array);

        // Print the sorted array
        System.out.println("Sorted array:");
        printArray(array);


    }
}
