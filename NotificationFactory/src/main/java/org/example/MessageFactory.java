package org.example;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class MessageFactory {
    private final Map<MessageType, Function<String, Message>> registry;

    public MessageFactory() {
        Map<MessageType, Function<String, Message>> tempRegistry = new EnumMap<>(MessageType.class);
        tempRegistry.put(MessageType.EMAIL, EmailMessage::new);
        tempRegistry.put(MessageType.PUSH, PushMessage::new);
        tempRegistry.put(MessageType.SMS, SmsMessage::new);
        tempRegistry.put(MessageType.TELEGRAM, TelegramMessage::new);

        this.registry = Collections.unmodifiableMap(tempRegistry);
    }

    public Message createMessage(MessageType messageType, String recipientId) {
        if (messageType == null) {
            throw new IllegalArgumentException("Message type cannot be null");
        }

        Function<String, Message> messageConstructor = registry.get(messageType);
        if (messageConstructor == null) {
            throw new IllegalArgumentException("Unsupported message type: " + messageType);
        }
        return messageConstructor.apply(recipientId);
    }
}
