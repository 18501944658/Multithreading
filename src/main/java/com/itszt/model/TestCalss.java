package com.itszt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestCalss {
    public static void main(String[] args) {
        List<KlassGroup> groupList = Arrays.asList(
                new KlassGroup(new Klass(1), new Klass(2), new Klass(3)),
                new KlassGroup(new Klass(4), new Klass(5), new Klass(6)),
                new KlassGroup(new Klass(7), new Klass(8), new Klass(9)),
                new KlassGroup(new Klass(10))
        );
        List<Klass> result2 = new ArrayList<>();
        for (KlassGroup group : groupList) {
            for (Klass klass : group.getKlassList()) {
                result2.add(klass);
            }
        }
        System.out.println("result2 = " + result2);
        List<Klass> collect = groupList.stream().flatMap(it -> it.getKlassList().stream()).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
