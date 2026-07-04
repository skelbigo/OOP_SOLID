package org.example;

public class TelegramSubscriber extends BaseSubscriber {
    public TelegramSubscriber(String username) {
        super(username);
    }

    @Override
    protected void validateContactInfo(String contactInfo) {
        if (contactInfo.length() <= 1 || !contactInfo.startsWith("@")) {
            throw new IllegalArgumentException("Invalid Telegram: username must start with '@'");
        }
    }

    @Override
    protected String formatNotifications(String category, String message) {
        return "Telegram to [" + contactInfo + "] | Category: " + category + " | " + message;
    }
}
// implement Topic-based Observer for store category discounts