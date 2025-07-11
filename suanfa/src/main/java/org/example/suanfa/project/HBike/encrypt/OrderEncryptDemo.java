package org.example.suanfa.project.HBike.encrypt;

public class OrderEncryptDemo {

    public static void main(String[] args) throws Exception {
        String orderId = "ORDER1234567890";
        System.out.println("原始订单号: " + orderId);

        String encryptedOrderId = AesEncryptUtils.encrypt(orderId);
        System.out.println("加密后的订单号: " + encryptedOrderId);

        String decryptedOrderId = AesEncryptUtils.decrypt(encryptedOrderId);
        System.out.println("解密后的订单号: " + decryptedOrderId);
    }
}
