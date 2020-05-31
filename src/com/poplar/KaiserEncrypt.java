package com.poplar;

/**
 * 凯斯加密，主要思想为位移
 * Create BY poplar ON 2020/5/29
 */
public class KaiserEncrypt {

    public static void main(String[] args) {
        String mata = "Hello World";
        int key = 3;
        String encryptStr = kaiserEncrypt(mata, key);
        System.out.println(encryptStr);
        String encrypt = kaiserDecrypt(encryptStr, key);
        System.out.println(encrypt);
    }

    //解密
    private static String kaiserDecrypt(String mata, int key) {
        StringBuilder sb = new StringBuilder();
        char[] chars = mata.toCharArray();
        for (char aChar : chars) {
            int input = aChar;
            input -= key;
            char newChar = (char) input;
            sb.append(newChar);
        }
        return sb.toString();
    }

    //开赛加密
    private static String kaiserEncrypt(String mata, int key) {
        StringBuilder sb = new StringBuilder();
        char[] chars = mata.toCharArray();
        for (char aChar : chars) {
            int input = aChar;
            input += key;
            char newChar = (char) input;
            sb.append(newChar);
        }
        return sb.toString();
    }
}
