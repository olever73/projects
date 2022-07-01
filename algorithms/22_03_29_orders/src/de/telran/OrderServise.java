package de.telran;



import java.util.*;


 public class OrderServise {
         /**
          *
          * @param orders a list of orders
          * @param delta time interval in millis
          * @return the number of orders from the same restaurant for every order for the previous delta millis before the oder.
          */
     public Map<String, Deque<Long>> countPreviousOrders(List<Order> orders, long delta) {
         Map<String, Deque<Long>> result = new HashMap<>();

         for (Order order : orders) {
             String key = order.getRestaurantId();
             Long orderTime = order.getTimestamp();

             if (result.containsKey(key)) {
                 Deque<Long> time = result.get(key);
                 time.addLast(orderTime);
                 while (orderTime - time.getFirst() > delta)
                     time.removeFirst();
             } else {
                 Deque<Long> time = new ArrayDeque<>();
                 time.addLast(orderTime);
                 result.put(key, time);
             }
         }
         return result;
     }


 }
