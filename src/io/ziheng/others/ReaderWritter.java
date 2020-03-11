package io.ziheng.others;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReaderWritter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // BufferedReader bufferedReader = new BufferedReader(
        //     new InputStreamReader(System.in)
        // );
        // bufferedReader.readLine();
        while (scanner.hasNext()) {
            System.out.println(
                scanner.nextInt()
            );
        }
        scanner.close();
    }
}
/* EOF */