package testIO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author q2333
 * @date 2022/06/23 09:39
 **/
@SuppressWarnings("all")
public class test {
    @Test
   public void createFile() throws IOException {
       String filePath="F:\\folder\\b.txt";
        final File file = new File(filePath);//内存中创建文件
        file.createNewFile();//将内存中的文件写入磁盘
    }
    @Test
    public void createFile2() throws IOException {
        String path="F:\\folder";
        String fileName="c.txt";
        final File file = new File(path,fileName);//内存中创建文件
        file.createNewFile();//将内存中的文件写入磁盘
    }
     @Test
    public void createFile3() throws IOException {
        URI uri= URI.create("https://github.com/");
        final File file = new File(uri);//内存中创建文件
        file.createNewFile();//将内存中的文件写入磁盘
    }
     @Test
    public void readFile() throws IOException {
         String fileWithPath="F:\\folder\\a.txt";
         File file = new File(fileWithPath);//内存中创建文件
         System.out.println(file.getName());
         System.out.println(file.getParent());
         System.out.println(file.getAbsolutePath());
         System.out.println(file.length());
         System.out.println(file.exists());
         System.out.println(file.isFile());
         System.out.println(file.isDirectory());

    }
         @Test
    public void createFileWithDir() throws IOException {
        String Dir="F:\\folder\\f2";
        final File file = new File(Dir);
        if (file.exists()){
            System.out.println("目录已存在");
        }
        else {
            file.mkdirs();
            file.createNewFile();
            System.out.println("创建目录"+ Dir+"成功");
        }
    }

    @Test
    void writeFile() throws IOException {
        String path="F:\\folder\\h.txt";
        String fileToWrite="hello,write()";
        final FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(fileToWrite.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }


    @Test
    void copyFile() throws IOException {
        String srcFile="F:\\folder\\mojave.jpg ";
        String destFile="F:\\folder\\mojave2.jpg";
        final FileInputStream inputStream = new FileInputStream(srcFile);
        final FileOutputStream outputStream = new FileOutputStream(destFile);
        byte[] buf  = new byte[1024*100];//100 kilo-bytes
        int readLen=0;
        while ((readLen=inputStream.read(buf))!=-1){
            outputStream.write(buf,0,readLen);
            //前面n次readlen=1024*100,最后一次为0~readLen长度的写入
        }
        inputStream.close();
        outputStream.close();
    }


}















