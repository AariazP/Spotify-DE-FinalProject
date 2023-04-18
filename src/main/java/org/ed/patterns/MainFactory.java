package org.ed.patterns;

import org.ed.application.Main;

public class MainFactory {

    public static Main INSTANCE;


    public static Main getMain(){
        return INSTANCE;
    }

    public static void setMain(Main main){
        INSTANCE = main;
    }


}
