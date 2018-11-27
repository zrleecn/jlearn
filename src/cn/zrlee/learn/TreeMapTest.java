package cn.zrlee.learn;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 实现Comparable接口定制排序
 */
class Emp implements Comparable<Emp>{

    private int sal = 0;

    public Emp(int sal) {
        this.sal = sal;
    }

    @Override
    public int compareTo(Emp o) {
        if(this.sal > o.sal){
            return 1;
        }else if(this.sal == o.sal){
            return 0 ;
        }
        return -1 ;
    }

    @Override
    public String toString() {
        return this.sal + "" ;
    }
}

class Student{
    private int score = 0 ;

    public Student(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "" + this.score ;
    }
}


public class TreeMapTest {
    public static void main(String[] args) {
        TreeSet<Emp> set = new TreeSet<>() ;
        set.add(new Emp(8000)) ;
        set.add(new Emp(5000)) ;
        set.add(new Emp(10000)) ;
        System.out.println(set.toString());


        // 内部类定制排序
        TreeSet set2 = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Student s1 = (Student)o1 ;
                Student s2 = (Student) o2;
                if(s1.getScore() > s2.getScore()){
                    return 1 ;
                }else{
                    return -1 ;
                }
            }
        });

        set2.add(new Student(90)) ;
        set2.add(new Student(80)) ;
        set2.add(new Student(100)) ;
        System.out.println(set2.toString());
    }
}
