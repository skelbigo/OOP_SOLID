package org.example;

import java.util.LinkedHashSet;
import java.util.Set;

public class YouTubeChannel implements Subject {
    private final String channelName;
    private final Set<Subscriber> subscribers;

    public YouTubeChannel(String channelName) {
        if (channelName == null || channelName.strip().isBlank()) {
            throw new IllegalArgumentException("Channel name cannot be null or blank");
        }
        this.channelName = channelName.strip();
        this.subscribers = new LinkedHashSet<>();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null");
        }
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null");
        }
        subscribers.remove(subscriber);
    }

    public void publishVideo(String videoTitle) {
        if (videoTitle == null || videoTitle.strip().isBlank()) {
            throw new IllegalArgumentException("Video title cannot be null or blank");
        }
        notifySubscribers(videoTitle.strip());
    }

    private void notifySubscribers(String videoTitle) {
        for(Subscriber subscriber : subscribers) {
            subscriber.update(channelName, videoTitle);
        }
    }
}
