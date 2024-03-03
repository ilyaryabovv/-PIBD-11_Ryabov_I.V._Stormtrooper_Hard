import java.awt.*;

public class DrawingEngines {
    private NumberOfEngines numberOfEngines;

    public NumberOfEngines getNumberOfEngines() {
        return numberOfEngines;
    }
    DrawingEngines (int a){
        setAmountOfEngines(a);
    }
    public void setAmountOfEngines(int amount){
        if(NumberOfEngines.contains(amount)) {
            numberOfEngines = NumberOfEngines.getNumber(amount);
        }
    }
    public void DrawDecks(Graphics g, int x, int y, int width, int height, Color bodyColor) {
        g.setColor(bodyColor);
        g.fillRect(x, y, width, height);
    }
}
