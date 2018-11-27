package cn.zrlee.learn;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 学习测试类
 * @author zrlee
 */
public class Test {
    public static void main(String[] args) {


    }



    /**
     * 冒泡排序
     *
     * @author zrlee
     */
    public static void bubbleSort() {
        int[] array = {3, 76, 33, 45, 8, 90, 24, 0, 60};
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    // 整个循环下来flag 都是true表示没有进行交换 则判定为已经排序好了 不需要再执行排序
                    flag = false;
                }
            }
            if (flag) {
                break;
            }

        }

        System.out.println(Arrays.toString(array));
    }

    /**
     * 二分查找
     */
    public static void find() {
        int[] array = {3, 76, 33, 45, 8, 90, 24, 0, 60};
        // 先排序
        Arrays.sort(array);
        // 要查找的值
        int value = 24;
        int index = binarySort(array, value);
        System.out.println(index);

    }

    /**
     * 二分查找
     *
     * @param arr   在哪个数组查找
     * @param value 需要查找的数值
     * @return 如果查找到 返回数值所在数组的索引 否则返回-1
     */
    public static int binarySort(int[] arr, int value) {

        int low = 0;
        int hight = arr.length - 1;
        while (low <= hight) {
            int mid = (low + hight) / 2;
            if (arr[mid] == value) {
                return mid;
            }
            if (value > arr[mid]) {
                low = mid + 1;
            }
            if (value < arr[mid]) {
                hight = mid - 1;
            }

        }
        return -1;
    }

    /**
     * Date DateFormat SimpleDateFormat 类
     * @author zrlee
     */
    public static void date(){
        Date date = new Date(System.currentTimeMillis()) ;
        // 获得时间戳
//        System.out.println(date.getTime());
//        System.out.println(System.currentTimeMillis());
        // 指定格式输出时间字符串
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        System.out.println(dateFormat.format(date));
        // 指定格式的字符串转成时间类型
        String date_str = "2019-10-23 12:01:23" ;
        try {
            date = dateFormat.parse(date_str) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date.toString());

    }

    /**
     * Calendar 类
     */
    public static void calendar(){
        Calendar calendar = new GregorianCalendar() ;
        System.out.println(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) +"月"
                + calendar.get(Calendar.DAY_OF_MONTH)+"日");  // 0 表示1月
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // 1-7   1对应是星期日
        calendar.set(Calendar.YEAR, 2019) ;  // 设置年份
        calendar.add(Calendar.DAY_OF_MONTH,100) ; // 100 天后的日期

        // 相互转换
        Date date = calendar.getTime() ;
        calendar.setTime(new Date());
    }

    /**
     * 可视化日历
     */
    public static void vCalendar(){
        String date_str = "2018-11-2" ;
        // 实例化时间格式化对象
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
        Date date = null ;
        try {
            // 字符串转成Date 对象
            date =  dateFormat.parse(date_str) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        // Date 转Calendar对象
        calendar.setTime(date);

        System.out.print("日\t一\t二\t三\t四\t五\t六\t");
        System.out.println();

        for (int i = 0 ; i < calendar.get(Calendar.DAY_OF_WEEK) - 1; i++){
            System.out.print("\t");
        }

        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            System.out.print(i + "\t");
            calendar.set(Calendar.DAY_OF_MONTH, i) ;
            // 如果是星期六 就换行
            if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                System.out.println();
            }
        }
    }


    public static void testPrintFile(){
        File file = new File("/home/zrlee/Documents/mdnote") ;
        printFile(file, 0);
    }
    /**
     * 递归打印文件目录文件
     * @param file File对象
     * @param level 递归执行层数
     */
    public static void printFile(File file , int level){

        for (int i =0 ; i<=level; i++){
            System.out.print("--");
        }
        // 打印文件/目录名
        System.out.println(file.getName());
        // 如果是目录
        if(file.isDirectory()){
            // 目录下的文件
            File[] files = file.listFiles() ;
            for (File temp : files){
                printFile(temp, level + 1);
            }
        }
    }

    /**
     * List,Set 集合遍历
     */
    public static void iter(){
        ArrayList<String> arrayList = new ArrayList<>() ;
        arrayList.add("aa") ;
        arrayList.add("bb") ;
        arrayList.add("cc") ;
        Iterator iterator = arrayList.iterator() ;
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    /**
     * List 遍历方式
     */
    public static void iterMap(){
        Map<Integer,String> map = new HashMap<>() ;
        map.put(1,"zrlee") ;
        map.put(2,"marry") ;
        map.put(3,"kobey") ;
        // 第一种方式
        Set<Map.Entry<Integer,String>> s = map.entrySet() ;
        Iterator<Map.Entry<Integer,String>> iterator =s.iterator() ;
        while (iterator.hasNext()){
            Map.Entry<Integer,String> me = iterator.next() ;
            System.out.println(me.getKey()+"-->" + me.getValue());
        }
        // 第二种方式
        Set<Integer> set = map.keySet() ;
        for(int key : set){
            System.out.println(map.get(key));
        }


    }

}
