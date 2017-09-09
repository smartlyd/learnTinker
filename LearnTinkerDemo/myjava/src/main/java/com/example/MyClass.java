package com.example;


import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MyClass {

    public enum Color {
        RED,
        BLUD,
        GREEN
    }

    public static void main(String[] args) {
        List<TestD> list = new ArrayList<>();
        TestD d = new TestD(1, "a", 1);
        list.add(d);
        d = new TestD(2, "b", 2);
        list.add(d);
        d = new TestD(3, "c", 3);
        list.add(d);
        for (TestD t : list) {
            if(t.getName().equals("b")){
                TestD temp = new TestD(4,"abc",4);
                t = temp;
            }
        }

        System.out.println(new Gson().toJson(list));
    }

    public static String timeToDateStrLin(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String format = sdf.format(new Date(time));
        String[] split = format.split(" ");
        if (split != null && split.length == 2) {
            format = split[0] + "\n" + split[1];
        }
        return format;
    }

    public static long getStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return start.getTime();
    }

    public static void myprint(List<TestD> list) {
        Iterator it = list.iterator(); // 得到迭代器，用于遍历list中的所有元素
        while (it.hasNext()) {// 如果迭代器中有元素，则返回true
            System.out.println("\t" + it.next());// 显示该元素
        }
    }


//    public static class FilterFoodSorter implements Comparator<TestD> {
//
//        @SuppressWarnings("unused")
//        private final static String TAG = "FilterFoodSorter";
//        private String filter = "";
//        private String filterLow = "";
//        /**
//         * 菜品排序
//         */
//        private Comparator<TestD> foodSorter;
//        private boolean checkFilter = true;
//
//
//        @Override
//        public int compare(TestD o1, TestD o2) {
//            if (checkFilter) {
//                int p1 = getFilterSort(o1);
//                int p2 = getFilterSort(o2);
//                System.out.println("============");
//                System.out.println("o1" + new Gson().toJson(o1) + "\n" + "p1=" + p1);
//                System.out.println("o2" + new Gson().toJson(o2) + "\n" + "p2=" + p2);
//                System.out.println("============");
//                if (p1 > p2) {
//                    return 1;
//                } else if (p1 < p2) {
//                    return -1;
//                } else
//                    return 0;
//            }
//            return foodSorter.compare(o1, o2);
//        }
//
//        public boolean setFilter(String filter) {
//            if (filter != null && !filter.equals(this.filter)) {
//                this.filter = filter;
//                if (this.checkFilter) {
//                    this.filterLow = filter.toLowerCase();
//                }
//                return true;
//            }
//            return false;
//        }
//
//        public boolean setFoodSorter(Comparator<TestD> foodSorter) {
//            if (this.foodSorter != foodSorter) {
//                this.foodSorter = foodSorter;
//                return true;
//            }
//            return false;
//        }
//
//        private int getFilterSort(TestD data) {
//            String code = data.getCode();
//            String name = data.getName();
//            if (code.equals(filterLow)) {
//                return 8;
//            }
//            if (code.startsWith(filterLow)) {
//                return 9;
//            }
//            return 10;
//        }
//
//        public String getFilter() {
//            return filter;
//        }
//
//        public String getFilterLow() {
//            return filterLow;
//        }
//
//        public boolean checkFood(TestD labelData) {
//            if (checkFilter) {
//                if (!labelData.getCode().contains(filterLow) && !labelData.getName().contains(filter)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//
//        public boolean needSort() {
//            return this.checkFilter || foodSorter != null;
//        }
//
//        public boolean isCheckFilter() {
//            return checkFilter;
//        }
//
//    }

    static class TestD {


        private long code;
        private String name;
        private int num;


        public TestD(long code, String name, int num) {
            this.code = code;
            this.name = name;
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public long getCode() {
            return code;
        }

        public void setCode(long code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public boolean equals(Object o) {
            if (this.code == ((TestD) o).getCode())
                return false;
            if (!this.name.equals(((TestD) o).getName()))
                return false;
            if (this.num != ((TestD) o).getNum())
                return false;
            return true;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
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
