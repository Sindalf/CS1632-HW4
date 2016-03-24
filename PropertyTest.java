import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyTest {

    int[][] many_arrays = null;

    /*
    * Create 100 different int arrays of different sizes
    * Values are unknown to the tester
    * Math.random() * 100 returns a value between 0<=N<100
     */
    @Before
    public void setUp() {
        many_arrays = new int[100][];
        int size = 0;

        for (int j = 0; j < 100; j++) {
            size = (int) (Math.random() * 100);
            many_arrays[j] = RandomArray(size);
        }
    }

    /*
    * Method to create an array of N size with random values
    * Random values are between 0<=N<100
     */
    public int[] RandomArray(int size) {
        int[] newarray = new int[size];

        for (int i = 0; i < size; i++) {
            newarray[i] = (int) (Math.random() * 100);
        }
        return newarray;
    }

    /*
    * Simple bubble sort to use indepdently of Arrays.sort()
    * Arrays.sort() and BubbleSort() should produce the same output
    * Regardless of input and size
     */
    public int[] BubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /*
    * Tests if the increasing property is held true for all values
     */
    @Test
    public void testIncreasing() {
        int[] check = null;
        for (int i = 0; i < many_arrays.length; i++) {
            check = many_arrays[i];
            Arrays.sort(check);
            for (int j = 0; j < check.length - 1; j++) {
                assertTrue(check[j] <= check[j + 1]);
            }
        }
    }

    /*
    * Tests if every value in the array before sort is still in the array
     */
    @Test
    public void testContains() {
        int[] check = null;
        int index = -1;
        for (int i = 0; i < many_arrays.length; i++) {
            check = Arrays.copyOf(many_arrays[i], many_arrays[i].length);
            Arrays.sort(check);
            for (int j = 0; j < check.length; j++) {
                index = Arrays.binarySearch(check, many_arrays[i][j]);
                assertTrue(index >= 0);
            }
        }
    }

    /*
    * Tests if the Arrays.sort is pure
    * Makes sure that if ran twice we get the same output as before.
     */
    @Test
    public void testPure() {
        int[] check = null;
        for (int i = 0; i < many_arrays.length; i++) {
            check = Arrays.copyOf(many_arrays[i], many_arrays[i].length);
            check = BubbleSort(check);
            Arrays.sort(many_arrays[i]);
            Arrays.sort(many_arrays[i]);
            Assert.assertArrayEquals(check, many_arrays[i]);
        }
    }

    /*
    * Making sure the sort function actually works from a functional standpoint
    * NOT A PROPERTY
     */
    @Test
    public void testSort() {
        int[] check = null;
        for (int i = 0; i < many_arrays.length; i++) {
            check = Arrays.copyOf(many_arrays[i], many_arrays[i].length);
            check = BubbleSort(check);
            Arrays.sort(many_arrays[i]);
            Assert.assertArrayEquals(check, many_arrays[i]);
        }
    }

}
