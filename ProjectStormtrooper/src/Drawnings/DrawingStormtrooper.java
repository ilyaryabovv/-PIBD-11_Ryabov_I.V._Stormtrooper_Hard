package Drawnings;
import Drawnings.Engines.DrawingEngines;
import Drawnings.Engines.DrawingOvalEngines;
import Drawnings.Engines.DrawingTriangleEngines;
import Drawnings.Engines.IDrawingEngines;
import Entities.*;
import  java.awt.*;

public class DrawingStormtrooper extends  DrawingBaseStormtrooper{

    public DrawingStormtrooper(int speed, float weight, Color bodyColor, Color additionalColor,boolean rockets, boolean bombs, boolean engines) {
        EntityBaseStormtrooper = new EntityStormtrooper(speed, weight, bodyColor, additionalColor, rockets, bombs, engines);
        _startPosX=null;
        _startPosY=null;
        _pictureWidth = null;
        _pictureHeight = null;
    }
    public DrawingStormtrooper(EntityStormtrooper entityStormtrooper, IDrawingEngines drawingEngines){
        this.EntityBaseStormtrooper=entityStormtrooper;
        this.drawingEngines = drawingEngines;
    }

    @Override
    public void DrawTransport(Graphics g)
    {
        if (EntityBaseStormtrooper == null ||!(EntityBaseStormtrooper instanceof EntityStormtrooper entityStormtrooper) || _startPosX==null || _startPosY==null)
        {
            return;
        }
        super.DrawTransport(g);
        //Ракеты бомбардировщика
        if (entityStormtrooper.getRockets())
        {
            g.setColor(entityStormtrooper.getAdditionalColor());
            g.setColor(entityStormtrooper.getAdditionalColor());
            g.fillRect( _startPosX + 35, _startPosY + 20, 15, 5);
            g.fillRect( _startPosX + 35, _startPosY + 110, 15, 5);
        }
        //Бомбы бомбардировщика
        if (entityStormtrooper.getBombs())
        {
            g.setColor(entityStormtrooper.getAdditionalColor());
            g.fillRect(_startPosX + 40, _startPosY + 40, 10, 10);
            g.fillRect(_startPosX + 40, _startPosY + 90, 10, 10);
        }
        if(entityStormtrooper.getEngines() && drawingEngines!=null){
            drawingEngines.SwitchDrawEngines(g, _startPosX, _startPosY, EntityBaseStormtrooper.getBodyColor());
        }
    }
}
