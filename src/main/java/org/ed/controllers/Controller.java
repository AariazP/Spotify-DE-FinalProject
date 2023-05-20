package org.ed.controllers;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.DoubleLinkedList;
import org.ed.application.Main;
import org.ed.model.Song;
import org.ed.patterns.DataFactory;

@Setter
@Getter
public abstract class Controller {

    private Main main;

    private DataFactory data;

}
