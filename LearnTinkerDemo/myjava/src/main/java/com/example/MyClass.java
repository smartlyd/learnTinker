package com.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MyClass {

    public static void main(String[] args) {
        List<TestD> l = new ArrayList<>();

        TestD d = new TestD();
        d.setName("abc" );
        d.setCode("def");
        l.add(d);
        d = new TestD();
        d.setName("cba" );
        d.setCode("lhc");
        l.add(d);
        d = new TestD();
        d.setName("cba" );
        d.setCode("dmq");
        l.add(d);
        d = new TestD();
        d.setName("cba" );
        d.setCode("lbb");
        l.add(d);
        d = new TestD();
        d.setName("cba" );
        d.setCode("mq");
        l.add(d);
        System.out.println("================");
        FilterFoodSorter sorter = new FilterFoodSorter();
        sorter.setFilter("mq");

        Comparator<TestD> codeSorter = new Comparator<TestD>() {

            @Override
            public int compare(TestD o1, TestD o2) {
                return o1.getCode().toLowerCase().compareTo(o2.getCode().toLowerCase());
            }

            @Override
            public String toString() {
                return "按助记码";
            }
        };

        sorter.setFoodSorter(codeSorter);

        Collections.sort(l,sorter);
        System.out.println(l);
        HashMap<String,String> map = new HashMap<>();
       for (String s:map.keySet()){

       }

    }



    public static class FilterFoodSorter implements Comparator<TestD> {

        @SuppressWarnings("unused")
        private final static String TAG = "FilterFoodSorter";
        private String filter = "";
        private String filterLow = "";
        /**
         * 菜品排序
         */
        private Comparator<TestD> foodSorter;
        private boolean checkFilter = true;



        @Override
        public int compare(TestD o1, TestD o2) {
            if (checkFilter) {
                int p1 = getFilterSort(o1);
                int p2 = getFilterSort(o2);
                if (p1 != p2) {
                    return p1 - p2;
                }
            }
            if (foodSorter == null) {
                return 0;
            }
            return foodSorter.compare(o1, o2);
        }

        public boolean setFilter(String filter) {
            if (filter != null && !filter.equals(this.filter)) {
                this.filter = filter;
                if (this.checkFilter) {
                    this.filterLow = filter.toLowerCase();
                }
                return true;
            }
            return false;
        }

        public boolean setFoodSorter(Comparator<TestD> foodSorter) {
            if (this.foodSorter != foodSorter) {
                this.foodSorter = foodSorter;
                return true;
            }
            return false;
        }

        private int getFilterSort(TestD data) {
            String code = data.getCode();
            String name = data.getName();
            if (code.equals(filterLow) ) {
                return 8;
            }
            if (code.startsWith(filterLow)) {
                return 9;
            }
            return 10;
        }

        public String getFilter() {
            return filter;
        }

        public String getFilterLow() {
            return filterLow;
        }

        public boolean checkFood(TestD labelData) {
            if (checkFilter) {
                if (!labelData.getCode().contains(filterLow) && !labelData.getName().contains(filter) ) {
                    return false;
                }
            }
            return true;
        }

        public boolean needSort() {
            return this.checkFilter || foodSorter != null;
        }

        public boolean isCheckFilter() {
            return checkFilter;
        }

    }
    static class TestD {
        private String code;
        private String name;


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static <T> List<List<T>> dividerList(List<T> list, Comparator<? super T> comparator) {
        List<List<T>> lists = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean isContain = false;
            for (int j = 0; j < lists.size(); j++) {
                if (lists.get(j).size() == 0
                        || comparator.compare(lists.get(j).get(0), list.get(i)) == 0) {
                    lists.get(j).add(list.get(i));
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                List<T> newList = new ArrayList<>();
                newList.add(list.get(i));
                lists.add(newList);
            }
        }
        return lists;
    }

}
