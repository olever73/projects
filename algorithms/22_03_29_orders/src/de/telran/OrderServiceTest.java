package de.telran;



import org.junit.jupiter.api.Test;

import java.util.Deque;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


    public class OrderServiceTest {


        OrderServise orderServise = new OrderServise();

        Order order1 = new Order("01", 100L, "1");
        Order order2 = new Order("02", 200L, "1");
        Order order3 = new Order("03", 300L, "2");
        Order order4 = new Order("04", 400L, "3");
        Order order5 = new Order("05", 500L, "1");
        Order order6 = new Order("06", 600L, "2");
        Order order7 = new Order("07", 700L, "1");
        List<Order> orders = new ArrayList<>();



        @Test
        public void CountPreviousOrders_Test_1() {
            orders.add(order1);
            orders.add(order2);
            orders.add(order3);
            orders.add(order4);
            orders.add(order5);
            orders.add(order6);
            orders.add(order7);
            Map<String, Deque<Long>> result = orderServise.countPreviousOrders(orders, 350L);

            assertEquals(2, result.get("1").size());
        }

        @Test
        public void testCountPreviousOrders_Test_2() {
            orders.add(order1);
            orders.add(order2);
            orders.add(order3);
            orders.add(order4);
            orders.add(order5);
            orders.add(order6);
            orders.add(order7);

            Map<String, Deque<Long>> result = orderServise.countPreviousOrders(orders, 500L);

            assertEquals(3, result.get("1").size());
        }
        @Test
        public void testCountPreviousOrders_Test_3() {
            orders.add(order1);
            orders.add(order2);
            orders.add(order3);
            orders.add(order4);
            orders.add(order5);
            orders.add(order6);
            orders.add(order7);

            Map<String, Deque<Long>> result = orderServise.countPreviousOrders(orders, 700L);

            assertEquals(4, result.get("1").size());
        }

    }