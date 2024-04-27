
package CollectionGenericObjects;

public interface ICollectionGenericObjects<T>
{
    int getCount();
    void SetMaxCount(int count, Class<T> type);
    int Insert(T obj);
    int Insert(T obj, int position);
    T Remove(int position);
    T Get(int position);
}
