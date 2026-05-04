package com.swiftspend.swiftspend.threads;

public class MonthlySummaryThread extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Calculating Summary...");
            Thread.sleep(2000);
            System.out.println("Summary Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}