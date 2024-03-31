package Drawnings.Engines;

import java.awt.*;

public class DrawingTriangleEngines implements IDrawingEngines {
    private NumberOfEngines numberOfEngines;
    @Override
    public NumberOfEngines getNumberOfEngines() {
        return numberOfEngines;
    }
    @Override
    public void setAmountOfEngines(int amount){
        if(NumberOfEngines.contains(amount)) {
            numberOfEngines = NumberOfEngines.getNumber(amount);
        }
    }
    @Override
    public void DrawEngines(Graphics g, int x, int y, Color bodyColor) {
        g.setColor(bodyColor);
        Point[] Nose = new Point[3];
        int[] arrX = {x,x,x+10};
        int[] arrY = {y,y+12,y+6};
        Polygon poly = new Polygon(arrX, arrY, 3);
        g.fillPolygon(poly);
    }
    @Override
    public void drawTwoEngines(Graphics g, int x, int y,  Color bodyColor){
        DrawEngines(g,x + 65, y + 50, bodyColor);
        DrawEngines(g,x + 65, y + 81,bodyColor);
    }
    public @Override
    void drawFourEngines(Graphics g, int x, int y, Color bodyColor){
        DrawEngines(g,x + 62, y + 10,bodyColor);
        DrawEngines(g,x + 64, y + 101,bodyColor);
        DrawEngines(g,x + 64, y + 30,bodyColor);
        DrawEngines(g,x + 62, y + 121,bodyColor);
    }
    @Override
    public   void drawSixEngines(Graphics g, int x, int y, Color bodyColor){
        drawFourEngines(g,x,y,bodyColor);
        drawTwoEngines(g,x,y,bodyColor);
    }
    public void SwitchDrawEngines(Graphics g, int x, int y, Color bodyColor){
        switch(getNumberOfEngines()){
            case NumberOfEngines.TWO:
                drawTwoEngines(g,x,y,bodyColor);
                break;
            case NumberOfEngines.FOUR:
                drawFourEngines(g,x,y,bodyColor);
                break;
            case NumberOfEngines.SIX:
                drawSixEngines(g,x,y,bodyColor);
                break;
        }
    }
}

