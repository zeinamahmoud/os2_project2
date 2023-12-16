/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.io.File;
import java.util.*;

public class My_project extends gui {
    private gui guiInstance;
    private String longestFileWord = "";
    private String shortestFileWord = "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups";
    
    public My_project(gui guiInstance) {
        super(); // Ensure to call the constructor of the superclass
        this.guiInstance = guiInstance;
    }
    public static void main(String[] args) {

    }

    public void Word_statistics(String text,String fileName) {
        File file = new File(fileName);
        String lastFileName = file.getName();
        String char1 = "is";
        String char2 = "you";
        String char3 = "are";
        int number_of_word = num_of_words(text);
        String subLongest = LogestWord(text);
        String subShorts = ShortestWord(text);
        int i_exested = searchWord(text, char1);
        int you_exested = searchWord(text, char2);
        int are_exested = searchWord(text, char3);
        Object[] rows = {lastFileName,i_exested, you_exested, are_exested, number_of_word, subLongest, subShorts};
        guiInstance.addRowToTable(lastFileName,i_exested, you_exested, are_exested, number_of_word, subLongest, subShorts);
        sharedLongestWord(subLongest);
        sharedShortestWord(subShorts);
    }

    public static int num_of_words(String text) {

        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                count++;
            }
        }
        return ++count;
    }

    public static String LogestWord(String text) {

        String arr[] = text.split(" ");

        String LogestWord = arr[0];

        for (String a : arr) {
            if (LogestWord.length() < a.length()&& !a.equals("is") && !a.equals("you") && !a.equals("are")) {
                LogestWord = a;
            }
        }

        return LogestWord;
    }

    public static String ShortestWord(String text) {

        String arr[] = text.split(" ");

        String ShortestWord = arr[0];

        for (String a : arr) {
            if (ShortestWord.length() > a.length() && !a.equals("is") && !a.equals("you") && !a.equals("are")) {
                ShortestWord = a;
            }
        }

        return ShortestWord;
    }

    public static int searchWord(String text, String word) {

        String arr[] = text.split(" ");

        String searchWord = word;
        int count = 0;
        for (String a : arr) {
            if (searchWord.equals(a)) {
                count++;
            }
        }

        return count;
    }

    public synchronized void sharedLongestWord(String subLongest) {
        try {
            while (longestFileWord.length() >= subLongest.length()) {
                wait();
            }

            // Update the longest word
            longestFileWord = subLongest;
            guiInstance.updateLongestWordInFile(subLongest);
            System.out.println(" Longest Word in folder " + longestFileWord);
            // Notify other waiting threads
            Thread.sleep(2000);
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void sharedShortestWord(String subShortest) {
        try {
            // Wait if the condition is not met
            while (shortestFileWord.length() <= subShortest.length()) {
                wait();
            }

            // Update the shortest word
            shortestFileWord = subShortest;
            guiInstance.updateShortsWordInFile(subShortest);
            System.out.println(" shortest Word in folder " + shortestFileWord);

            // Notify other waiting threads
            Thread.sleep(2000);
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
