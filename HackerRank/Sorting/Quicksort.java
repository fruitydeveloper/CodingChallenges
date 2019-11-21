import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static void quicksort(int[] array) {
        quicksort(array, 0, array.length-1);
    }

    public static void quicksort(int[] array, int left, int right){
        if(left >= right) {
            return;
        }
        int pivot = array[(left+right)/2];
        int index = partition(array, left, right, pivot);
        quicksort(array, left, index - 1);
        quicksort(array, index, right);
    }

    public static int partition(int[] array, int left, int right, int pivot){
        while(left <= right) {
            while(array[left] < pivot) {
                left++;
            }

            while(array[right] > pivot) {
                right--;
            }

            if(left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static void swap(int[] array, left, right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args) throws IOException {
        int[] list = new int[10];
        list[0] = 0;
        list[1] = 6;
        list[2] = 5;
        list[3] = 1;
        list[4] = 8;
        list[5] = 9;
        list[6] = 4;
        list[7] = 2;
        list[8] = 7;
        list[9] = 3;

        quicksort(list);
    }
}
