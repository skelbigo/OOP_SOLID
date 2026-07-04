package org.example;

public interface GitObservable {
    void subscribeToCommits(String branch, GitObserver gitObserver);
    void subscribeToMerges(String branch, GitObserver gitObserver);

    void unsubscribeFromCommits(String branch, GitObserver gitObserver);
    void unsubscribeFromMerges(String branch, GitObserver gitObserver);
}
