package CollectionGenericObjects;

import java.util.ArrayList;
import java.util.List;
public class ListGenericObjects<T> implements ICollectionGenericObjects<T> {
    private List<T> _collection;
    private int _maxCount;
    public int getCount() {
        return _collection.size();
    }
    @Override
    public void SetMaxCount(int size) {
        if (size > 0) {
            _maxCount = size;
        }
    }
    public ListGenericObjects() {
        _collection = new ArrayList<T>();
    }
    @Override
    public T Get(int position)
    {
        if (position >= getCount() || position < 0) return null;
        return _collection.get(position);
    }
    @Override
    public int Insert(T obj)
    {
        if (getCount() == _maxCount) return -1;
        _collection.add(obj);
        return getCount();
    }
    @Override
    public T Remove(int position)
    {
        if (position >= getCount() || position < 0) return null;
        T obj = _collection.get(position);
        _collection.remove(position);
        return obj;
    }
}