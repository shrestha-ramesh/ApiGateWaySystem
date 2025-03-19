package com.products.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MementoTakeCare{
    private final List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento memento){
        mementoList.add(memento);
    }

    public List<Memento> getMementoList(){
        return mementoList;
    }
}
