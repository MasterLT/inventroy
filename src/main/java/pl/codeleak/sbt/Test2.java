package pl.codeleak.sbt;

/**
 * Created by Administrator on 2017/11/8.
 */
public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Test test=new Test();
        Test.MyList myList= test.new MyList<String>();
    }
}
