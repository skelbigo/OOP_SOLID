package org.example;

public abstract class BaseMessage implements Message {
    @Override
    public String send(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank");
        }
        return doSend(content);
    }

    protected abstract String doSend(String content);
}
