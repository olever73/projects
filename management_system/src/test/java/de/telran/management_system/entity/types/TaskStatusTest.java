package de.telran.management_system.entity.types;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TaskStatusTest {
    @Nested
    @DisplayName("findByIntegerValueTest()")
    class findByIntegerValue{
        @Test
        @DisplayName("shuld find by integer null, when  value is null")
        public void shuldFindByIntegerValueisNullTest() {

            Integer statusActual=null;
            Integer expectedStatus=null;
            Assertions.assertEquals(expectedStatus, TaskStatus.findByIntegerValue(statusActual));
        }
        @Test
        @DisplayName("shuld find by integer one, when  value is one")
        void shuldFindByIntegerOneValueisOneTest() {
            Integer statusActual=1;
            TaskStatus expectedStatus=TaskStatus.TODO;

            Assertions.assertEquals(expectedStatus, TaskStatus.findByIntegerValue(statusActual));
        }

        @Test
        @DisplayName("shuld find By integer two, when  value is two")
        void shuldFindByIntegerTwoValueisTwoTest() {
            Integer statusActual=2;
            TaskStatus expectedStatus=TaskStatus.IN_PROGRESS;
            Assertions.assertEquals(expectedStatus, TaskStatus.findByIntegerValue(statusActual));
        }
        @Test
        @DisplayName("shuld find By integer three, when  value is three")
        void shuldFindByIntegerThreeValueisThreeTest() {
            Integer statusActual=3;
            TaskStatus expectedStatus=TaskStatus.DONE;
            Assertions.assertEquals(expectedStatus, TaskStatus.findByIntegerValue(statusActual));
        }
    }

    @Nested
    @DisplayName("findByStringValueTest()")
    class findByStringValue {
        @Test
        @DisplayName("shuld find by null , when  value is string null")
        public void shuldFindByNullValueNullTest() {

            String statusActual = "null";
            TaskStatus expected = null;
            Assertions.assertEquals(expected, TaskStatus.findByStringValue(statusActual));
        }

        @Test
        @DisplayName("shuld  by status todo, when  value is string todo")
        public void shuldFindByStringTodoValueTodoTest() {
            String statusActual = "todo";
            TaskStatus expected = TaskStatus.TODO;
            Assertions.assertEquals(expected, TaskStatus.findByStringValue(statusActual));
        }

        @Test
        @DisplayName("shuld  by status in progress, when  value is string in progress")
        public void shuldFindByStringInProgressValueInProgressTest() {
            String statusActual = "in_progress";
            TaskStatus expected = TaskStatus.IN_PROGRESS;
            Assertions.assertEquals(expected, TaskStatus.findByStringValue(statusActual));
        }

        @Test
        @DisplayName("shuld  by status done, when  value is string done")
        public void shuldFindByStringDoneValueDoneTest() {
            String statusActual = "done";
            TaskStatus expected = TaskStatus.DONE;
            Assertions.assertEquals(expected, TaskStatus.findByStringValue(statusActual));
        }
    }
}