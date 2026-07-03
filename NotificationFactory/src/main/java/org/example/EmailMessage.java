package org.example;

public class EmailMessage extends BaseMessage {
    private final String emailAddress;

    public EmailMessage(String emailAddress) {
        if (emailAddress == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        String stripped = emailAddress.strip();
        if (stripped.isBlank() || !stripped.matches("^[^@\\s]+@[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid Email: must contain text before and after '@'");
        }
        this.emailAddress = stripped;
    }

    @Override
    protected String doSend(String content) {
        return "Sending Email to [" + emailAddress + "]: " + content;
    }
}
