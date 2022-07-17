package de.telran.management_system.entity.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusConverterTest {

    TaskStatusConverter tsc = new TaskStatusConverter();

    @Nested
    @DisplayName("convertToDatabaseColumn()")
    class convertToDatabaseColumn {
        @Test
        @DisplayName("shuld convert to integer one, when value status todo")
        public void shuldConvertToIntegerOneWhenValueStatusTodoTest() {
            TaskStatus actual = TaskStatus.TODO;
            Integer expected = 1;
            Assertions.assertEquals(expected, tsc.convertToDatabaseColumn(actual));
        }

        @Test
        @DisplayName("shuld convert to integer two, when value status in progress")
        public void shuldConvertToIntegerTwoWhenValueStatusInProgressTest() {
            TaskStatus actual = TaskStatus.IN_PROGRESS;
            Integer expected = 2;
            Assertions.assertEquals(expected, tsc.convertToDatabaseColumn(actual));
        }

        @Test
        @DisplayName("shuld convert to integer three, when value status done")
        public void shuldConvertToIntegerThreeWhenValueStatusDoneTest() {
            TaskStatus actual = TaskStatus.DONE;
            Integer expected = 3;
            Assertions.assertEquals(expected, tsc.convertToDatabaseColumn(actual));
        }

        @Test
        @DisplayName("shuld convert to null, when value null")
        public void shuldConvertToNullWhenValueNullTest() {
            TaskStatus actual = null;
            Integer expected = null;
            Assertions.assertEquals(expected, tsc.convertToDatabaseColumn(actual));
        }

    }

    @Nested
    @DisplayName("convertToEntityAttribute()")
    class convertToEntityAttribute {

        @Test
        @DisplayName("shuld convert to  atribute null, when value null")
        public void shuldConvertToAtributeNullWhenValueNullTest() {
            Integer actual = null;
            TaskStatus expected = null;
            Assertions.assertEquals(expected, tsc.convertToEntityAttribute(actual));
        }

        @Test
        @DisplayName("shuld convert to atribute integer three, when value status done")
        public void shuldConvertToIntegerThreeWhenValueStatusDoneTest() {
            Integer actual = 3;
            TaskStatus expected = TaskStatus.DONE;
            Assertions.assertEquals(expected, tsc.convertToEntityAttribute(actual));
        }

        @Test
        @DisplayName("shuld convert to atribute integer two, when value status in progess")
        public void shuldConvertToIntegerTwoWhenValueStatusInProgressTest() {
            Integer actual = 2;
            TaskStatus expected = TaskStatus.IN_PROGRESS;
            Assertions.assertEquals(expected, tsc.convertToEntityAttribute(actual));
        }

        @Test
        @DisplayName("shuld convert to atribute integer one, when value status todo")
        public void shuldConvertToIntegerOneWhenValueStatusTodoTest() {
            Integer actual = 1;
            TaskStatus expected = TaskStatus.TODO;
            Assertions.assertEquals(expected, tsc.convertToEntityAttribute(actual));
        }
    }

}