package io.lance.test;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Author Lance.
 * Date: 2017-08-17 11:22
 * Desc:
 */
public class MainTest {

    @Test
    public void run(){
        List<String> list= Lists.newArrayList();
        list.add("22");
        list.add("fff");

        String s=list.get(0);
        System.out.println(s);
        s="3";
        System.out.println(list);
    }
}
