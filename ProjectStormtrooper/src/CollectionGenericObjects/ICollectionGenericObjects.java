
package CollectionGenericObjects;

public interface ICollectionGenericObjects<T>
{
    int getCount();
    void SetMaxCount(int count);
    int Insert(T obj);
    T Remove(int position);
    T Get(int position);
}
