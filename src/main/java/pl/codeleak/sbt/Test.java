package pl.codeleak.sbt;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.jar.Pack200;

/**
 * Created by Administrator on 2017/11/3.
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        String s="111@";
        Test test=new Test();
        System.out.print(test.getList().getClass());
        List<String> list1=new LinkedList<String>();
        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
//        for(String s:list){
//            if (s.equals("1"))
//                list.remove(s);
//        }
        List<String> list2=list.subList(1,3);
        list2.set(0,"122");
        list2.remove(1);
        list.remove(2);
//        for(String s:list){
//            System.out.println(s);
//        }
//        for(String s:list2){
//            System.out.println(s);
//        }
    }

    public MyList getList(){
        return new MyList();
    }

    public void p(MyList l){
        l.p();
    }

    public class MyList<T>{
        private Object[] s=new Object[10];
        private int i=0;
        public int add(T o){
            s[i++]=o;
            return i;
        }
        public T get(){
            return (T)s[i];
        }

        public void p(){
            System.out.print("1");
        }
    }



}
