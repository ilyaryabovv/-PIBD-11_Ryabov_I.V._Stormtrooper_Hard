package  MovementStrategy;
public class MoveToBorder extends AbstractStrategy
{
    @Override
    protected  boolean IsTargetDestinaion()
    {
        ObjectParameters objParams = GetObjectParameters();
        if (objParams == null)
        {
            return false;
        }
        return objParams.LeftBorder - GetStep() <= 0 &&
                objParams.RightBorder + GetStep() >= FieldWidth &&
                objParams.TopBorder - GetStep() <= 0
                && objParams.ObjectMiddleVertical + GetStep() >= FieldHeight;
    }
    @Override
    protected  void MoveToTarget()
    {
        ObjectParameters objParams = GetObjectParameters();
        if (objParams == null) {
            return;
        }
        int x = objParams.RightBorder;
        if (x + GetStep() < FieldWidth)MoveRight();
        int y = objParams.DownBorder;
        if (y + GetStep() < FieldHeight) MoveDown();
    }
}
