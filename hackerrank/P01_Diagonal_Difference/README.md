# Diagonal Difference

The problem description can be found [here](https://www.hackerrank.com/challenges/diagonal-difference/problem).

To quickly summarise it, the task at hand is to take a _square matrix_ called `arr`, and find the **sums** of its
**diagonals**. Before jumping into a solution, let's talk a little bit about terminology.

## Terminology and Context

The word "matrix" comes from mathematics, but for our purposes a _matrix_ is just a 2D array of numbers.
A _square_ matrix is a 2D array of numbers where the number of rows is equal to the number of columns, and
so when you print out all the elements of the 2D array in a table-like format, you'll find that they form
something resembling a square. The _diagonals_ of a square matrix are, rather intuitively, the numbers occupying
the corners, as well as the centre of a square matrix. One diagonal consists of the top left, centre, and bottom right,
while the other consists of the top right, centre, and bottom left. In the case of the example given to us ...

```
1 2 3
4 5 6
9 8 9
```

The diagonals are `[1, 5, 9]` and `[3, 5, 9]`. Though, it should be noted that the order of the diagonals does not
matter.

## Solution

Our problem can be split into a handful of sub-problems:

1. Find the diagonals of the square matrix `int arr[n][m]`
1. Sum the diagonals
1. Find the _absolute value_ of the _difference_ of the two sums

Let's start by reading our square matrix into a 2D array from Standard Input in Java

```java
import java.util.*;

public class DiagonalDifference {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);

        // The first integer we receive from stdin is
        // the size of the square matrix. Since a square
        // matrix has the same amount of rows and columns
        // we can describe its size with a single integer n
        int n = scan.nextInt();

        int[][] arr = new int[n][n];

        // We use a nested for loop to fill
        // the entire 2D array with the values
        // from stdin
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = scan.nextInt();

        // ...
    }
}
```

Now that we have a 2D array called `arr` to work with, we can focus on writing the code that
will solve the problem.

### Finding Diagonals

The first listed sub-problem is finding the diagonals of the 2D array `arr`. We don't necessarily
need to _store_ the diagonals separately in their own 1D arrays. We just need to know how to find
them so that we can iterate (in other words, loop) through the 2D array and add each diagonal value
to our diagonal _sums_, since the sums are all we need to solve the problem.
Let's first set this up by creating two integer values to store our diagonal sums,
inserting the following after the comment `// …` in our previous code snippet.

```java
        // ...

        int diagonalSum1 = 0;
        int diagonalSum2 = 0;
    }
}
```

We're going to be looping through the 2D array to find the values that belong to either diagonal and then
add them to the corresponding `diagonalSum` variable. But how will we know if the value we're currently
at belongs to either diagonal? Sometimes looking at a 2D array in terms of its indices (plural of index)
can help us see patterns that will in turn allow us to solve problems. Let's look at our square matrix
from before in terms of its _indices_.

```
[0][0] [0][1] [0][2]
[1][0] [1][1] [1][2]
[2][0] [2][1] [2][2]
```

You might notice the following pattern in the indices of the left-to-right diagonal: for every value
in that diagonal, the indices are _the same_. For the first value of that diagonal, the indices are `[0][0]`,
for the second, the indices are `[1][1]`, and for the third, the indices are `[2][2]`. When we're looping
through the 2D array, we can express this in more _general_ terms by saying that when a value is part of
the left-to-right diagonal, `i == j`.

We can add the following bit of code to our solution to test out this idea:

```java
        // Right after we create our two integer variables

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    System.out.println(arr[i][j]);
            }
        }
    }
}
```

If we create an input file `input.txt` with the following content:

```
3
11 2  4
4  5  6
10 8 -12
```

And then run our program with `java DiagonalDifference < input.txt`, we
should get the following output:

```
11
5
-12
```

This is, in fact, our left-to-right diagonal, which means we are doing something right!
However, it is clear that this pattern only applies to the left-to-right diagonal. We will
need to think a little harder to find a pattern that works for the right-to-left diagonal.

One intuitive though inefficient approach to finding the right-to-left diagonal is to simply
flip the 2D array around and then apply the same pattern we used to find the left-to-right
diagonal from before. Let's give that a try _before_ trying to explore a more efficient solution.

A separate guide to understanding array reversal is available [here](./array_reverse.md). I
highly advise reading it if you're not sure how to reverse an array already! The algorithm
is not necessarily intuitive, and there is no reason to feel like it is your responsibility
to come up with it all by yourself. Learning tricks and algorithms as well as where to apply
them is your job. You do _not_ need to design them from scratch all the time.

Let's extend our solution by adding another nested loop, in which we apply the array reversal
algorithm to each individual row of the 2D array. Then we will print out the diagonal again
in the same way we did before.

```java
        // Right after the code where we print out the left-to-right
        // diagonal


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                // Remember:
                // arr[i] is the array
                // arr[j] is the jth index of the array
                int tmp = arr[i][j];
                arr[i][j] = arr[i][n - j - 1];
                arr[i][n - j - 1] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    System.out.println(arr[i][j]);
            }
        }
    }
}
```

We apply the array reversal algorithm to every row of `arr` as explained previously,
and then we rewrite the exact same nested for loop for printing out the left-to-right
diagonal which we used before. If we do `java DiagonalDifference < input.txt` again,
we will see the following output:

```
11
5
-12
4
5
10
```

The first three numbers are in fact the left-to-right diagonal from before, while the last
three numbers are the right-to-left diagonal, which means our array reversal method worked!
Technically, now that we have both 2D array diagonals, we can move on to the step where
we add them to their respective sums and then find the absolute differences of the sums.
However, there is a much more efficient way to go about this that applies a similar
method to the array reversal method that avoids too many nested loops (which can make
your program really, really slow with large arrays).

In our solution right now, we swap the `j`th element of `arr[i]` with the `n - j - 1`th element
to reverse the array `arr[i]`. In other words, we swap the `j`th element with the element that
is `j` indices away from the final element of `arr[i]`. Let's look at the 2D array in terms of
its indices again like we did before:

```
[0][0] [0][1] [0][2]
[1][0] [1][1] [1][2]
[2][0] [2][1] [2][2]
```

Focusing on the right-to-left diagonal, which consists of the following pairs of indices:

```
i = 0, j = 2
i = 1, j = 1
i = 2, j = 0
```

When we perform our array reversal, we take these elements and perform the following:

* We swap `j = 0` with `j = n - j - 1`, i.e. `j = 2`
* We swap `j = 1` with `j = n - j - 1`, i.e. `j = 1`
* We swap `j = 2` with `j = n - j - 1`, i.e. `j = 0`

Notice that for each element of `j` in the right-to-left diagonal that we're swapping
with `n - j - 1`, the number `n - j - 1` is actually equal to the corresponding `i`
value. E.g.

* For `j = 2`, `n - j - 1 = 3 - 2 - 1 = 0`, and for `j = 2`, `i = 0`
* For `j = 1`, `n - j - 1 = 3 - 1 - 1 = 1`, and for `j = 1`, `i = 1`
* For `j = 0`, `n - j - 1 = 3 - 0 - 1 = 2`, and for `j = 0`, `i = 2`

So instead of fully reversing every array `arr[i]`, we can just check
if `i == n - j - 1` to find out if the current value is part of the right-to-left
diagonal!

This is a difficult pattern to notice, but nevertheless, it is there! You should
not feel as though you have failed if you were unable to see it yourself. Even _I_
didn't see it myself. It took me a lot of time reading and trying to understand the
solution on Hackerrank to understand why this works (as my solution worked differently,
albeit less efficiently). It is also the case that I did not understand the array reversal
algorithm before looking it up and spending some time reading it and tracing through it.
But as I've mentioned before, finding, practicing and internalising tricks, patterns and
algorithms such as these will only help you and I grow!

Let's refactor our solution to work in this new pattern:

```java
        // Starting right before we declared the diagonalSum variables

        int diagonalSum1 = 0;
        int diagonalSum2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    System.out.println("Left-to-right: " + arr[i][j]);
                if (i == n - j - 1)
                    System.out.println("Right-to-left: " + arr[i][j]);
            }
        }
    }
}
```

We add in some labelling so as not to confuse ourselves. We should be able to verify with
`java DiagonalDifference < input.txt` that we are getting the correct diagonals shown to us.

### Summing and Finding Absolute Difference

Summing the diagonals and finding their absolute difference is trivial
in comparison to the task of finding the diagonals themselves. We have already
created two integer variables called `diagonalSum1` and `diagonalSum2` to store
the left-to-right and right-to-left diagonal sums respectively. We also already have
two if statements in our solution that tell us whether or not the current value we're
at in the 2D array is part of either diagonal. We simply need to replace the print
statements with code that will add the current value to the sum variable it belongs to,
like this:

```java
        // Right before we create the sum variables again
        int diagonalSum1 = 0;
        int diagonalSum2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    diagonalSum1 += arr[i][j];
                if (i == n - j - 1)
                    diagonalSum2 += arr[i][j];
            }
        }
    }
}
```

Now our two `diagonalSum` variables will each contain the sum of each diagonal. Notice that
we use two `if`s instead of an `if` and `else if`. This distinction is important. Had we used
an `else if (i == n - j - 1)` instead of another `if`, the centre value of the 2D array would
only get added to `diagonalSum1` and not `diagonalSum2`. When we use two `if`s in a row, _both_
of them will run if both of their conditions are true. Meanwhile, if we use an `if` followed by
an `else if`, only the first one whose condition is true will be run.

Finding the absolute difference of the two sums is not difficult at all. We can simply add the following
line of code after our for loop:

```java
System.out.println(Math.abs(diagonalSum1 - diagonalSum2));
```

And then we will have solved the problem! View the full solution [here](./DiagonalDifference.java).
