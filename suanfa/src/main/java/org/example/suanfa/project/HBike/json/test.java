package org.example.suanfa.project.HBike.json;

/**
 * @author heweitao538 2025/6/25
 */
public class test {

    public static void main(String[] args) {
            int caseId=22222220;
        String udId="handsome";
        String key = String.format("%d-%s", caseId, udId);
        System.out.println(key);
    }
}
