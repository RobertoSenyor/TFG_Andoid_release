package com.example.rgr.JavaModule.Types;

import java.util.Comparator;

public class Dot2D implements UserType
{
    private int x;
    private int y;

    public Dot2D() {
        x = 0;
        y = 0;
    }

    public Dot2D(int _axis_x, int _axis_y)
    {
        x = _axis_x;
        y = _axis_y;
    }

    @Override
    public String type_name() {
        return "Dot2D";
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
        return new Object[]{x,y};
    }

    @Override
    public Object parse_value(String ss) {

        String[] point2d = ss.split(";");

        x = Integer.parseInt(point2d[0]);
        y = Integer.parseInt(point2d[1]);
        return this;
    }

    @Override
    public Comparator get_type_Comparator() {
        return this;
    }

    @Override
    public String toString() { return x + ";" + y; }

    public double getDistance(int _x, int _y)
    {
        return Math.abs(Math.sqrt(Math.pow((x - _x),2)-Math.pow(y - _y,2)));
    }

    @Override
    public int compare(Object o1, Object o2) {

        if(((Dot2D)o1).getDistance(0,0)==((Dot2D)o2).getDistance(0,0)) {
            return 0;
        }
        if(((Dot2D)o1).getDistance(0,0)>((Dot2D)o2).getDistance(0,0)) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
