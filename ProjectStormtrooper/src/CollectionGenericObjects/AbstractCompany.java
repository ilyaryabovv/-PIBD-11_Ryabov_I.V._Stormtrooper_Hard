package CollectionGenericObjects;
import Drawnings.DrawingBaseStormtrooper;

import java.awt.*;

public abstract class AbstractCompany {
    protected int _placeSizeWidth = 220;
    protected  int _placeSizeHeight = 155;
    protected int _pictureWidth;
    protected  int _pictureHeight;
    public ICollectionGenericObjects<DrawingBaseStormtrooper> _collection = null;
    private int GetMaxCount() {
        return _pictureWidth * _pictureHeight / (_placeSizeWidth * _placeSizeHeight);

    }
    public AbstractCompany(int picWidth, int picHeight, ICollectionGenericObjects<DrawingBaseStormtrooper> collection)
    {
        _pictureWidth = picWidth;
        _pictureHeight = picHeight;
        _collection = collection;
        _collection.SetMaxCount(GetMaxCount());
    }
    //Перегрузок нет
    public DrawingBaseStormtrooper GetRandomObject()
    {
        return _collection.Get((int)(Math.random()*GetMaxCount() + 0));
    }
    public void SetPosition()
    {
        SetObjectsPosition();
    }
    public abstract void DrawBackgound(Graphics graphics);
    protected abstract void SetObjectsPosition();
}