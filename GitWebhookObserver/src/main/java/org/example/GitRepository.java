package org.example;

import java.util.*;

public class GitRepository implements GitObservable {
    private final String repositoryName;
    private final Map<String, Set<GitObserver>> commitSubscribers = new HashMap<>();
    private final Map<String, Set<GitObserver>> mergeSubscribers = new HashMap<>();

    public GitRepository(String repositoryName) {
        if (repositoryName == null || repositoryName.strip().isBlank()) {
            throw new IllegalArgumentException("Repository name cannot be null or blank");
        }
        this.repositoryName = repositoryName.strip();
    }

    @Override
    public void subscribeToCommits(String branch, GitObserver gitObserver) {
        subscribe(commitSubscribers, branch, gitObserver);
    }

    @Override
    public void subscribeToMerges(String branch, GitObserver gitObserver) {
        subscribe(mergeSubscribers, branch, gitObserver);
    }

    @Override
    public void unsubscribeFromCommits(String branch, GitObserver gitObserver) {
        unsubscribe(commitSubscribers, branch, gitObserver);
    }

    @Override
    public void unsubscribeFromMerges(String branch, GitObserver gitObserver) {
        unsubscribe(mergeSubscribers, branch, gitObserver);
    }

    public void commit(String branch, String commitMessage) {
        if (branch == null || branch.strip().isBlank()) {
            throw new IllegalArgumentException("Branch cannot be null or blank");
        }
        if (commitMessage == null || commitMessage.strip().isBlank()) {
            throw new IllegalArgumentException("Commit message cannot be null or blank");
        }

        String normalizedBranch = normalize(branch);
        String details = "[Repo: " + repositoryName + "] Message: " + commitMessage.strip();

        notifySubscribers(commitSubscribers, EventType.COMMIT, normalizedBranch, details);
    }

    public void merge(String sourceBranch, String targetBranch) {
        if (sourceBranch == null || sourceBranch.strip().isBlank()) {
            throw new IllegalArgumentException("Source branch cannot be null or blank");
        }
        if (targetBranch == null || targetBranch.strip().isBlank()) {
            throw new IllegalArgumentException("Target branch cannot be null or blank");
        }

        String normalizedTarget = normalize(targetBranch);
        String details = "[Repo: " + repositoryName + "] Merged '" + normalize(sourceBranch) + "' into '" + normalizedTarget + "'";

        notifySubscribers(mergeSubscribers, EventType.MERGE, normalizedTarget, details);
    }

    private void subscribe(Map<String, Set<GitObserver>> map, String branch, GitObserver gitObserver) {
        validateInputs(branch, gitObserver);
        map.computeIfAbsent(normalize(branch), k -> new LinkedHashSet<>()).add(gitObserver);
    }

    private void unsubscribe(Map<String, Set<GitObserver>> map, String branch, GitObserver gitObserver) {
        validateInputs(branch, gitObserver);
        String normalizedBranch = normalize(branch);

        Set<GitObserver> subscribers = map.get(normalizedBranch);
        if (subscribers != null) {
            subscribers.remove(gitObserver);
            if (subscribers.isEmpty()) {
                map.remove(normalizedBranch);
            }
        }
    }

    private void notifySubscribers(Map<String, Set<GitObserver>> map, EventType eventType, String branch, String details) {
        Set<GitObserver> subscribers = map.getOrDefault(branch, Collections.emptySet());
        for (GitObserver gitObserver : subscribers) {
            gitObserver.update(eventType, branch, details);
        }
    }

    private void validateInputs(String branch, GitObserver gitObserver) {
        if (branch == null || branch.strip().isBlank()) {
            throw new IllegalArgumentException("Branch cannot be null or blank");
        }
        if (gitObserver == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
    }

    private String normalize(String input) {
        return input.strip().toLowerCase();
    }
}
