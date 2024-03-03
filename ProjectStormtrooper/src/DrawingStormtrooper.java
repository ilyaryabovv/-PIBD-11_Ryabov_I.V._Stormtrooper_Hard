import  java.awt.*;
import java.util.Random;

public class DrawingStormtrooper {
    public EntityStormtrooper EntityStormtrooper;
    public DrawingEngines drawingEngines =null;
    /// <summary>
    /// Ширина окна
    /// </summary>
    private Integer _pictureWidth;

    /// <summary>
    /// Высота окна
    /// </summary>
    private Integer _pictureHeight;

    /// <summary>
    /// Левая координата прорисовки бомбардировщика
    /// </summary>
    private Integer _startPosX;

    /// <summary>
    /// Верхняя кооридната прорисовки бомбардировщика
    /// </summary>
    private Integer _startPosY;

    /// <summary>
    /// Ширина прорисовки бомбардировщика
    /// </summary>
    private final Integer _drawningStormtrooperWidth = 140;

    /// <summary>
    /// Высота прорисовки бомбардировщика
    /// </summary>
    private final Integer _drawningStormtrooperHeight = 135;

    /// <summary>
    /// Инициализация свойств
    /// </summary>
    /// <param name="speed">Скорость</param>
    /// <param name="weight">Вес</param>
    /// <param name="bodyColor">Основной цвет</param>
    /// <param name="additionalColor">Дополнительный цвет</param>
    /// <param name="engines">Признак наличия двигателей</param>
    /// <param name="bombs">Признак наличия бомб</param>
    /// <param name="rockets">Признак наличия ракет</param>
    public void Init(int speed, float weight, Color bodyColor, Color additionalColor,boolean rockets, boolean bombs, boolean engines)
    {
        EntityStormtrooper = new EntityStormtrooper();
        EntityStormtrooper.Init(speed, weight, bodyColor, additionalColor, rockets,bombs ,engines);
        if(engines==true){
            drawingEngines = new DrawingEngines((int)((Math.random()*3)+1)*2);
        }
        _startPosX=null;
        _startPosY=null;
        _pictureWidth = null;
        _pictureHeight = null;
        
    }
    /// <summary>
    /// Установка границ поля
    /// </summary>
    /// <param name="width">Ширина поля</param>
    /// <param name="height">Высота поля</param>
    /// <returns> true - границы заданы, false - проверка не пройдена, нельзя разместить объект в этих размерах</returns>
    public boolean SetPictureSize(int width, int height)
    {
        // TODO проверка, что объект "влезает" в размеры поля
        // если влезает, сохраняем границы и корректируем позицию объекта, если она была уже установлена
        if (width < _drawningStormtrooperWidth || height < _drawningStormtrooperHeight) return false;
        _pictureWidth = width;
        _pictureHeight = height;
        if (_startPosX !=null || _startPosY !=null)
        {
            if (_startPosX + _drawningStormtrooperWidth > _pictureWidth)
            {
                _startPosX = -_drawningStormtrooperWidth + _pictureWidth;
            }
            else if (_startPosX < 0)
            {
                _startPosX = 0;
            }
            if (_startPosY + _drawningStormtrooperHeight > _pictureHeight)
            {
                _startPosY = -_drawningStormtrooperHeight + _pictureHeight;
            }
            else if (_startPosY < 0)
            {
                _startPosY = 0;
            }
        }
        return true;
    }
    /// <summary>
    /// Установка позиции
    /// </summary>
    /// <param name="x">Координата X</param>
    /// <param name="y">Координата Y</param>
    public void SetPosition(int x, int y)
    {
        if (!(_pictureHeight != null && _pictureWidth != null)) return;
        // TODO если при установке объекта в эти координаты, он будет "выходить" за границы формы
        // то надо изменить координаты, чтобы он оставался в этих границах
        if (x + _drawningStormtrooperWidth > _pictureWidth)
        {
            _startPosX = x - (x + _drawningStormtrooperWidth - _pictureWidth);
        }
        else if (x < 0)
        {
            _startPosX = 0;
        }
        else
        {
            _startPosX = x;
        }
        if (y + _drawningStormtrooperHeight > _pictureHeight)
        {
            _startPosY = y - (y + _drawningStormtrooperHeight - _pictureHeight);
        }
        else if (y < 0)
        {
            _startPosY = 0;
        }
        else
        {
            _startPosY = y;
        }
    }

    /// <summary>
    /// Изменение направления перемещения
    /// </summary>
    /// <param name="direction">Направление</param>
    /// <returns>true - перемещене выполнено, false - перемещение невозможно</returns>
    public boolean MoveTransport(DirectionType direction)
    {
        if (EntityStormtrooper == null || _startPosX==null || _startPosY==null)
        {
            return false;
        }
        switch (direction)
        {
            //влево
            case DirectionType.Left:
                if (_startPosX - EntityStormtrooper.Step > 0)
                {
                    _startPosX -= (int)EntityStormtrooper.Step;
                }
                return true;
            //вверх
            case DirectionType.Up:
                if (_startPosY - EntityStormtrooper.Step > 0)
                {
                    _startPosY -= (int)EntityStormtrooper.Step;
                }
                return true;
            // вправо
            case DirectionType.Right:
                //TODO прописать логику сдвига в право
                if (_startPosX + _drawningStormtrooperWidth + EntityStormtrooper.Step < _pictureWidth)
                {
                    _startPosX += (int)EntityStormtrooper.Step;
                };
                return true;
            //вниз
            case DirectionType.Down:
                //TODO прописать логику сдвига в вниз
                if (_startPosY + _drawningStormtrooperHeight + EntityStormtrooper.Step < _pictureHeight)
                {
                    _startPosY += (int)EntityStormtrooper.Step;
                }
                return true;
            default:
                return false;
        }
    }
    /// <summary>
    /// Прорисовка объекта
    /// </summary>
    /// <param name="g"></param>
    public void DrawTransport(Graphics g)
    {
        if (EntityStormtrooper == null || _startPosX==null || _startPosY==null)
        {
            return;
        }
        g.setColor(Color.black);
        //Brush bodyColorBrush = new SolidBrush(EntityStormtrooper.BodyColor);
        //Brush additionalBrush = new SolidBrush(EntityStormtrooper.AdditionalColor);
        //Тело бомбардировщика
        g.drawRect(_startPosX + 20, _startPosY + 60, 120, 20);
        //Задние крылья бомбардировщика
        g.drawLine(_startPosX+ 140, _startPosY+ 30, _startPosX+ 140, _startPosY+ 110);
        g.drawLine(_startPosX+ 120, _startPosY+ 90, _startPosX+ 120, _startPosY+ 80);
        g.drawLine(_startPosX+ 140, _startPosY+ 110, _startPosX + 120, _startPosY + 90);
        g.drawLine(_startPosX+ 140, _startPosY+ 30, _startPosX+ 120, _startPosY+ 50);
        g.drawLine(_startPosX+ 120, _startPosY+ 50, _startPosX+ 120, _startPosY+ 60);
        //Крылья бомбардировщика
        g.drawLine(_startPosX+ 50, _startPosY, _startPosX + 50, _startPosY + 60);
        g.drawLine(_startPosX+ 50, _startPosY + 80, _startPosX + 50, _startPosY + 135);
        g.drawLine(_startPosX+ 50, _startPosY + 135, _startPosX + 60, _startPosY + 135);
        g.drawLine(_startPosX+ 60, _startPosY + 135, _startPosX + 65, _startPosY + 80);
        g.drawLine(_startPosX+ 50, _startPosY, _startPosX + 60, _startPosY);
        g.drawLine(_startPosX+ 60, _startPosY, _startPosX + 65, _startPosY + 60);
        ///Нос бомбардировщика
        Point[] Nose = new Point[3];
        int[] arrX = {_startPosX + 20, _startPosX,_startPosX+20};
        int[] arrY = {_startPosY + 80,_startPosY + 70,_startPosY + 60};
        Polygon poly = new Polygon(arrX,arrY,3);
        g.fillPolygon(poly);
        //Ракеты бомбардировщика
        if (EntityStormtrooper.getRockets())
        {
            g.setColor(EntityStormtrooper.getAdditionalColor());
            g.fillRect( _startPosX + 35, _startPosY + 20, 15, 5);
            g.fillRect( _startPosX + 35, _startPosY + 110, 15, 5);

        }
        //Бомбы бомбардировщика
        if (EntityStormtrooper.getBombs())
        {
            g.setColor(EntityStormtrooper.getAdditionalColor());
            g.fillRect(_startPosX + 40, _startPosY + 40, 10, 10);
            g.fillRect(_startPosX + 40, _startPosY + 90, 10, 10);
        }
        if(EntityStormtrooper.getEngines() && drawingEngines!=null){
            switch(drawingEngines.getNumberOfEngines()){
                case TWO:
                    drawingEngines.DrawDecks(g,_startPosX + 65, _startPosY + 50, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 65, _startPosY + 81, 10, 10,EntityStormtrooper.getBodyColor());
                    break;
                case FOUR:
                    drawingEngines.DrawDecks(g,_startPosX + 62, _startPosY + 10, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 64, _startPosY + 101, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 64, _startPosY + 30, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 62, _startPosY + 121, 10, 10,EntityStormtrooper.getBodyColor());
                    break;
                case SIX:
                    drawingEngines.DrawDecks(g,_startPosX + 62, _startPosY + 10, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 64, _startPosY + 30, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 65, _startPosY + 50, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 65, _startPosY + 81, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 64, _startPosY + 101, 10, 10,EntityStormtrooper.getBodyColor());
                    drawingEngines.DrawDecks(g,_startPosX + 62, _startPosY + 121, 10, 10,EntityStormtrooper.getBodyColor());
                    break;
            }
        }
    }
}
