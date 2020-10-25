public class RecursiveSum {
    public static int[] tail(int[] input) {
        // The tail of an array is just every
        // value except the first one, i.e. the head
        // so the length is the input array's length
        // minus 1
        int[] output = new int[input.length - 1];

        // Just set each value of the tail to each
        // corresponding value of the input, accounting
        // for the fact that we're starting from the 1st
        // index
        for (int i = 1; i < input.length; i++)
            output[i - 1] = input[i];
        
        return output;
    }

    public static int sum(int[] input) {
        // The sum of an array of length 1
        // is just its first element. This
        // is our base case.
        if (input.length == 1)
            return input[0];

        // In any other case, the sum can
        // be expressed as the first value
        // of the array + the sum of the rest
        // of its values, i.e. the sum of
        // its tail
        return input[0] + sum(tail(input));
    }

    public static void main(String[] args) {
        int[] intArgs = new int[args.length];

        for (int i = 0; i < args.length; i++)
            intArgs[i] = Integer.parseInt(args[i]);

        System.out.println(sum(intArgs));
    }
}
