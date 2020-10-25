public class EvenNumbers {
    public static int[] intArgs(String[] args) {
        // Initialize an integer array to the size of
        // the input array
        int[] returnArray = new int[args.length];

        // Loop through args and set every value of
        // returnArray to the integer value of the corresponding
        // arg value
        for (int i = 0; i < args.length; i++)
            // Note that this will fail if args
            // contains any non-integer values
            returnArray[i] = Integer.parseInt(args[i]);

        return returnArray;
    }

    public static boolean all(boolean[] input) {
        // For each boolean in input ...
        for (int i = 0; i < input.length; i++)
            // If a single input is false ...
            if (!input[i])
                return false;
        // If the entire for loop runs without returning false,
        // we know that this boolean array only contains true
        return true;
    }

    public static boolean any(boolean[] input) {
        // For each boolean in input ...
        for (int i = 0; i < input.length; i++)
            // If a single input is true ...
            if (input[i])
                return true;

        // If the entire for loop runs without returning true,
        // we know that not a single value in this boolean array
        // contains true
        return false;
    }

    public static int[] findEvens(int[] numbers) {
        // We don't know (yet) how many even numbers
        // are in the numbers array, so make a size
        // variable to calculate then store it,
        // and a temporary array to store them to
        // later transfer to a properly sized array
        int size = 0;
        int[] tmp = new int[numbers.length];

        // For each int in numbers ...
        for (int i = 0; i < numbers.length; i++) {
            // If the current number is divisible by 2 ...
            if (numbers[i] % 2 == 0) {
                // Add it to the temporary array* ...
                tmp[size] = numbers[i];
                // ... and increment size
                size++;
            }
        }

        /* 
         * Why do we do tmp[size] = numbers[i] instead of
         * tmp[i] = numbers[i]?
         *
         * Let's say, for example,
         * that numbers is an array equal to {1, 2, 3, 4, 5, 6}.
         * If we did tmp[i] = numbers[i], tmp would look like this:
         * 
         * tmp -> {0, 2, 0, 4, 0, 6}
         * 
         * But we want tmp to look like:
         *
         * tmp -> {2, 4, 6, 0, 0, 0}
         * 
         * so that we don't have to loop through the whole array
         * again later.
         *
         * Since size starts at 0 and increments
         * once every time an even number is found, it can also
         * work as an appropriate current index for tmp.
         */

        // Use the calculated size to make a new array of proper
        // length
        int[] evenNumbers = new int[size];

        // For each int in evenNumbers ...
        for (int i = 0; i < evenNumbers.length; i++)
            // Set the current evenNumber to the corresponding
            // value in the temporary array
            evenNumbers[i] = tmp[i];

        return evenNumbers;
    }

    public static boolean[] findEvensB(int[] numbers) {
        // Create a boolean array the same size as numbers
        boolean[] convertedValues = new boolean[numbers.length];

        // For each int in numbers ...
        for (int i = 0; i < numbers.length; i++)
            // Set the corresponding boolean value in convertedValues
            // to whether or not the current number is divisible by 2
            convertedValues[i] = numbers[i] % 2 == 0;

        return convertedValues;
    }

    public static void main(String[] args) {
        // Create an array of integer values
        // corresponding to the command-line arguments
        int[] integerArguments = intArgs(args);

        // Create an array of booleans where the value is
        // true if the corresponding value in integerArguments
        // is even, and false if it's odd
        boolean[] evenBooleans = findEvensB(integerArguments);

        // Create an int array only containing the even numbers in
        // integerArguments
        int[] evens = findEvens(integerArguments);

        /* Do you know how printf works? The little %b is what's called a
         * "format specifier". When the string is printed out, the %b will
         * be replaced by a boolean value that's passed to printf as an
         * argument. The \n is an "escape code", indicating that a new
         * line should be printed. System.out.println includes these at
         * the end of your input by default. The \n is read "newline".
         */

        System.out.printf("At least 1 of the inputs was even: %b\n", any(evenBooleans));
        System.out.printf("All of the inputs were even: %b\n", all(evenBooleans));

        System.out.println("The following inputs were even numbers");
        for (int i = 0; i < evens.length; i++)
            System.out.println(evens[i]);
    }
}
