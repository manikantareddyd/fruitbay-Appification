package com.fruitbay.model;

import java.util.Comparator;

/**
 * Created by ManikantaReddyD on 23-Sep-16.
 */
public class CustomComparators {

    public class stockComparatorAscending implements Comparator<FruitsClass>
    {
        public int compare(FruitsClass o1, FruitsClass o2)
        {
            return Integer.valueOf(o1.getStock()).compareTo(Integer.valueOf(o2.getStock()));
        }
    }

    public class stockComparatorDescending implements Comparator<FruitsClass>
    {
        public int compare(FruitsClass o1, FruitsClass o2)
        {
            return Integer.valueOf(o2.getStock()).compareTo(Integer.valueOf(o1.getStock()));
        }
    }

    public class PriceComparatorAscending implements Comparator<FruitsClass>
    {
        public int compare(FruitsClass o1, FruitsClass o2)
        {
            return Float.valueOf(o1.getPrice()).compareTo(Float.valueOf(o2.getPrice()));
        }
    }
    public class PriceComparatorDescending implements Comparator<FruitsClass>
    {
        public int compare(FruitsClass o1, FruitsClass o2)
        {
            return Float.valueOf(o2.getPrice()).compareTo(Float.valueOf(o1.getPrice()));
        }
    }
}
