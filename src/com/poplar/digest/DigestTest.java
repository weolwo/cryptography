package com.poplar.digest;

import java.security.MessageDigest;

/**
 * Create BY poplar ON 2020/5/30
 */
public class DigestTest {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "中国";
        // 算法
        String algorithm = "MD5";
        // 获取数字摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 获取消息数字摘要的字节数组
        byte[] digest = messageDigest.digest(input.getBytes());
        //System.out.println(Base64.encode(digest));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hexString = Integer.toHexString(b & 0xff);
            // 如果生成的字符只有一个，前面补0
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        System.out.println(sb.toString());
    }
}
