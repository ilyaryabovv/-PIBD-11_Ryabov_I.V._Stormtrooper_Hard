package CollectionGenericObjects;

import Drawnings.DrawingBaseStormtrooper;
import Drawnings.DrawingStormtrooper;
import Drawnings.Engines.IDrawingEngines;
import Entities.EntityBaseStormtrooper;
import Entities.EntityStormtrooper;
import java.lang.reflect.Array;
import java.util.Random;

public class AdditionalCollection <T extends EntityBaseStormtrooper, U extends IDrawingEngines>{
    public T[] _collectionEntity;
    public U[] _collectionEngines;
    public AdditionalCollection(int size, Class<T> type1, Class<T> type2) {
        _collectionEntity = (T[]) Array.newInstance(type1, size);
        _collectionEngines = (U[]) Array.newInstance(type2, size);
        CountEntities = size;
        CountEngines = size;
    }
    public int CountEntities;
    public int CountEngines;
    public int Insert(T entity) {
        int index = 0;
        while (index < CountEntities) {
            if (_collectionEntity[index] == null)
            {
                _collectionEntity[index] = entity;
                return index;
            }
            ++index;
        }
        return -1;
    }
    public int Insert(U decks) {
        int index = 0;
        while (index < CountEngines) {
            if (_collectionEngines[index] == null)
            {
                _collectionEngines[index] = decks;
                return index;
            }
            ++index;
        }
        return -1;
    }
    public DrawingBaseStormtrooper CreateAdditionalCollectionStormtrooper() {
        Random random = new Random();
        if (_collectionEntity == null || _collectionEngines == null) return null;
        T entity = _collectionEntity[random.nextInt(CountEntities)];
        U engines = _collectionEngines[random.nextInt(CountEngines)];
        DrawingBaseStormtrooper drawingBaseStormtrooper = null;
        if (entity instanceof EntityStormtrooper) {
            drawingBaseStormtrooper = new DrawingStormtrooper((EntityStormtrooper) entity, engines);
        }
        else {
            drawingBaseStormtrooper = new DrawingBaseStormtrooper(entity, engines);
        }
        return drawingBaseStormtrooper;
    }
}