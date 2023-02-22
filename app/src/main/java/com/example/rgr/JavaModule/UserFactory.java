package com.example.rgr.JavaModule;

import com.example.rgr.JavaModule.Types.Int;
import com.example.rgr.JavaModule.Types.Dot2D;
import com.example.rgr.JavaModule.Types.UserType;

import java.util.ArrayList;
import java.util.Arrays;

public class UserFactory {

    public static ArrayList<String> get_type_name_list()
    {
        ArrayList<String> type_list = new ArrayList<>(Arrays.asList("Int", "Dot2D"));
        return type_list;
    }

    public static UserType get_builder_by_name(String type_name)
    {
        switch (type_name)
        {
            case "Int":
                return new Int();
            case "Dot2D":
                return new Dot2D();
            default:
                return null;
        }
    }
}
