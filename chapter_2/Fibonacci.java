public class Fibonacci {
    public static int factorial(int n) {
        // The factorial of 1 is 1, and there's no reason for us
        // to take the factorial of a number less than 1,
        // so this can be our base case.
        if (n == 1)
            return 1;

        return n * factorial(n - 1);
    }


    public static long fib(int n) {
        // The factorial of 0 and 1 are
        // 0 and 1, so we can just return
        // n if n is either of those.
        //
        // This is our base case.
        if (n == 0 || n == 1)
            return n;

        // Otherwise return the sum
        // of the last two fibonacci
        // numbers
        return fib(n - 1) + fib(n - 2);
    }

    // Create an array of longs to cache
    // the results of calling fib
    private static long[] fibCache;
    
    public static long dynamicFib(int n) {
        if (n == 0 || n == 1)
            return n;

        /* Long arrays are initialized such that
         * all values are 0L by default. Therefore,
         * if a value of fibCache is 0L, we know
         * that that value has not yet been calculated.
         * We put L after a long literal to denote that
         * it is a long and not an int.
         */
        if (fibCache[n] != 0L)
            return fibCache[n];

        /* Here we use the "ternary" operator to set fib1 and fib2 to either the
         * cached value of dynamicFib(n - 1) and dynamicFib(n - 2) if either exists,
         * or otherwise the result of calling dynamicFib(n - 1) and dynamicFib(n - 2)
         *
         * Ternary expressions can be read, for example, like this:
         * "Is fibCache[n - 1] not equal to 0L?
         *  If so, evaluate to fibCache[n - 1].
         *  If not, evaluate to dynamicFib(n - 1)."
         *
         *  The syntax is `boolean_expression ? value_if_true : value_if_false`
         */
        long fib1 = fibCache[n - 1] != 0L ? fibCache[n - 1] : dynamicFib(n - 1);
        long fib2 = fibCache[n - 2] != 0L ? fibCache[n - 2] : dynamicFib(n - 2);

        // Store the sum of the last 2 fib numbers
        // as the nth value of fibCache
        fibCache[n] = fib1 + fib2;

        return fibCache[n];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // Our cache only needs to store n fibonacci
        // numbers, but since we access the nth number
        // with the index n, the 0th index is never used.
        // Therefore, we set our size equal to n + 1
        fibCache = new long[n + 1];

        // Initialize our stopwatch
        Stopwatch fibTimer = new Stopwatch();

        // Calculate the nth fibonacci number with our
        // dynamic-programming implementation of the function
        System.out.println(dynamicFib(n));
        // Get the amount of time that's passed since we initialized
        // the stopwatch
        double elapsedTime = fibTimer.elapsedTime();
        // Print out the amount of time it took to calculate the nth fibonacci number with
        // the dynamic programming implementation
        System.out.printf("Elapsed time for dynamic-programming implementation: %f\n", elapsedTime);

        System.out.println(fib(n));
        // Remove the amount of time it took to calculate the nth fibonacci
        // number with the other implementation from the current elapsed time
        // to get the amount of time it took to calculate that number with the
        // default implementation
        elapsedTime = fibTimer.elapsedTime() - elapsedTime;
        System.out.printf("Elapsed time for default implementation: %f\n", elapsedTime);
    }
}
