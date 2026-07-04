package org.example;

public class EmailSubscriber extends BaseSubscriber {
    public EmailSubscriber(String email) {
        super(email);
    }

    @Override
    protected void validateContactInfo(String contactInfo) {
        if (!contactInfo.matches("^[^@\\s]+@[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid Email format");
        }
    }

    @Override
    protected String formatNotifications(String category, String message) {
        return "Email to [" + contactInfo + "] | Category: " + category + " | " + message;
    }
}
