package de.telran;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {

             Main m = new Main();

     @Test
       public void testAssertMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("employee_name", "MapTest");
        map.put("employee_salary", "99999");
        map.put("employee_age", "30");
        map.put("profile_image", "test.png");
        Map<String, Object> expected = new HashMap<>();
         expected.put("employeeName", "MapTest");
         expected.put("employeeSalary", "99999");
         expected.put("employeeAge", "30");
         expected.put("profileImage", "test.png");
         assertEquals (expected, m.snakeToCamelMap(map));
    }

}

