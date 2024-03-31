package  MovementStrategy;
public class ObjectParameters
{
    private  int _x;
    private  int  _y;
    public  int  _width;
    private  int _height;
    public int TopBorder =_y;
    public int LeftBorder = _x;
    public int RightBorder = _x + _width;
    public int DownBorder = _y + _height;
    public int ObjectMiddleHorizontal;
    public int ObjectMiddleVertical;
    public ObjectParameters(int x, int y, int width, int height)
    {
        _x = x;
        _y = y;
        _width = width;
        _height = height;
        ObjectMiddleHorizontal = _x + _width/ 2;
        ObjectMiddleVertical = _y + _height/ 2;

    }
}