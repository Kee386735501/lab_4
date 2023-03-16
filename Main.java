package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // read the file
        String filename = "input.txt";
        List<List<Integer>> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                List<Integer> rowNumbers = new ArrayList<>();
                for (String token : tokens) {
                    int number = Integer.parseInt(token);
                    rowNumbers.add(number);
                }
                numbers.add(rowNumbers);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // order the number
        numbers.sort((list1, list2) -> {
            int sizeDiff = list2.size() - list1.size();
            if (sizeDiff != 0) {
                return sizeDiff;
            } else {
                int firstNumDiff = list1.get(0) - list2.get(0);
                if (firstNumDiff != 0) {
                    return firstNumDiff;
                } else {
                    return list1.size() - list2.size();
                }
            }
        });


        //create the color list and arrange it
        List<String> colors = new ArrayList<>();

        for (char c = 'A'; c < 'Z'; c++) {
            String color = String.valueOf(c);
            colors.add(color);
        }




        //create the color integer pair
        HashMap<Integer,String> coloredPoint = new HashMap<>();


        //create the list(color-number)
        List<String> ans = new ArrayList<>();


        for (int i = 0; i < numbers.size(); i++) {
            int colorIndex = 0;
            //if the neighbour is painted?
            for (int j = 1; j < numbers.get(i).size(); j++) {
                if (coloredPoint.containsKey(numbers.get(i).get(j)))
                    colorIndex+=1;
            }
            //paint this point
            coloredPoint.put(numbers.get(i).get(0),colors.get(colorIndex));
            ans.add(numbers.get(i).get(0)+colors.get(colorIndex));
        }


        //sort the ans
        ans.sort((s1, s2) -> {
            int n1 = Integer.parseInt(s1.substring(0, s1.length() - 1));
            int n2 = Integer.parseInt(s2.substring(0, s2.length() - 1));
            if (n1 != n2) {
                return Integer.compare(n1, n2);
            } else {
                return s1.substring(s1.length() - 1).compareTo(s2.substring(s2.length() - 1));
            }
        });
        System.out.println(ans);
          //write into output.txt
        FileWriter writer = new FileWriter("output.txt");
        BufferedWriter buffer = new BufferedWriter(writer);

        for (int i = 0; i < ans.size(); i++) {
            buffer.write(ans.get(i).toString());
            buffer.newLine();
        }

        buffer.close();
    }
}