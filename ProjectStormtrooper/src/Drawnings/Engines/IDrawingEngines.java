package Drawnings.Engines;

import java.awt.*;

public interface IDrawingEngines {
    void setAmountOfEngines(int amount);

    NumberOfEngines getNumberOfEngines();

    void DrawEngines(Graphics g, int x, int y, Color bodyColor);

    void  drawTwoEngines(Graphics g, int x, int y,  Color bodyColor);
    void drawFourEngines(Graphics g, int x, int y,  Color bodyColor);
    void drawSixEngines(Graphics g, int x, int y,  Color bodyColor);
    void SwitchDrawEngines(Graphics g, int startPosX, int startPosY, Color bodyColor);

}
