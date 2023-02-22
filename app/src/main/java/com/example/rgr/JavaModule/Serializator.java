package com.example.rgr.JavaModule;


import com.example.rgr.JavaModule.Types.UserType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Serializator {

    public  static void saveToFile(BigList bigList, String filename, String type) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(type);
            bigList.forEach(writer::print);
        }
        System.out.println("\nList was saved!");
    }

    public static BigList loadFile(String filename) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String type = br.readLine();
            if (!UserFactory.get_type_name_list().contains(type)) {
                throw new Exception("Wrong file structure");
            }

            String line = br.readLine();
            String[] items = line.split(" ");
            UserType[] arr = new UserType[items.length];

            for (int i = 0; i < arr.length; i++)
                if (!Objects.equals(items[i], "null"))
                    arr[i] = (UserType) UserFactory.get_builder_by_name(type).parse_value(items[i]);

            System.out.println("\nList was loaded!");

//            System.out.println(Arrays.asList(arr));

            return new BigList(arr);
        }
    }
}
