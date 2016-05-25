package io.stevejamison;

/**
 * Created by stevejaminson on 5/25/16.
 */
public class ClassField {

    String name;
    String modifiers;
    String className;

    public ClassField(String name, String mod, String className){
        this.name = name;
        this.modifiers = mod;
        this.className = className;
    }
}
