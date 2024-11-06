package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TaskTest {

    @Test
    public void shouldBeEqualIfIdsAreEqual() {
        Task task1 = new Task("Задание 1", "Тест");
        Task task2 = new Task("Задание 2", "Тест");

        task1.setId(1);
        task2.setId(1);

        Assertions.assertEquals(task1, task2);
    }

    @Test
    public void shouldBeEqualForSubclassesIfIdsAreEqual() {
        Epic epic1 = new Epic("Эпик 1", "Тест");
        Epic epic2 = new Epic("Эпик 2", "Тест");

        epic1.setId(1);
        epic2.setId(1);

        Assertions.assertEquals(epic1, epic2);
    }

}
