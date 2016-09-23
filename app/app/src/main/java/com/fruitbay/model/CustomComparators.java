package com.fruitbay.model;

import java.util.Comparator;

/**
 * Created by ManikantaReddyD on 23-Sep-16.
 */
public  class CustomComparators {

    public static class stockComparatorAscending implements Comparator<FruitsClass>
    {
        @Override
        public int compare(FruitsClass o1, FruitsClass o2)
        {
            return Integer.valueOf(o2.getStock()).compareTo(Integer.valueOf(o1.getStock()));
        }
    }


    public static class PriceComparatorAscending implements Comparator<FruitsClass>
    {
        public int compare(FruitsClass o1, FruitsClass o2)
        {
//            return Float.valueOf(o1.getPrice()).compareTo(Float.valueOf(o2.getPrice()));
            float change1 = Float.valueOf(o1.getPrice());
            float change2 = Float.valueOf(o2.getPrice());

            if (change1 > change2) return -1;
            if (change1 < change2) return 1;
            return 0;
        }
    }

}
