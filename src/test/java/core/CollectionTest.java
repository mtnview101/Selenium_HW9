package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;

public class CollectionTest {
public static void main(String[] args) {
    List<String> zones = new ArrayList<>();
    List<String> sorted = new ArrayList<>();
    for (Integer i=100;i>0;i--){
    	zones.add(i.toString());
    	sorted.add(i.toString());
    }

    Collections.sort(sorted);
    System.out.println("original: "+zones);
    System.out.println("sorted: "+sorted);
    Assert.assertEquals(zones,sorted);
}
}
