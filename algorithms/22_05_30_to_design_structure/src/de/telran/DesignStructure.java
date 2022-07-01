package de.telran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DesignStructure {

        public static Random rm = new Random();
        private HashMap<Long,Integer> design;
        private ArrayList<Long> keynumber;

        /** Initialize your data structure here. */
        public DesignStructure () {
            design = new HashMap<>();
            keynumber = new ArrayList<>();
        }

        public boolean add (long val){
            if(design.containsKey(val)){
                return false;
            }
            keynumber.add(val);
            design.put(val, keynumber.size()-1);
            return true;
        }



        public boolean remove(long val) {
            if(!design.containsKey(val)){
                return false;
            }

            if(design.get(val) != keynumber.size()-1){
                int loc = design.get(val);//location of val
                Long tail = keynumber.get(keynumber.size()-1);//number of last
                //data.set(data.size()-1, data.get(loc));
                keynumber.set(loc, tail);
                design.put(tail,loc);
            }

            design.remove(val);
            keynumber.remove(keynumber.size()-1);
            return true;
        }

    public Long getRandom() {
        return keynumber.get(rm.nextInt(keynumber.size()));
    }
        public static void main(String[] args) {




        }
    }



