package Entities;

import java.awt.*;

public class EntityBaseStormtrooper
{
    private int Speed;
    public void setSpeed(int speed){
        Speed = speed;
    }
    private float Weight;
    public void setWeight ( float weight){
        Weight= weight;
    }
    private Color BodyColor;
    public void setBodyColor (Color bodyColor){
        BodyColor = bodyColor;
    }
    public Color getBodyColor() {return BodyColor;}
    public double Step;
    public  EntityBaseStormtrooper(int speed, float weight, Color bodyColor)
    {
        Speed = speed;
        Weight = weight;
        BodyColor = bodyColor;
        Step=speed*100/weight;
    }
}
