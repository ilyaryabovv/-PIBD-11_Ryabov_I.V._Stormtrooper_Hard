package  MovementStrategy;
import Drawnings.*;
import Entities.EntityBaseStormtrooper;
public class MoveableStormtrooper implements IMoveableObject
{
    private  DrawingBaseStormtrooper _stormtrooper ;

    public MoveableStormtrooper(DrawingBaseStormtrooper stormtrooper)
    {
        _stormtrooper = stormtrooper;
    }
    @Override
    public ObjectParameters GetObjectPosition() {
        if (_stormtrooper == null || _stormtrooper.EntityBaseStormtrooper == null || _stormtrooper.getPosX()==null || _stormtrooper.getPosY()==null) {
            return null;
        }
        return new ObjectParameters(_stormtrooper.getPosX(), _stormtrooper.getPosY(), _stormtrooper.getWidth(), _stormtrooper.getHeight());
    }
    @Override
    public int getStep(){ return (int)(_stormtrooper.EntityBaseStormtrooper.Step);}
    @Override
    public boolean TryMoveObject(MovementDirection direction)
    {
        if (_stormtrooper == null || _stormtrooper.EntityBaseStormtrooper == null)
        {
            return false;
        }
        return _stormtrooper.MoveTransport(GetDirectionType(direction));
    }
    private static DirectionType GetDirectionType(MovementDirection direction) {
        switch (direction) {
            case Left:
                return DirectionType.Left;
            case Right:
                return DirectionType.Right;
            case Up:
                return DirectionType.Up;
            case Down:
                return DirectionType.Down;
            default:
                return DirectionType.Unknow;
        }
    }
}


