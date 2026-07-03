package org.example;

public class PushMessage extends BaseMessage {
    private final String deviceToken;

    public PushMessage(String deviceToken) {
        if (deviceToken == null) throw new IllegalArgumentException("Token cannot be null");

        String stripped = deviceToken.strip();
        if (stripped.isBlank()) {
            throw new IllegalArgumentException("Invalid Push: device token cannot be empty");
        }
        this.deviceToken = stripped;
    }

    @Override
    protected String doSend(String content) {
        return "Sending Push Notification to device [" + deviceToken + "]: " + content;
    }
}
