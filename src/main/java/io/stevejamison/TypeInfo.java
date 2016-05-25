package io.stevejamison;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by stevejaminson on 5/25/16.
 */
public class TypeInfo {

    private static int testing;
    public String atesting2;

    public boolean classImplementsInterface(Class className, String interfaceName) {
        boolean match = false;
        ArrayList<String> interfaces = new ArrayList<String>();
        for (int i = 0; i < className.getInterfaces().length; i++) {
            interfaces.add(className.getInterfaces()[i].getSimpleName());
        }
        for (String interf : interfaces) {
            if (interf.equalsIgnoreCase(interfaceName)) {
                match = true;
            } else {
                match = false;
            }
        }
        return match;
    }

    public String listAllMembers(Object objectName){
        Class<?> current = objectName.getClass();
        String res = "";
        ArrayList<ClassField> field = new ArrayList<ClassField>();

            while (current != null) {

                /**
                 *Populate Fields
                 */
                for(Field f : current.getDeclaredFields()){
                    int m = f.getModifiers();
                    field.add(new ClassField(f.getName(),Modifier.toString(m),f.getDeclaringClass().getSimpleName()));
                }

                Collections.sort(field, new Comparator<ClassField>() {
                    public int compare(ClassField o1, ClassField o2) {
                        return o1.name.compareToIgnoreCase(o2.name);
                    }
                });

                for(ClassField cf : field) {
                    cf.name = String.format("%-20s", "Field : " + cf.name);
                    cf.className = String.format("%-20s", "Class : " + cf.className);
                    cf.modifiers = String.format("%-40s","Modifiers : " + cf.modifiers);
                }

                for(int i = 0; i < current.getDeclaredFields().length; i++){
                    res += field.get(i).className + field.get(i).modifiers + field.get(i).name + "\n";
                }

                /**
                 *Populate Constructors
                 */
            field.clear();
                for(Constructor c : current.getDeclaredConstructors()){
                    int m = c.getModifiers();
                    field.add(new ClassField(c.getName(),Modifier.toString(m),c.getDeclaringClass().getSimpleName()));
                }

                Collections.sort(field, new Comparator<ClassField>() {
                    public int compare(ClassField o1, ClassField o2) {
                        return o1.name.compareToIgnoreCase(o2.name);
                    }
                });

                for(ClassField cf : field) {
                    cf.name = String.format("%-20s", "Constructor : " + cf.name);
                    cf.className = String.format("%-20s", "Class : " + cf.className);
                    cf.modifiers = String.format("%-40s","Modifiers : " + cf.modifiers);
                }

                for(int i = 0; i < current.getDeclaredConstructors().length; i++){
                    res += field.get(i).className + field.get(i).modifiers + field.get(i).name + "\n";
                }

                /**
                 *Populate Methods
                 */

                field.clear();
                for(Method meth : current.getDeclaredMethods()){
                    int m = meth.getModifiers();
                    field.add(new ClassField(meth.getName(),Modifier.toString(m),meth.getDeclaringClass().getSimpleName()));
                }

                Collections.sort(field, new Comparator<ClassField>() {
                    public int compare(ClassField o1, ClassField o2) {
                        return o1.name.compareToIgnoreCase(o2.name);
                    }
                });

                for(ClassField cf : field) {
                    cf.name = String.format("%-20s", "Method : " + cf.name);
                    cf.className = String.format("%-20s", "Class : " + cf.className);
                    cf.modifiers = String.format("%-40s","Modifiers : " + cf.modifiers);
                }

                for(int i = 0; i < current.getDeclaredMethods().length; i++){
                    res += field.get(i).className + field.get(i).modifiers + field.get(i).name + "\n";
                }

                /**
                 *Move to next superclass
                 */
                current = current.getSuperclass();
                res += "\n";
            }
        return res;
        }


    public String getClassHierarchy(Object objectName){
        String res = "";
        Class current = objectName.getClass();
        ArrayList<Class> hierarchy = new ArrayList<Class>();
        hierarchy.add(current);
        if(objectName.getClass().getSuperclass() != null){
            current = objectName.getClass().getSuperclass();
            hierarchy.add(current);
        }

        for (int i = hierarchy.size()-1; i >= 0; i--){
            res += hierarchy.get(i) + "\n  ";
        }
        return res;
    }

    public ArrayList<Object> instantiateClassHierarchy(Object objectName){
        Class current = objectName.getClass();
        ArrayList<Class> hierarchy = new ArrayList<Class>();
        ArrayList<Object> result = new ArrayList<Object>();
        hierarchy.add(current);
        if(objectName.getClass().getSuperclass() != null){
            current = objectName.getClass().getSuperclass();
            hierarchy.add(current);
        }

        for (int i = hierarchy.size()-1; i >= 0; i--){
            try {
                result.add(hierarchy.get(i).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
