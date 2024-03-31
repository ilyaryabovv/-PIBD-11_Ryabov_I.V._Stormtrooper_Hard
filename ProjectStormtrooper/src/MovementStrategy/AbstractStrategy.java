package  MovementStrategy;
public abstract class AbstractStrategy
{
    private IMoveableObject _moveableObject;
    private StrategyStatus _state = StrategyStatus.NotInit;
    public int FieldWidth;
    public int FieldHeight;
    public StrategyStatus GetStatus (){  return _state; }
    public void SetData(IMoveableObject moveableObject, int width, int height)
    {
        if (moveableObject == null)
        {
            _state = StrategyStatus.NotInit;
            return;
        }
        _state = StrategyStatus.InProgress;
        _moveableObject = moveableObject;
        FieldWidth = width;
        FieldHeight = height;
    }

    public void MakeStep()
    {
        if (_state != StrategyStatus.InProgress)
        {
            return;
        }
        if (IsTargetDestinaion())
        {
            _state = StrategyStatus.Finish;
            return;
        }
        MoveToTarget();
    }

    protected boolean MoveLeft() {return  MoveTo(MovementDirection.Left);}
    protected boolean MoveRight() {return MoveTo(MovementDirection.Right);}
    protected boolean MoveUp(){return MoveTo(MovementDirection.Up);}
    protected boolean MoveDown(){return MoveTo(MovementDirection.Down);}

    protected ObjectParameters GetObjectParameters (){return  _moveableObject.GetObjectPosition();}
    protected int GetStep(){
    if (_state != StrategyStatus.InProgress)
    {
        return -1;
    }
    return _moveableObject.getStep();
    }
    protected abstract void MoveToTarget();
    protected abstract boolean IsTargetDestinaion();
    private boolean MoveTo(MovementDirection movementDirection) {
        if (_state != StrategyStatus.InProgress) {
            return false;
        }
        if (_moveableObject.TryMoveObject(movementDirection)) {
            return _moveableObject.TryMoveObject(movementDirection);
        }
        return  false;
    }
}