package Entities;

import java.awt.*;
public class EntityStormtrooper extends EntityBaseStormtrooper{
    private Color AdditionalColor;
    public Color getAdditionalColor() {
        return AdditionalColor;
    }
    private boolean Rockets;
    public boolean getRockets() {
        return Rockets;
    }
    private boolean Bombs;
    public boolean getBombs() {
        return Bombs;
    }
    private boolean Engines;
    public boolean getEngines() {return Engines;}
    public  EntityStormtrooper(int speed, float weight, Color bodyColor, Color additionalColor, boolean rockets, boolean bombs, boolean engines)
    {
        super(speed,weight,bodyColor);
        AdditionalColor = additionalColor;
        Rockets = rockets;
        Bombs = bombs;
        Engines = engines;
    }
}
