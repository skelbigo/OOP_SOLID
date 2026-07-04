package org.example;

public class SmsSubscriber extends BaseSubscriber {
    public SmsSubscriber(String phone) {
        super(phone);
    }

    @Override
    protected void validateContactInfo(String contactInfo) {
        if (!contactInfo.matches("\\+?\\d+")) {
            throw new IllegalArgumentException("Invalid SMS: phone number must contain digits and may start with '+'");
        }
    }

    @Override
    protected String formatNotifications(String category, String message) {
        return "Sms to [" + contactInfo + "] | Category: " + category + " | " + message;
    }
}
