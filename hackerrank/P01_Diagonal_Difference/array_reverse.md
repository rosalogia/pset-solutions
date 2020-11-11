# Array Reversal

How do you flip a 2D array? Just reverse every array in the 2D array! How do you reverse an array?
Let's look at the following program called `ArrayReverse`. Note that a more heavily commented
version of this program is available [here](./ArrayReverse.java):

```java
public class ArrayReverse {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};

        int n = array.length;

        for (int i = 0; i < n/2; i++) {
            int tmp = array[i];

            array[i] = array[n - i - 1];

            array[n - i - 1] = tmp;
        }

        // Code snippet to print out the elements of
        // the newly reversed array in order
        for (int i = 0; i < n; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}
```

Read the code, test it out, and think about it for a little while. Why does this work?
You didn't need to know or understand this ahead of time, and you don't need to expect
yourself to have come up with it on the spot. Once you learn and internalise ideas and
algorithms like this, you'll be more equipped to quickly solve problems in the future.

Given an array of length _n_, we perform a loop over that array that runs only `n/2` times.
Why is that? We need to understand what the loop is doing in order to understand why it only
takes `n/2` iterations to completely reverse the array. In our loop, we are essentially
_swapping_ every `i`th element of the array with the element of the array that is `i`
indices away from the final element. That's a confusing sentence, so let's think about it
through examples.

## Stepping Through the Loop

When `i = 0`, we are swapping the `0`th value of the array with the value that is `0` indices 
away from the `4`th value of the array, which is also the _final_ value of the array. In other
words, `array[0]` gets swapped with `array[4]`, giving us an array like this: `5 2 3 4 1`.

We are clearly closer to getting our fully reversed array, let's move on to the next iteration.

When `i = 1`, we swap the `1`st value of the array with the value that is `1` index away
from the `4`th (final) value of the array. `4 - 1 = 3`, so that means we're swapping the
`1`st index of the array with the `3`rd index of the array. `array[1]` gets swapped with
`array[3]`, giving us the following array: `5 4 3 2 1`.

Since our array has an odd number of values, we are already done even though there is one
iteration left to go. However, if we had an even number of values, such as 6, 2 iterations
would not be enough to completely reverse the array. Our method works for both odd and even
length arrays, but in the case that the array has an odd length, the final iteration will do
the following:

When `i = 2`, swap the `2`nd value of the array with the value that is `2` indices away
from the `4`th value of the array. `4 - 2 = 2`, so we swap the `2`nd index with the `2`nd index.
In other words, nothing changes at all, and we are still left with `5 4 3 2 1`.

Hopefully it is now more clear why the loop only runs while `i < n/2` and not while `i < n`.

## Inside the Loop Body

Within the body of the loop that reverses the array, we have the following code:

```java
// ...

int tmp = array[i];

array[i] = array[n - i - 1];

array[n - i - 1] = tmp;

// ...
```

When swapping two values in an array, a common pattern is to create a temporary variable called `tmp` to store
the value of the first element you're modifying. In our case, that element is `array[i]`. This is because
eventually, we want to set `array[n - i - 1]` to the value of `array[i]`, but if we change
`array[i]` to `array[n - i - 1]` before we do that, we would be setting `array[n - i - 1]` equal to itself!

We use the `tmp` variable to make sure this doesn't occur. It might help you understand how and why this happens
if you remove the `tmp` variable and simply write …

```java
array[i] = array[n - i - 1];
array[n - i - 1] = array[i];
```

… and see what happens!

Why `n - i - 1`? `n` is the length of the array, and what we want to swap the `i`th element with is the element
that is exactly `i` indices away from the _final_ element of the array. Remember that since arrays are indexed
starting at 0, `n` is not a valid array index. Instead, the final element of the array is at index `n - 1`. Therefore,
the index that is `i` indices away from the final element of the array is the `n - 1`th element _minus_ `i`. The order
in which `i` and `1` are subtracted from `n` don't make any difference, so we do `n - i - 1`.
