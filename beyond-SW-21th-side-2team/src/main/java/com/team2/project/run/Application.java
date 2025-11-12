package com.team2.project.run;

import com.team2.project.controller.SubwayManager;

import java.util.Arrays;
import java.util.List;

// Application.java
public class Application {
    public static void main(String[] args) {
        SubwayManager sm = new SubwayManager();
        sm.setUp();

        System.out.println(sm.bfs(6,11));
        //System.out.println(Arrays.toString(sm.bfs(6, 11)));
    }
}

