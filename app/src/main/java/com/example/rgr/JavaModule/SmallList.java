package com.example.rgr.JavaModule;


import com.example.rgr.JavaModule.Types.UserType;

import java.io.Serializable;
import java.util.ArrayList;

public class SmallList implements List_action
{
    private int count;            // количество элементов списка
    private SmallListNode head; // первый элемент списка
    private SmallListNode tail; // последний элемент списка

    private class SmallListNode
    {
        private UserType item;        // данные узла списка
        private SmallListNode next; // указатель на след. узел

        public SmallListNode(UserType _item, SmallListNode _next)
        {
            item = _item;
            next = _next;
        }

        public SmallListNode get_next()
        {
            return next;
        }

        public UserType get_value()
        {
            return (item != null) ? item : null;
        }

    };

    public SmallList()
    {
        count = 0;
        head = tail = null;
    }

    @Override
    public SmallListNode get_head()
    {
        return head;
    }

    @Override
    public SmallListNode get_tail()
    {
        return tail;
    }

    public void push(UserType _item)
    {
        SmallListNode newnode = new SmallListNode(_item, null);

        if(tail == null)
        {
            head = newnode;
        }
        else
        {
            tail.next = newnode;
        }

        tail = newnode;
        count++;
    }

    public void push_to_head(UserType _item)
    {
        SmallListNode newnode = new SmallListNode(_item, head);

        if(head==null)
        {
            tail = newnode;
        }

        head = newnode;
        count++;
    }

    public boolean insert_item_on_position( int logical_position, UserType _item)
    {
        SmallListNode prev = null;
        SmallListNode cur = head;

        if(logical_position == get_count() + 1)
        {
            push(_item);
            return true;
        }

        if(logical_position > get_count() || logical_position <= 0)
        {
            System.err.println("SmallList (insert_on_position): u write wrong position " + logical_position);
            System.err.println("Need 1-" + get_count());
            return false;
        }

        switch (logical_position)
        {
            case 1:
            {
                push_to_head(_item);
                return true;
            }
            default:
            {
                for (int buf_pos = 1; ; prev = cur, cur = cur.next, buf_pos++)
                {
                    if (buf_pos == logical_position)
                    {
                        SmallListNode newNode = new SmallListNode(_item, cur);
                        prev.next = newNode;
                        count++;
                        return true;
                    }
                }
            }
        }
    }

    public UserType remove_item_from_head() {

        UserType buf_item = (UserType) head.get_value();

        if (count == 1)
        {
            System.err.println("SmallList (remove_on_position): your SmallList have only so lone element");
            System.err.println("Can u add another item in future?");
        }

        if(head != null)
        {
            head = head.next;
            count --;
            return buf_item;
        }
        else
        {
            System.out.println("Error: can't delete *HEAD* element");
            return null;
        }
    }

    public UserType remove_item_on_position(int logical_position)
    {
        SmallListNode prev = null;
        SmallListNode cur = head;

        if(count==1)
        {
            System.err.println("SmallList (remove_on_position): your SmallList have only so lone element");
            System.err.println("Can u add another item in future?");
        }

        if(logical_position > get_count() || logical_position <= 0)
        {
            System.err.println("BigList (remove_on_position): u write wrong position " + logical_position);
            System.err.println("Need 1-" + get_count());
            return null;
        }

        UserType buf_cur = null;

        for (int buf_pos = 1 ; buf_pos <= count; prev = cur, cur = cur.next, buf_pos++)
        {
            if (buf_pos == logical_position)
            {
                if (cur == head)
                {
                    buf_cur = cur.get_value();
                    remove_item_from_head();
                    break;
                }
                else
                {
                    buf_cur = cur.get_value();
                    prev.next = cur.next;
                    cur = null;
                    count--;
                    break;
                }
            }
        }
        return buf_cur;
    }

    @Override
    public UserType get_item_on_position(int logical_position)
    {
        SmallListNode cur = head;

        if(logical_position > get_count() || logical_position <= 0)
        {
            System.err.println("SmallList (get_on_position): u write wrong position " + logical_position);
            System.err.println("Need 1-" + get_count());
        }

        for (int buf_pos = 1; cur.next != null; cur = cur.next, buf_pos++)
        {
            if (buf_pos == logical_position)
            {
                return cur.get_value();
            }
        }
        return cur.get_value();
    }


    public boolean change_item_on_pos(int logical_position, UserType _new_value)
    {
        SmallListNode cur = head;

        if(logical_position > get_count() || logical_position <= 0)
        {
            System.err.println("SmallList (get_on_position): u write wrong position " + logical_position);
            System.err.println("Need 1-" + get_count());
            return false;
        }

        for (int buf_pos = 1; cur.next != null; cur = cur.next, buf_pos++)
        {
            if (buf_pos == logical_position)
            {
                remove_item_on_position(logical_position);
                insert_item_on_position(logical_position, _new_value);
                break;
            }
        }
        return (cur.get_value())!=null;
    }

    @Override
    public String print_list() {

        return to_array().toString();
    }

    @Override
    public int get_count() {
        return count;
    }

    @Override
    public ArrayList to_array()
    {
        ArrayList<UserType> array = new ArrayList<>();

        if (head != null)
        {
            for (SmallListNode cur = head; cur != null; cur = cur.next)
            {
                array.add(cur.item);
            }
            return array;
        }
        else
        {
            return null;
        }
    }

    public UserType remove_from_head()
    {
        UserType buf_result = head.item;

        if(head != null)
        {
            head = head.next;
            count--;
            return buf_result;
        }
        else
        {
            System.out.println("Error: can't delete *HEAD* element");
            return null;
        }
    }

    @Override
    public void remove_list() {

        while (count!=0)
        {
            remove_from_head();
        }
    }
}
