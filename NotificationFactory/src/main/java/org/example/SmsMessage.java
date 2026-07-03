package org.example;

public class SmsMessage extends BaseMessage {
    private final String phoneNumber;

    public SmsMessage(String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        String stripped = phoneNumber.strip();
        if (stripped.isBlank() || !stripped.matches("\\+?\\d+")) {
            throw new IllegalArgumentException("Invalid SMS: phone number must contain digits and may start with '+'");
        }
        this.phoneNumber = stripped;
    }

    @Override
    protected String doSend(String content) {
        return "Sending SMS to [" + phoneNumber + "]: " + content;
    }
}
