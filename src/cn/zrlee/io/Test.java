package cn.zrlee.io;

import java.io.*;

/**
 * 字节
 *      InputStream  字节输入流父类 单位字节
 *          read()
 *          close()
 *      OutputStream
 *          write(int)
 *          flush()
 *          close()
 *
 * 字符
 *      Reader  字符输入流父类 单位字符
 *          read()
 *          close()
 *      Writer
 *          write(String)
 *          flush()
 *          close()
 *
 *
 * 步骤
 *  1. 创建源
 *  2. 选择流
 *  3.操作
 *  4.释放资源
 */

public class Test {
    public static void main(String[] args) {

    }


    /**
     *  字符集
     */
    public static void mycharset(){
        String msg = "中文字符" ;
        byte[] datas = {} ;
        try {
            // 获得字节数组 utf-8 一个中文占3字节
            datas = msg.getBytes("utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(datas.length);


        String str = "" ;
        try {
            // 字节数组转String new String(byte[] bytes, int offet, int length, String charsetName)
            str = new String(datas,0, datas.length, "utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

    /**
     * InputStream1
     */

    public static void inputStream1(){
        //        System.out.println(System.getProperty("user.dir"));
        // 创建源
        File src = new File("data.txt") ;
        // 选择流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(src) ;
            int temp;
            while((temp=inputStream.read()) != -1){
                System.out.println((char)temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){
                    // 释放资源
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * InputStream2 字节输入流
     */
    public static void inputStream2(){
        //        System.out.println(System.getProperty("user.dir"));
        // 创建源
        File src = new File("data.txt") ;
        InputStream inputStream =null ;
        try {
            inputStream = new FileInputStream(src) ;
            int len = -1 ; // 读取到的长度
            //缓冲容器
            byte[] flush = new byte[1024] ;
            // 读取到的数据保存到car 缓冲数组中 返回读取的长度
            while((len=inputStream.read(flush)) != -1){
                // 将字节数组转为字符串
                String str = new String(flush, 0, len,"utf-8") ;
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * OutputStream 字节输出流
     */

    public static void outputStream(){
        File src = new File("dest.txt") ;
        OutputStream outputStream = null ;

        try {
            // 不追加
            outputStream = new FileOutputStream(src, false) ;
            String msg = "write zrlee" ;
            // 字符串转字节流
            byte[] data = msg.getBytes() ;
            outputStream.write(data, 0, data.length) ;
            //
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * FileReader 字符输入流 只能操作文本
     */
    public static void fileReader(){
        File src = new File("data.txt") ;
        // 字符流
        Reader reader = null ;
        try {
            reader = new FileReader(src) ;
            char[] data = new char[1024] ;
            int len = -1 ;
            while ((len=reader.read(data)) != -1){
                // 转成字符串
                System.out.println(new String(data,0, len));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * FileWriter 字节输出流
     */
    public static void fileWriter(){
        File dest = new File("data.txt") ;
        Writer writer = null ;
        try {
            // 字符输出流
            writer = new FileWriter(dest, false) ;
            // 可以直接写字符串
            writer.write("哈哈");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 字节数组输入流
     * 源头是字节数组 任何数据都可以转成字节数组 方便网络传输
     *
     */
    public static void byteArrayInputStream(){
        // 源头是字节数组
        byte[] src = "talk is cheap show me the code".getBytes() ;
        int len = -1 ;
        InputStream inputStream = null ;
        inputStream = new ByteArrayInputStream(src) ;
        byte[] flush = new byte[40] ;
        try{
            while((len=inputStream.read(flush)) != -1){
                System.out.println(new String(flush, 0 , len));
            }
        }catch (IOException e){

        }
    }

    public static void byteArrayOutputStream(){
        // 源头是字节数组
        byte[] dest = null;
        int len = -1 ;
        // 选择流
        ByteArrayOutputStream byteArrayOutputStream = null ;
        byteArrayOutputStream = new ByteArrayOutputStream() ;
        String msg = "show me the code" ;
        byte[] datas = msg.getBytes() ; // 转成字节数组
        byteArrayOutputStream.write(datas, 0, datas.length);
        try {
            byteArrayOutputStream.flush();
            dest = byteArrayOutputStream.toByteArray() ; // 这是子类新增方法 所以上面初始化对象不能用多态创建对象
            System.out.println(new String(dest,0 , dest.length));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 装饰模式 有效提高效率
     */
    public static void buffered(){
        File src = new File("data.txt") ;
        InputStream inputStream =null ;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(src) ) ;
            int len = -1 ; // 读取到的长度
            //缓冲容器
            byte[] flush = new byte[1024] ;
            // 读取到的数据保存到car 缓冲数组中 返回读取的长度
            while((len=inputStream.read(flush)) != -1){
                // 将字节数组转为字符串
                String str = new String(flush, 0, len,"utf-8") ;
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
