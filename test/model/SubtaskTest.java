package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubtaskTest {

    @Test
    public void subtaskCannotHaveItselfAsEpic() {

        Subtask subtask = new Subtask("Подзадача 1", "Тест", 1);
        subtask.setId(1);

        Assertions.assertEquals(subtask.getId(), subtask.getEpicId());
        // Не знаю как сделать
    }
}
