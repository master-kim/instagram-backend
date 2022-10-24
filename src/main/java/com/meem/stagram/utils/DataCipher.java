package com.meem.stagram.utils;

/**
 * 설명 : DataCipher 데이터 암/복호화를 담당하는 클래스파일
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.20    김요한    최초작성 
 * -------------------------------------------------------------
 */

import java.security.MessageDigest;

public class DataCipher {
    
    private DataCipher() {
    }
    
    public static String encryptDataToString(String i_strUserId, String i_strPassword) {
        
        String resultData = "";
        
        try {
            /* MessageDigest 클래스의 getInstance() 메소드의 매개변수에 "SHA-256" 알고리즘 이름을 지정함으로써 
            해당 알고리즘에서 해시값을 계산하는 MessageDigest를 구할 수 있다 */
            MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
            
            // 데이터(패스워드 평문)를 한다. 즉 '암호화'와 유사한 개념
            mdSHA256.update(i_strPassword.getBytes("UTF-8"));
            // 바이트 배열로 해쉬를 반환한다.
            byte[] sha256Hash = mdSHA256.digest();
            
            // StringBuffer 객체는 계속해서 append를 해도 객체는 오직 하나만 생성된다. => 메모리 낭비 개선
            StringBuffer hexSHA256hash = new StringBuffer();
            
            // 256비트로 생성 => 32Byte => 1Byte(8bit) => 16진수 2자리로 변환 => 16진수 한 자리는 4bit
            for(byte b : sha256Hash) {
                String hexString = String.format("%02x", b);
                hexSHA256hash.append(hexString);
            }
            resultData = hexSHA256hash.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 최종 결과를 리턴
        return resultData;
    }
}