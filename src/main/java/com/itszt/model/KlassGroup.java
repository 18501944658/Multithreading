package com.itszt.model;

import java.util.ArrayList;
import java.util.List;

public class KlassGroup {
    private List<Klass> group = new ArrayList<>();

    public KlassGroup(Klass... objList) {
        for (Klass item : objList) {
            this.group.add(item);
        }
    }

    public List<Klass> getKlassList() {
        return group;
    }
}
