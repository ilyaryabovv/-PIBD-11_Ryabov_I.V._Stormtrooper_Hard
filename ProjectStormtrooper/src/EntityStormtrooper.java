import java.awt.*;
public class EntityStormtrooper {
    private int Speed;
    public int getSpeed() {
        return Speed;
    }
    private float Weight;
    public float getWeight() {
        return Weight;
    }
    private Color BodyColor;
    public Color getBodyColor() {
        return BodyColor;
    }
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
    public float Step;
    public void Init(int speed, float weight, Color bodyColor, Color additionalColor, boolean rockets, boolean bombs, boolean engines)
    {
        Speed = speed;
        Weight = weight;
        BodyColor = bodyColor;
        AdditionalColor = additionalColor;
        Rockets = rockets;
        Bombs = bombs;
        Engines = engines;
        Step = Speed * 100 / Weight;
    }
}
