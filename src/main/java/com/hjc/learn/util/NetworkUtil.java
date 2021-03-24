package com.hjc.learn.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author houjichao
 */
public class NetworkUtil {

    /**
     * 获取本机使用网卡的MAC地址
     */
    private static String getMacAddress() throws UnknownHostException, SocketException {
        //获取IP地址

        //InetAddress address = InetAddress.getLocalHost();
        //这种方式会报错
        //linux配了localhost的hosts映射，用localhost方法取不到网卡信息．用虚拟机真实ＩＰ地址取才行
        InetAddress ia = InetAddress.getByName("10.95.22.92");
        //获取网卡的MAC地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

        String macString = toMacString(mac);
        System.out.println(macString);

        boolean flag = NetworkInterface.getNetworkInterfaces().hasMoreElements();

        if (flag) {
            NetworkInterface networkInterface = NetworkInterface.getNetworkInterfaces().nextElement();
            byte[] macByte = networkInterface.getHardwareAddress();
            String s = toMacString(macByte);
            System.out.println(s);
        }

        return macString;

    }

    private static String toMacString(byte[] mac) {
        StringBuilder sb = new StringBuilder();
        if (mac != null) {
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0").append(str);
                } else {
                    sb.append(str);
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 按照"XX-XX-XX-XX-XX-XX"格式，获取本机MAC地址
     *
     * @return
     * @throws Exception
     */
    public static String getMacAddressTwo() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface netI = networkInterfaces.nextElement();

            byte[] bytes = netI.getHardwareAddress();
            if (netI.isUp() && netI != null && bytes != null && bytes.length == 6) {
                StringBuffer sb = new StringBuffer();
                for (byte b : bytes) {
                    //与11110000作按位与运算以便读取当前字节高4位
                    sb.append(Integer.toHexString((b & 240) >> 4));
                    //与00001111作按位与运算以便读取当前字节低4位
                    sb.append(Integer.toHexString(b & 15));
                    sb.append("-");
                }
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString().toUpperCase();
            }
        }
        return null;
    }


    public static void main(String[] args) throws UnknownHostException, SocketException {
        System.out.println(getMacAddress());
        System.out.println(getMacAddressTwo());

    }
}
