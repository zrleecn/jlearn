package cn.zrlee.learn;

/**
 * Person 类
 * @author zrlee
 *
 */
public class Person {
    public String name = null ;
    public int age = 0;
    protected String title = "haha" ;

    /**
     * 构
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取名字
     * @return String 名字
     */
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
