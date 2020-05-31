package com.poplar.des;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Create BY poplar ON 2020/5/30
 */
public class DesTest {

    public static void main(String[] args) throws Exception {
        // 原文
        String input = "中国66";
        // des加密必须是8位
        String key = "12345678";
        String algorithm = "DES";
        //默认的模式 DES/ECB/PKCS5Padding
        //String transformation = "DES/ECB/PKCS5Padding";
        // CBC模式,必须指定初始向量,初始向量中密钥的长度必须是8个字节
        //String transformation = "DES/CBC/PKCS5Padding";
        String transformation = "DES/CBC/NoPadding";
        // 算法
        String desEncrypt = desEncrpyt(input, key, algorithm, transformation);
        System.out.println(desEncrypt);
        System.out.println(desDecrypt(desEncrypt, key, algorithm, transformation));
    }

    private static String desEncrpyt(String input, String key, String algorithm, String transformation) throws Exception {
        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
        IvParameterSpec ips = new IvParameterSpec(key.getBytes());
        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.ENCRYPT_MODE, sks, ips);
        // 进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 打印字节，因为ascii码有负数，解析不出来，所以乱码
       /* for (byte b : bytes) {
            System.out.println(b);
        }*/
        //使用Base64编码
        // 打印密文
        return Base64.encode(bytes);
    }

    private static String desDecrypt(String input, String key, String algorithm, String transformation) throws Exception {

        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
        IvParameterSpec ips = new IvParameterSpec(key.getBytes());
        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.DECRYPT_MODE, sks, ips);
        //使用base64解码
        byte[] decode = Base64.decode(input);
        // 进行加密
        byte[] bytes = cipher.doFinal(decode);
        // 打印字节，因为ascii码有负数，解析不出来，所以乱码
        // 返回解密后的值
        return new String(bytes);
    }
}
