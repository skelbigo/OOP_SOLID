import org.example.EventType;
import org.example.GitRepository;
import org.example.WebHook;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GitRepositoryTest {
    private GitRepository repository;
    private WebHook mainCommitHook;
    private WebHook devCommitHook;
    private WebHook mainMergeHook;

    @BeforeEach
    void setUp() {
        repository = new GitRepository("MyJava");
        mainCommitHook = new WebHook("Main-Commit-Hook");
        devCommitHook = new WebHook("Dev-Commit-Hook");
        mainMergeHook = new WebHook("Main-Merge-Hook");
    }

    @Test
    void shouldNotifyOnlyCommitSubscribersOnSpecificBranch() {
        repository.subscribeToCommits("main", mainCommitHook);
        repository.subscribeToCommits("dev", devCommitHook);

        repository.commit("main", "Fix login bug");

        assertEquals(1, mainCommitHook.getReceivedEvents().size());
        String event = mainCommitHook.getReceivedEvents().get(0);

        assertTrue(event.contains("COMMIT"));
        assertTrue(event.contains("branch 'main'"));
        assertTrue(event.contains("[Repo: MyJava]"));
        assertTrue(event.contains("Message: Fix login bug"));

        assertTrue(devCommitHook.getReceivedEvents().isEmpty());
    }


    @Test
    void shouldNotifyOnlyMergeSubscribersOnTargetBranch() {
        repository.subscribeToCommits("main", mainCommitHook);
        repository.subscribeToMerges("main", mainMergeHook);

        repository.merge("dev", "main");

        assertEquals(1, mainMergeHook.getReceivedEvents().size());
        String event = mainMergeHook.getReceivedEvents().get(0);

        assertTrue(event.contains("MERGE"));
        assertTrue(event.contains("branch 'main'"));
        assertTrue(event.contains("Merged 'dev' into 'main'"));
        assertTrue(event.contains("[Repo: MyJava]"));

        assertTrue(mainCommitHook.getReceivedEvents().isEmpty());
    }

    @Test
    void shouldNormalizeBranchNamesCorrectly() {
        repository.subscribeToCommits("  MAIN  ", mainCommitHook);
        repository.commit("main", "Initial commit");
        assertEquals(1, mainCommitHook.getReceivedEvents().size());
    }

    @Test
    void shouldStopNotifyingAfterUnsubscribe() {
        repository.subscribeToCommits("dev", devCommitHook);
        repository.commit("dev", "WIP feature");

        assertEquals(1, devCommitHook.getReceivedEvents().size());
        repository.unsubscribeFromCommits("dev", devCommitHook);
        repository.commit("dev", "Another commit");
        assertEquals(1, devCommitHook.getReceivedEvents().size());
    }

    @Test
    void unsubscribeFromNonExistentBranchShouldNotThrowException() {
        assertDoesNotThrow(() -> repository.unsubscribeFromCommits("fake-branch", mainCommitHook));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForInvalidGitRepositoryInputs() {
        assertThrows(IllegalArgumentException.class, () -> new GitRepository(null));
        assertThrows(IllegalArgumentException.class, () -> new GitRepository("   "));

        assertThrows(IllegalArgumentException.class, () -> repository.commit(null, "Message"));
        assertThrows(IllegalArgumentException.class, () -> repository.commit("main", "   "));

        assertThrows(IllegalArgumentException.class, () -> repository.merge("dev", null));
        assertThrows(IllegalArgumentException.class, () -> repository.merge("   ", "main"));

        assertThrows(IllegalArgumentException.class, () -> repository.subscribeToCommits(" ", mainCommitHook));
        assertThrows(IllegalArgumentException.class, () -> repository.subscribeToCommits("main", null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForInvalidWebHookInputs() {
        assertThrows(IllegalArgumentException.class, () -> new WebHook(null));
        assertThrows(IllegalArgumentException.class, () -> new WebHook("   "));

        assertThrows(IllegalArgumentException.class, () -> mainCommitHook.update(null, "main", "details"));
        assertThrows(IllegalArgumentException.class, () -> mainCommitHook.update(EventType.COMMIT, null, "details"));
        assertThrows(IllegalArgumentException.class, () -> mainCommitHook.update(EventType.COMMIT, "main", null));
    }

    @Test
    void webHookReceivedEventsShouldBeImmutable() {
        repository.subscribeToCommits("main", mainCommitHook);
        repository.commit("main", "Test encapsulation");

        List<String> events = mainCommitHook.getReceivedEvents();

        assertThrows(UnsupportedOperationException.class, () -> events.add("Fake event"));
    }
}
