package com.example.rgr.JavaModule;

import com.example.rgr.JavaModule.Types.UserType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface List_action
{
    Object get_head();
    Object get_tail();
    String print_list();
    int get_count();
    ArrayList to_array();
    Object remove_item_from_head();
    Object get_item_on_position(int logical_position);
    boolean change_item_on_pos(int logical_position, UserType _item);
    void remove_list();
}
