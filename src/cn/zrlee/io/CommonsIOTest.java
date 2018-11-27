package cn.zrlee.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;


public class CommonsIOTest {
    public static void main(String[] args) {

    }



    /**
     * 文件列表 过滤条件
     */
    public static void list_fiilter(){

        Collection<File> files = FileUtils.listFiles(new File("src/cn"),
                // 过滤条件文件java结尾 或 文件不为空  FileFilterUtils.and()
                FileFilterUtils.or( new SuffixFileFilter("java"), EmptyFileFilter.NOT_EMPTY)
                , DirectoryFileFilter.INSTANCE) ;

        // DirectoryFileFilter.INSTANCE 列出子孙级的文件
        for (File file : files){
            System.out.println(file.getAbsolutePath());
        }

    }

    /**
     * listFile
     */
    public static void list1(){
        Collection<File> files = FileUtils.listFiles(new File("src/cn"), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE) ;

        // DirectoryFileFilter.INSTANCE 列出子孙级的文件
        for (File file : files){
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * 基本信息
     */
    public static void info() {
        //文件大小
        long len = FileUtils.sizeOf(new File(("data.txt"))) ;
        //目录大小
        System.out.println(len);
        len = FileUtils.sizeOf(new File("src")) ;
        System.out.println(len);
    }

    /**
     * 读取
     */
    public static  void read(){
        try {
            String msg = FileUtils.readFileToString(new File("data.txt"), "utf-8");
            System.out.println(msg);
            System.out.println("==============");
            // 读到字节数组
            byte[] datas = FileUtils.readFileToByteArray(new File("data.txt")) ;
            System.out.println(new String(datas,0 , datas.length));
            System.out.println("==============");
            // 逐行读

            LineIterator lineIterator = FileUtils.lineIterator(new File("data.txt"), "utf-8") ;
            while (lineIterator.hasNext()){
                System.out.println(lineIterator.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写
     */
    public static void write(){
        // 写入文件
        try {
            FileUtils.writeStringToFile(new File("dest.txt"), "this is content\r\n","utf-8",true);
            FileUtils.writeByteArrayToFile(new File("dest.txt"), "this is conten\r\nt".getBytes("utf-8"),true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝
     */
    public static void copy(){
        try {
            // 复制文件
            FileUtils.copyFile(new File("data.txt"), new File("data-copy.txt"));
            //拷贝到目录
            FileUtils.copyFileToDirectory(new File("data.txt"), new File("upload"));

            // FileUtils.copyDirectory();
//            FileUtils.copyDirectoryToDirectory();
//            String data = IOUtils.toString(new URL("https://www.baidu.com") , "utf-8") ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
