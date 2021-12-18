package com.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/*
* The Purpose of this assignment is to read in a text file of words and then sort them with a bubble sort. The user
* can input words to search for. The words are searched for by a binary search algorithm which finds weather or not
* the word occurs in the array.
*/
public class Main {

    public static void main(String[] args) {

        //read file to array method call
        String[] fileArray = readFile();
        // print out the array before sorting
        System.out.println("Before Sorting");
        PrintArray(fileArray);
        // sort the array
        SortMyArray(fileArray, fileArray.length);
        // print out the array after sorting
        System.out.println("\nAfter Sorting");
        PrintArray(fileArray);


        Scanner scanner = new Scanner(System.in);
        String searchWord;
        int result;

        // Using a do/while loop to get the words from user and then search the array as long as user
        // does not enter 0
        do {

            System.out.print("\nEnter a word to search for: ");
            searchWord = scanner.next();

            if(Objects.equals(searchWord, "0")){
                System.out.println("\nGoodBye!!!");
                break;
            }

            result =  BinarySearch(fileArray, searchWord);

            if(result == -1)
                System.out.println("\n" + searchWord + " was not found!");
            else
                System.out.println("\n" + searchWord + " was found at index " + result);

        }while (!Objects.equals(searchWord, "0"));

    }


    /*
     * This is my binary search algorithm. It takes in the array of words and the word that we are searching for, then
     * using a divide and conquer strategy, searches for the searchWord in the fileArray.
     */
    private static int BinarySearch(String[] fileArray, String searchWord) {

        // Initialize the start and end positions
        int start = 0, end = fileArray.length - 1;

        // The while loop will continue until start position becomes greater than the end position signaling that the
        // array has been fully searched through.
        while (start <= end) {
            // Find the middle of the array and store it in an int called middle
            int middle = start + (end - start) / 2;

            // store the result of comparing the search word to the word located in the middle index of the array.
            int result = searchWord.compareTo(fileArray[middle]);

            // If result is equal to 0, we have found the word in the array, return the index at which the word
            // was found.
            if (result == 0)
                return middle;

            // If result is greater than 0, throw away the second half of the array
            if (result > 0)
                start = middle + 1;

                // If result is less than 0, throw away the first half of the array
            else
                end = middle - 1;
        }

        // If the word does not exist in the array, -1 will be returned because there can be no index at -1
        return -1;
    }

    /*
    * This method sorts the array alphabetically by use of the bubble sort.
    */
    private static void SortMyArray(String[] fileArray, int myArraySize) {

        // base index
        for(int i = 0; i < myArraySize - 1; i++) {
            // all other indexes searched after base index

            for (int j = i + 1; j < fileArray.length; j++) {

                // using the compareTo() method to see if the string in the array at position should be ordered
                // lexicographically. If so, then string in fileArray[i] is swapped with fileArray[j]
                if (fileArray[i].compareTo(fileArray[j]) > 0) {
                    String temp = fileArray[i];
                    fileArray[i] = fileArray[j];
                    fileArray[j] = temp;
                }
            }
        }
    }

    // Simple method to print the array

    private static void PrintArray(String[] fileArray) {
        for (String word : fileArray) {

            System.out.print(word + " ");
        }
        System.out.println();
    }

    /*
    * Method reads in the strings from the file and stores them in an array of Strings
    */
    private static String[] readFile() {
        String[] words = new String[10000];
        int wordCounter = 0;
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("C:\\CST-201\\Week1\\ArrayWarmupActivity\\src\\com\\algorithms\\myText.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (sc2 != null) {
            while (sc2.hasNextLine()) {
                Scanner s2 = new Scanner(sc2.nextLine());
                while (s2.hasNext()) {
                    words[wordCounter]  = s2.next();
                    wordCounter++;

                }
            }
        }

        words = resizeArray(words); // method call to resize the array removing null values
        return words;
    }

    /*
    * This method will resize the array to get rid of all null values
    */
    private static String[] resizeArray(String[] words) {

        String[] myWords = new String[words.length];
        int count = -1;
        for(String word : words) {
            if(word != null) {
                myWords[++count] = word;
            }
        }
        words = Arrays.copyOf(myWords, count + 1);
        return words;
    }
}
