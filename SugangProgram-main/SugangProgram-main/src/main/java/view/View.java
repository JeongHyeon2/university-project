//package view;
//
//import java.util.List;
//import java.util.Map;
//
//public class View<E> {
//
//    public void readAll(List<E> dtoList) {
//        dtoList.stream().forEach(v -> System.out.println(v.toString()));
//    }
//
//    public void readTest(List<Map<String, Object>> mapList) {
//        mapList.stream().forEach(v-> System.out.println(v.toString()));
//    }
//
//}
package view;

import java.util.List;
import java.util.Map;

public class View<E> {

    public String readAll(List<E> dtos)
    {
        StringBuilder sb = new StringBuilder();
        dtos.stream().forEach(v -> sb.append("&amp"+v.toString()+"&amp"));

        return sb.toString();
    }

}

