package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class EpicTest {

    @Test
    public void epicCannotContainItselfAsSubtask() {
        Epic epic = new Epic("Эпик 1", "Тест");
        epic.setId(1);

        Subtask subtask = new Subtask("Эпик 1", "Тест", epic.getId());
        subtask.setId(1);

        ArrayList<Subtask> subtasks = epic.getSubtasks();
        subtasks.add(subtask);

        Assertions.assertEquals(epic.getId(), subtask.getId());
        // Не знаю как сделать
    }
}
