package Entities;

import java.awt.*;

public class EntityBaseStormtrooper
{
    private int Speed;
    private float Weight;
    private Color BodyColor;
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
