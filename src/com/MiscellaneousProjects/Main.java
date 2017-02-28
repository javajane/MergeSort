package com.MiscellaneousProjects;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    /**
     * @param args
     * @author: Pinaki (javajane@github)
     * @program MergeSort - This program takes an array of Integers as parameter and sorts per the Merge Sort algorithm.
     * Essential an array is continually split until one or two element subarrays remain - which in turn are then sorted
     * and combined recursively until a sorted array of original length is formed
     */
    public static void main(String[] args) {

        //int[] inarray = new int[]{3,4,9,88,1, 90, 5, 23, 11, 6, 76,55};
        //int[] inarray = new int[]{3,5,4,1,90,-78,2,89};
        //int[] inarray = new int[]{5, 49, 6, 25, 1, 9};
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        try {
            for (String str : args) {
                int num=Integer.valueOf(str);
                integerArrayList.add(num);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid argument, please try again" + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again " + e.getMessage() );
            System.exit(1);
        }

        int[] inarray = new int[integerArrayList.size()];
        int i=0;
        for(Integer num:integerArrayList){
            inarray[i++]=num;
        }


        int[] sortedarray = new int[inarray.length];


        sortedarray = mergeSort(inarray);

        System.out.println("Original array:");
        for (int num : inarray) {
            System.out.println("Input array: " + num);
        }

        System.out.println("\nPRINTING SORTED ARRAY");

        for (int num : sortedarray) {
            System.out.println("SORTED ARRAY: " + num);
        }


    }

    /**
     *
     * @param inarray - takes an unsorted array of integers
     * @return - returns a sorted array of integers
     * This is a recursive function where it first splits array into two and recursively calls itself if either or both
     * arrays have more than 1 element
     */
    public static int[] mergeSort(int[] inarray) {

        int length = inarray.length;
        int midlength = (inarray.length % 2 == 0) ? (inarray.length / 2) : (inarray.length / 2 + 1);

        ArrayList<Integer> sortedleft = new ArrayList<>();
        ArrayList<Integer> sortedright = new ArrayList<>();


        int[] leftarray = new int[inarray.length / 2];
        int[] rightarray = new int[midlength];


        System.arraycopy(inarray, 0, leftarray, 0, inarray.length / 2);
        System.arraycopy(inarray, inarray.length / 2, rightarray, 0, midlength);


        if (leftarray.length >= 2 && rightarray.length >= 2) {


            int[] returnleftarray = mergeSort(leftarray);
            int[] returnrightarray = mergeSort(rightarray);

            for (int i : returnleftarray) {
                sortedleft.add(i);
            }

            for (int i : returnrightarray) {
                sortedright.add(i);
            }
        } else if (leftarray.length == 1 && rightarray.length == 2) {

            sortedleft.add(leftarray[0]);
            int[] returnrightarray = mergeSort(rightarray);
            sortedright.add(returnrightarray[0]);
            sortedright.add(returnrightarray[1]);


        } else if (leftarray.length == 2 && rightarray.length == 1) {

            int[] returnleftarray = mergeSort(leftarray);
            sortedleft.add(returnleftarray[0]);
            sortedleft.add(returnleftarray[1]);

            sortedright.add(rightarray[0]);

        } else if (leftarray.length == 1 && rightarray.length == 1) {
            if (leftarray[0] < rightarray[0]) {
                sortedleft.add(leftarray[0]);
                sortedright.add(rightarray[0]);

            } else {
                sortedright.add(rightarray[0]);
                sortedleft.add(leftarray[0]);
            }

        } else {
            System.out.println("Unhandled number of elements");
        }

        int returnarraylength = sortedleft.size() + sortedright.size();
        int[] returnarray = new int[returnarraylength];
        int r = 0;

        OUTER_LOOP:
        for (int i = 0; i < sortedleft.size(); i++) {
            INNER_LOOP:
            for (int j = 0; j < sortedright.size(); j++) {
                int leftint = sortedleft.get(i);
                int rightint = sortedright.get(j);
                if (leftint < rightint) {
                    returnarray[r++] = leftint;
                    sortedleft.remove(i);
                    i = i - 1;
                    break INNER_LOOP;
                } else {
                    returnarray[r++] = rightint;
                    sortedright.remove(j);
                    j = j - 1;
                }
            }

        }

        for (int i = 0; i < sortedleft.size(); i++) {
            returnarray[r++] = sortedleft.get(i);
        }

        for (int j = 0; j < sortedright.size(); j++) {
            returnarray[r++] = sortedright.get(j);
        }

        return returnarray;

    }
}
