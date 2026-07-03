package org.example;

public class TelegramMessage extends BaseMessage {
    private final String username;

    public TelegramMessage(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        String stripped = username.strip();
        if (stripped.length() <= 1 || !stripped.startsWith("@")) {
            throw new IllegalArgumentException("Invalid Telegram: username must start with '@' followed by characters");
        }
        this.username = stripped;
    }

    @Override
    protected String doSend(String content) {
        return "Sending Telegram to [" + username + "]: " + content;
    }
}