package com.example;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {
//        List<TestD> l = new ArrayList<>();
//
//        TestD d = new TestD();
//        d.setName("abc");
//        d.setCode("def");
//        l.add(d);
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("lhc");
//        l.add(d);
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("dmq");
//        l.add(d);
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("lbb");
//        l.add(d);
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("mq");
//        l.add(d);
//
//
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("hmq");
//        l.add(d);
//
//
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("abmq");
//        l.add(d);
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("bcmq");
//        l.add(d);
//        d = new TestD();
//        d.setName("cba");
//        d.setCode("amqb");
//        l.add(d);
//        System.out.println("================");
//        FilterFoodSorter sorter = new FilterFoodSorter();
//        List<TestD> ml = new ArrayList<>();
//        sorter.setFilter("mq");
//        for (int i = 0; i < l.size(); i++) {
//            if (sorter.checkFood(l.get(i))) {
//                ml.add(l.get(i));
//            }
//        }
//        Comparator<TestD> codeSorter = new Comparator<TestD>() {
//
//            @Override
//            public int compare(TestD o1, TestD o2) {
//                return o1.getCode().(o2.getCode());
//            }
//
//            @Override
//            public String toString() {
//                return "按助记码";
//            }
//        };
//
//        sorter.setFoodSorter(codeSorter);
//        TestD[] mdatas = ml.toArray(new TestD[ml.size()]);
////        Arrays.sort(mdatas, sorter);
//        Collections.sort(ml, sorter);
//        myprint(l);

//        Map<String, String> map = new HashMap<>();
//        map.put("aaa", "bbb");
//        System.out.println(map.size());
//        map.clear();
//        System.out.println(map.size());
//        String a ;
//        String b = "a";
//        System.out.println(b.equals(""));

//
//        List<TestD> list = new ArrayList<>();
//        List<TestD> list2 = new ArrayList<>();
//
//        TestD d1 = new TestD(111, "bbb", 1);
//        TestD d2 = new TestD(222, "bbb", 1);
//        TestD d3 = new TestD(333, "bbb", 1);
//        TestD d4 = new TestD(123, "fff", 12);
//
//        list.add(d1);
//
//        list2.add(d2);
//        list2.add(d1);
//        list2.add(d3);
//        list2.add(d4);
//
//        Comparator<TestD> com = new Comparator<TestD>() {
//            @Override
//            public int compare(TestD d1, TestD d2) {
//                if (d1.getCode() > d2.getCode()) {
//                    return -1;
//                } else if (d1.getCode() < d2.getCode()) {
//                    return 1;
//                } else
//                    return 0;
//            }
//        };
//        Collections.sort(list2, com);
//        System.out.println(new Gson().toJson(list2));

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        strings.add("ddd");
        strings.add("eee");
        strings.add("fff");
        strings.add("mmm");
        int j = 0;
        for (int i = 0; i < strings.size(); i++) {
            if(strings.get(i).equals("bbb")){
                strings.set(i,"mmm");
                continue;
            }
            if(strings.get(i).equals("mmm")){
                j++;
            }
        }
        System.out.println(j);

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
