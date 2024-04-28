package CollectionGenericObjects;

import Drawnings.DrawingBaseStormtrooper;

import java.lang.reflect.Array;


public class MassiveGenericObjects<T> implements ICollectionGenericObjects<T>{
    private T[] _collection = null;
    private int Count;
    public void SetMaxCount(int size) {
        if (size > 0) {
            if (_collection == null) {
                _collection = (T[]) Array.newInstance((Class) DrawingBaseStormtrooper.class, size);
                Count = size;
            }
        }
    }
    @Override
    public int getCount() {
        return Count;
    }
    @Override
    public int Insert(T obj) {
        int index = 0;
        while (index < getCount())
        {
            if (_collection[index] == null)
            {
                _collection[index] = obj;
                return index;
            }
            ++index;
        }
        return -1;
    }
    @Override
    public T Remove(int position) {
        if (position >= getCount() || position < 0)
            return null;
        T obj = (T) _collection[position];
        _collection[position] = null;
        return obj;
    }
    @Override
    public T Get(int position) {
        if (position >= getCount() || position < 0) return null;
        return (T) _collection[position];
    }
}
