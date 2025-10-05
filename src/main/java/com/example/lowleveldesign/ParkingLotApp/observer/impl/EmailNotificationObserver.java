package com.example.lowleveldesign.ParkingLotApp.observer.impl;

import com.example.lowleveldesign.ParkingLotApp.observer.NotificationObserver;

public class EmailNotificationObserver implements NotificationObserver {
    @Override
    public void notify(String message) {
        System.out.println("📧 Email sent: " + message);
    }
}