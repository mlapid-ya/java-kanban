package manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ManagersTest {

    private final Managers managers = new Managers();

    @Test
    public void shouldReturnInitializedTaskManager() {
        TaskManager taskManager = managers.getDefault();
        Assertions.assertNotNull(taskManager);
    }

    @Test
    public void shouldReturnInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Assertions.assertNotNull(historyManager);
    }
}
