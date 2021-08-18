package com.basic.server;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/29 15:56
 */
public class Test {
    public static void main(String[] args) {
        String password = "zhangyong";
        String[] arr = new String[0];
        try {
            arr = ConfigTools.genKeyPair(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        try {
            System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           String flas =  ConfigTools.decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJR+cPKOVocoZxBPMFKtH66oHZOLtDccehm+2ypn2uOynzY3sWR5n5h6WQCL98W0yS0bq/s98gz1DhMO782RoGECAwEAAQ==",
                    "fYyvbsRwxFTMTl3UYWOmrvN8fG7U2mmuJIgn2gnWm0TvvdMPU4OXCDtdyNwkghKrOxz3AQYXsvfKUb2IqQwZTg==");
            System.out.println(flas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
