package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import settings.DatabaseServer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;

@Data
public class DatableImplementation<T> implements CrudData<T> {
    private Class entityBeanType;
    private ObjectContainer objectContainer;

    DatableImplementation() {
        this.objectContainer = DatabaseServer.getServer().openClient();
        this.entityBeanType = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public boolean create(T obj) {
        /* Timestamps */
        LocalDateTime currentDateTime = LocalDateTime.now();

        Method setCreatedAt = null;
        Method setUpdatedAt = null;
        try {
            setCreatedAt = obj.getClass().getMethod("setCreatedAt", LocalDateTime.class);
            setCreatedAt.invoke(obj, currentDateTime);

            setUpdatedAt = obj.getClass().getMethod("setUpdatedAt", LocalDateTime.class);
            setUpdatedAt.invoke(obj, currentDateTime);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        this.getObjectContainer().store(obj);
        this.getObjectContainer().commit();
        return true;
    }

    @Override
    public T update(T obj) {
        try {
            Method getId = obj.getClass().getMethod("getId");
            String id = (String) getId.invoke(obj);
            T currentObject = findBy("id", id);

            Method getCreatedAt = currentObject.getClass().getMethod("getCreatedAt");
            LocalDateTime createdAt = (LocalDateTime) getCreatedAt.invoke(currentObject);

            BeanUtils.copyProperties(obj, currentObject);
            Method setUpdatedAt = currentObject.getClass().getMethod("setUpdatedAt", LocalDateTime.class);
            setUpdatedAt.invoke(currentObject, LocalDateTime.now());

            Method setCreatedAt = currentObject.getClass().getMethod("setCreatedAt", LocalDateTime.class);
            setCreatedAt.invoke(currentObject, createdAt);

            this.objectContainer.store(currentObject);
            this.objectContainer.commit();

            return currentObject;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(T obj) {
        try {
            this.objectContainer.delete(obj);
            this.objectContainer.commit();
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        for(T model : findAll()) {
            objectContainer.delete(model);
            objectContainer.commit();
        }
    }

    @Override
    public boolean exists(String key, String value) {
        return findBy(key, value) != null;
    }

    @Override
    public T findBy(String key, String value) {
        Query query = this.objectContainer.query();
        query.constrain(this.entityBeanType);
        query.descend(key).constrain(value).equal();
        ObjectSet<T> result = query.execute();
        return result.hasNext() ? result.next() : null;
    }

    @Override
    public ObjectSet<T> findAll() {
        Query query = this.objectContainer.query();
        query.constrain(this.entityBeanType);
        return query.execute();
    }

    @Override
    public ObjectSet<T> findAllBy(String key, String value) {
        Query query = this.objectContainer.query();
        query.constrain(this.entityBeanType);
        query.descend(key).constrain(value).equal();
        return query.execute();
    }
}
