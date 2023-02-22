package com.example.rgr.JavaModule.Types;

import com.example.rgr.JavaModule.Types.UserType;

import java.util.Comparator;

public class Int implements UserType
{
    private int value;

    public Int() { value = 0;}

    public Int(int _value) {value = _value;}

    @Override
    public String type_name() {
        return "Int";
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object clone() {
        return this;
    }

    @Override
    public Object read_value() {
        return value;
    }

    @Override
    public Object parse_value(String ss) {

        value = Integer.parseInt(ss);
        return this;
    }

    @Override
    public Comparator get_type_Comparator() {
        return this;
    }

    @Override
    public String toString() {return String.valueOf(value);}

    @Override
    public int compare(Object o1, Object o2) {

        if (((Int)o1).value == ((Int)o2).value) return 0;
        if (((Int)o1).value > ((Int)o2).value) return 1;
        else return -1;
    }
}
