import java.awt.*;

public class DrawingEngines {
    private NumberOfEngines numberOfEngines;
    public NumberOfEngines getNumberOfEngines() {
        return numberOfEngines;
    }
    public void setAmountOfEngines(int amount){
        if(NumberOfEngines.contains(amount)) {
            numberOfEngines = NumberOfEngines.getNumber(amount);
        }
    }
    public void DrawEngines(Graphics g, int x, int y, Color bodyColor) {
        g.setColor(bodyColor);
        g.fillRect(x, y, 10, 10);
    }

    private void drawTwoEngines(Graphics g, int x, int y,  Color bodyColor){
        DrawEngines(g,x + 65, y + 50, bodyColor);
        DrawEngines(g,x + 65, y + 81,bodyColor);
    }
    private void drawFourEngines(Graphics g, int x, int y, Color bodyColor){
        DrawEngines(g,x + 62, y + 10,bodyColor);
        DrawEngines(g,x + 64, y + 101,bodyColor);
        DrawEngines(g,x + 64, y + 30,bodyColor);
        DrawEngines(g,x + 62, y + 121,bodyColor);
    }
    private  void drawSixEngines(Graphics g, int x, int y, Color bodyColor){
        drawFourEngines(g,x,y,bodyColor);
        drawTwoEngines(g,x,y,bodyColor);
    }
    public void SwitchDrawEngines(Graphics g, int x, int y, Color bodyColor){
        switch(getNumberOfEngines()){
            case TWO:
                drawTwoEngines(g,x,y,bodyColor);
                break;
            case FOUR:
                drawFourEngines(g,x,y,bodyColor);
                break;
            case SIX:
                drawSixEngines(g,x,y,bodyColor);
                break;
        }
    }
}
