public class ArrayReverse {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};

        // Creating a variable n to store
        // the length of the array, only
        // for the sake of clarity
        int n = array.length;

        // Why n/2? We're performing a swap across the
        // whole array, and we only need to swap the
        // first half of the array with the second half
        // of the array. For example, 5/2 (because of integer
        // division) is 2, and we only need to exchange
        // array[0] through array[2] with array[2] through array[4]
        // to fully reverse the array
        for (int i = 0; i < n/2; i++) {
            // Temporarily store the ith element
            // of the array before swapping it
            // with the n - i - 1th element
            int tmp = array[i];

            // Set the ith element equal to the element
            // that is i indices away from the last element.
            // E.g. when i = 0, set array[0] = array[4]
            // when i = 1, set array[1] = array[3]
            array[i] = array[n - i - 1];

            // Complete the swap by setting the
            // n - i - 1th element equal to the old
            // value of the ith element.
            // E.g. when i = 0, array[4] = the old array[0]
            array[n - i - 1] = tmp;
        }

        // Code snippet to print out the elements of
        // the newly reversed array in order
        for (int i = 0; i < n; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}
