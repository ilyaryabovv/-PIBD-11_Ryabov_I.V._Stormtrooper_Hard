package Entities;

import java.awt.*;
public class EntityStormtrooper extends EntityBaseStormtrooper{
    private Color AdditionalColor;
    public void setAdditionalColor(Color additionalColor){
        AdditionalColor = additionalColor;
    }
    public Color getAdditionalColor() {
        return AdditionalColor;
    }
    private boolean Rockets;
    public void setRockets ( boolean rockets){
        Rockets= rockets;
    }
    public boolean getRockets() {
        return Rockets;
    }
    private boolean Bombs;
    public void setBombs (boolean bombs){
        Bombs= bombs;
    }
    public boolean getBombs() {
        return Bombs;
    }
    private boolean Engines;
    public void setEngines (boolean engines){
        Engines = engines;
    }
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
