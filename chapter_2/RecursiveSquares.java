public class RecursiveSquares {
    public static void drawSquares(int n, double x, double y, double r) {
        // Order 0 implies no squares are drawn
        // so we can just quit if n == 0.
        // This is our base case.
        if (n == 0)
            return;

        // Draw a gray filled square with the current
        // parameters, then draw a black outline outside of it.
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledSquare(x, y, r);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(x, y, r);

        /* Each recursive square is a single order less than the current
         * square's order, so they all get n - 1 as their n value.
         *
         * The size ratio between squares is 2.2:1, so each recursive square's
         * half-length is just the current half-length divided by 2.2.
         *
         * The recursive squares differ in that ...
         * - 1 of them is shifted to the left   and up by "r" i.e. the half-length of the current square
         * - 1 of them is shifted to the right  and up by r
         * - 1 of them is shifted to the left   and down by r
         * - 1 of them is shifted to the right  and down by r
         */
        drawSquares(n - 1, x - r, y + r, r / 2.2);
        drawSquares(n - 1, x + r, y + r, r / 2.2);
        drawSquares(n - 1, x - r, y - r, r / 2.2);
        drawSquares(n - 1, x + r, y - r, r / 2.2);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // The default canvas is 1x1, so 0.5, 0.5 is right
        // in the center. 0.25 is a reasonable half-length to start with.
        drawSquares(n, 0.5, 0.5, 0.25);
    }
}
