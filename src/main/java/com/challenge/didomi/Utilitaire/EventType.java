package com.challenge.didomi.Utilitaire;

public enum EventType {
    EMAIL_NOTIFICATIONS("email_notifications"),

    SMS_NOTIFICATIONS("sms_notifications");

    private String notification;

    private EventType(String notification) {

        this.notification = notification;
    }

    public String getNotification() {

        return this.notification;
    }
}
