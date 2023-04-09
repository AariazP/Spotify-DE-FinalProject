package org.ed.utilities;

import javafx.scene.image.Image;

import java.util.Objects;

public class ViewUtilities {


    public static final String PAUSE = "/images/pause.png";
    public static final String PLAY = "/images/play.png";

    public static Image getIcon(String logo) {
        return new Image(Objects.requireNonNull(ViewUtilities.class.getResourceAsStream("/images/" + logo + ".png")));
    }
}
