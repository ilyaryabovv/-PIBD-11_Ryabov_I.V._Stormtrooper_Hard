package Drawnings;
import Drawnings.Engines.IDrawingEngines;
import  Entities.*;
import java.awt.*;

public class DrawingBaseStormtrooper {
    public EntityBaseStormtrooper EntityBaseStormtrooper;
    public IDrawingEngines drawingEngines =null;
    protected Integer _pictureWidth;
    protected Integer _pictureHeight;
    protected Integer _startPosX;
    protected Integer _startPosY;
    public  Integer getPosX(){return  _startPosX;}
    public  Integer getPosY(){return  _startPosY;}
    private Integer _drawningStormtrooperWidth = 140;
    public  Integer getWidth(){return  _drawningStormtrooperWidth;}
    private Integer _drawningStormtrooperHeight = 135;
    public  Integer getHeight(){return  _drawningStormtrooperHeight;}

    protected DrawingBaseStormtrooper(){
        _pictureWidth=null;
        _pictureHeight=null;
        _startPosX=null;
        _startPosY=null;
    }
    public DrawingBaseStormtrooper(int speed, float weight, Color bodyColor) {
        super();
        EntityBaseStormtrooper = new EntityBaseStormtrooper(speed, weight, bodyColor);
    }
    public DrawingBaseStormtrooper(EntityBaseStormtrooper entityBaseStormtrooper, IDrawingEngines drawingEngines){
        this.EntityBaseStormtrooper = entityBaseStormtrooper;
        this.drawingEngines = drawingEngines;
    }

    public boolean SetPictureSize(int width, int height) {
        // TODO проверка, что объект "влезает" в размеры поля
        // если влезает, сохраняем границы и корректируем позицию объекта, если она была уже установлена
        if (width < _drawningStormtrooperWidth || height < _drawningStormtrooperHeight) return false;
        _pictureWidth = width;
        _pictureHeight = height;
        if (_startPosX != null || _startPosY != null) {
            if (_startPosX + _drawningStormtrooperWidth > _pictureWidth) {
                _startPosX = -_drawningStormtrooperWidth + _pictureWidth;
            } else if (_startPosX < 0) {
                _startPosX = 0;
            }
            if (_startPosY + _drawningStormtrooperHeight > _pictureHeight) {
                _startPosY = -_drawningStormtrooperHeight + _pictureHeight;
            } else if (_startPosY < 0) {
                _startPosY = 0;
            }
        }
        return true;
    }

    public void SetPosition(int x, int y) {
        if (!(_pictureHeight != null && _pictureWidth != null)) return;
        // TODO если при установке объекта в эти координаты, он будет "выходить" за границы формы
        // то надо изменить координаты, чтобы он оставался в этих границах
        if (x + _drawningStormtrooperWidth > _pictureWidth) {
            _startPosX = x - (x + _drawningStormtrooperWidth - _pictureWidth);
        } else if (x < 0) {
            _startPosX = 0;
        } else {
            _startPosX = x;
        }
        if (y + _drawningStormtrooperHeight > _pictureHeight) {
            _startPosY = y - (y + _drawningStormtrooperHeight - _pictureHeight);
        } else if (y < 0) {
            _startPosY = 0;
        } else {
            _startPosY = y;
        }
    }

    public boolean MoveTransport(DirectionType direction) {
        if (EntityBaseStormtrooper == null || _startPosX == null || _startPosY == null) {
            return false;
        }
        switch (direction) {
            //влево
            case DirectionType.Left:
                if (_startPosX - EntityBaseStormtrooper.Step > 0) {
                    _startPosX -= (int) EntityBaseStormtrooper.Step;
                }
                return true;
            //вверх
            case DirectionType.Up:
                if (_startPosY - EntityBaseStormtrooper.Step > 0) {
                    _startPosY -= (int) EntityBaseStormtrooper.Step;
                }
                return true;
            // вправо
            case DirectionType.Right:
                //TODO прописать логику сдвига в право
                if (_startPosX + _drawningStormtrooperWidth + EntityBaseStormtrooper.Step < _pictureWidth) {
                    _startPosX += (int) EntityBaseStormtrooper.Step;
                }
                return true;
            //вниз
            case DirectionType.Down:
                //TODO прописать логику сдвига в вниз
                if (_startPosY + _drawningStormtrooperHeight + EntityBaseStormtrooper.Step < _pictureHeight) {
                    _startPosY += (int) EntityBaseStormtrooper.Step;
                }
                return true;
            default:
                return false;
        }
    }
    public void DrawTransport(Graphics g) {
        if (EntityBaseStormtrooper == null || _startPosX == null || _startPosY == null) {
            return;
        }
        g.setColor(Color.black);
        //Тело бомбардировщика
        g.drawRect(_startPosX + 20, _startPosY + 60, 120, 20);
        //Задние крылья бомбардировщика
        g.drawLine(_startPosX + 140, _startPosY + 30, _startPosX + 140, _startPosY + 110);
        g.drawLine(_startPosX + 120, _startPosY + 90, _startPosX + 120, _startPosY + 80);
        g.drawLine(_startPosX + 140, _startPosY + 110, _startPosX + 120, _startPosY + 90);
        g.drawLine(_startPosX + 140, _startPosY + 30, _startPosX + 120, _startPosY + 50);
        g.drawLine(_startPosX + 120, _startPosY + 50, _startPosX + 120, _startPosY + 60);
        //Крылья бомбардировщика
        g.drawLine(_startPosX + 50, _startPosY, _startPosX + 50, _startPosY + 60);
        g.drawLine(_startPosX + 50, _startPosY + 80, _startPosX + 50, _startPosY + 135);
        g.drawLine(_startPosX + 50, _startPosY + 135, _startPosX + 60, _startPosY + 135);
        g.drawLine(_startPosX + 60, _startPosY + 135, _startPosX + 65, _startPosY + 80);
        g.drawLine(_startPosX + 50, _startPosY, _startPosX + 60, _startPosY);
        g.drawLine(_startPosX + 60, _startPosY, _startPosX + 65, _startPosY + 60);
        ///Нос бомбардировщика
        g.setColor(EntityBaseStormtrooper.getBodyColor());
        Point[] Nose = new Point[3];
        int[] arrX = {_startPosX + 20, _startPosX, _startPosX + 20};
        int[] arrY = {_startPosY + 80, _startPosY + 70, _startPosY + 60};
        Polygon poly = new Polygon(arrX, arrY, 3);
        g.fillPolygon(poly);
    }
}
