package CollectionGenericObjects;
import java.util.*;

public class StorageCollection<T> {
    private Map<String, ICollectionGenericObjects<T>> _storages;
    public StorageCollection()
    {
        _storages = new HashMap<String, ICollectionGenericObjects<T>>();
    }
    public Set<String> Keys() {
        Set<String> keys = _storages.keySet();
        return keys;
    }
    public void AddCollection(String name, CollectionType collectionType)
    {
        if (_storages.containsKey(name)) return;
        if (collectionType == CollectionType.None) return;
        else if (collectionType == CollectionType.Massive)
            _storages.put(name, new MassiveGenericObjects<T>());
        else if (collectionType == CollectionType.List)
            _storages.put(name, new ListGenericObjects<T>());
    }
    public void DelCollection(String name)
    {
        if (_storages.containsKey(name))
            _storages.remove(name);
    }
    public ICollectionGenericObjects<T> getCollectionObject(String name) {
        if (_storages.containsKey(name))
            return _storages.get(name);
        return null;
    }
    public T remove(String name, int position){
        if(_storages.containsKey(name))
            return _storages.get(name).Remove(position);
        return null;
    }

}