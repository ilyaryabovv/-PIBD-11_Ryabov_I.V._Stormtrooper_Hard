package  MovementStrategy;
public interface IMoveableObject
{
    ObjectParameters GetObjectPosition ();
    int getStep();
    boolean TryMoveObject(MovementDirection direction);
}