package com.team2.project.run;

import com.team2.project.controller.SubwayManager;
import com.team2.project.view.SubwayMenu;

import java.util.Arrays;
import java.util.List;

// Application.java
public class Application {
    public static void main(String[] args) {
        SubwayManager sm = new SubwayManager();
        SubwayMenu menu = new SubwayMenu();
        menu.mainMenu();
    }
}

