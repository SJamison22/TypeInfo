package io.stevejamison;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by stevejaminson on 5/25/16.
 */
public class TypeInfoSpec {

    TypeInfo typeInfo;
    io.stevejamison.Test test;

    @Before
    public void init() {
        typeInfo = new TypeInfo();
        test = new io.stevejamison.Test();
    }

    @Test
    public void classImplementsInterfaceTest(){
        boolean expected = true;
        boolean actual = typeInfo.classImplementsInterface(test.getClass(),"testable");
        assertEquals(expected,actual);
    }

    @Test
    public void classImplementsInterfaceFalseTest(){
        boolean expected = false;
        boolean actual = typeInfo.classImplementsInterface(test.getClass(),"TypeInfo");
        assertEquals(expected,actual);
    }

    @Test
    public void getClassHierarchyTest(){
        String expected = "class java.lang.Object\n  class io.stevejamison.TypeInfo\n  ";
        String actual = typeInfo.getClassHierarchy(typeInfo);
        assertEquals(expected,actual);
    }

    @Test
    public void listAllMembersTest(){
        String expected = "quals";
        String actual = typeInfo.listAllMembers(typeInfo).substring(850, 855);
        assertEquals(expected,actual);
    }

    @Test
    public void instantiateClassHierarchyTest(){
        ArrayList<Class> myList = new ArrayList<Class>();
        ArrayList<Object> setup = typeInfo.instantiateClassHierarchy(typeInfo);
        for (int i = 0; i < setup.size(); i++) {
            myList.add(setup.get(i).getClass());
            System.out.println(setup.get(i).getClass());
        }
        Set<Class> mySet = new HashSet<Class>(myList);
        int expected = setup.size();
        int actual = mySet.size();
        assertEquals(expected,actual);

    }
}
