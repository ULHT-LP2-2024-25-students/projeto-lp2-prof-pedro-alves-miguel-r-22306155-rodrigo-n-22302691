package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.HashMap;

public class BoardItems {

    Creature creature;
    Equipment equipment;


    //Construtores
    public BoardItems(Creature creature) {
        this.creature = creature;
    }

    public BoardItems(Equipment equipment) {
        this.equipment = equipment;
    }
}
