package com.wwj;

import java.io.*;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        MainClass mainClass=new MainClass();

        int codeNum=-1;
        while (true) {
            System.out.println("请输入指令：1加密，2解密,3退出,按回车确认");

            codeNum=mainClass.getCodeNum();
            if(codeNum==1) {
                System.out.println("-----加密-----");
                System.out.println("请输入加密文件全路径");
                String readFilePath=mainClass.getReadFilePath();
                if("3".equals(readFilePath)) {
                    break;
                }
                else {
                    System.out.println("请输入文件加密后保存文件夹全路径");
                    String writeFolderPath=mainClass.getWriteFolderPath();
                    if("3".equals(writeFolderPath)) {
                        break;
                    }
                    else {
                        System.out.println("文件开始加密");
                        mainClass.encrypt(readFilePath,writeFolderPath);
                        System.out.println("加密完成");
                    }
                }
            }
            else if(codeNum==2) {
                System.out.println("-----解码-----");
                System.out.println("请输入解码文件全路径");
                String readFilePath=mainClass.getReadFilePath();
                if("3".equals(readFilePath)) {
                    break;
                }
                else {
                    System.out.println("请输入文件解码后保存文件夹全路径");
                    String writeFolderPath=mainClass.getWriteFolderPath();
                    if("3".equals(writeFolderPath)) {
                        break;
                    }
                    else {
                        System.out.println("文件开始解码");
                        mainClass.encrypt(readFilePath,writeFolderPath);
                        System.out.println("解码完成");
                    }
                }
            }
            else if(codeNum==3) {
                break;
            }
        }

        System.out.println("程序结束");

    }

    public int getCodeNum() {
        Scanner scanner=new Scanner(System.in);
        int codeNum=-1;
        while (true) {
            String operate=scanner.nextLine();
            if("1".equals(operate)) {
                codeNum=1;
            }
            else if("2".equals(operate)) {
                codeNum=2;
            }
            else if("3".equals(operate)) {
                codeNum=3;
            }

            if(codeNum==1||codeNum==2||codeNum==3) {
                break;
            }
            else {
                System.out.println("输入内容不符合要求，请重新输入");
            }
        }
        return codeNum;
    }

    public String getReadFilePath(){
        Scanner scanner=new Scanner(System.in);
        String readFilePath="";
        while (true) {
            readFilePath=scanner.nextLine();
            if("3".equals(readFilePath)) {
                break;
            }
            else {
                File file=new File(readFilePath);
                if(!file.exists()) {
                    System.out.println("文件不存在请重新输入");
                }
                else {
                    break;
                }
            }
        }
        return readFilePath;
    }

    public String getWriteFolderPath() {
        Scanner scanner=new Scanner(System.in);
        String writeFolderPath="";
        while (true) {
            writeFolderPath=scanner.nextLine();
            if("3".equals(writeFolderPath)) {
                break;
            }
            else {
                File file=new File(writeFolderPath);
                if(file.isDirectory()) {
                    break;
                }
                else {
                    System.out.println("该路径不是文件夹，请重新输入");
                }
            }
        }
        return writeFolderPath;
    }

    public void encrypt(String readFilePath,String writeFolderPath) {

        File readFile=new File(readFilePath);

        StringBuilder fileName=new StringBuilder(readFilePath);
//        fileName.delete(fileName.lastIndexOf("."), fileName.length());
        fileName.delete(0,fileName.lastIndexOf("\\"));
//        fileName.append(".wwj");

        StringBuilder writeFolderPathStringBuilder=new StringBuilder(writeFolderPath);
        writeFolderPathStringBuilder.append(fileName);
        String writeFilePath=writeFolderPathStringBuilder.toString();

        File writeFile=new File(writeFilePath);

        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(readFile);
            fos=new FileOutputStream(writeFile);

            byte[] bytes=new byte[1024];
            int count=fis.read(bytes);
            while (count!=-1) {
                if(count>1){
                    byte temp=bytes[0];
                    bytes[0]=bytes[1];
                    bytes[1]=temp;
                }

                fos.write(bytes,0,count);
                fos.flush();
                count=fis.read(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
