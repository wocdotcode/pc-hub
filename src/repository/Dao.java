
package repository;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {
    
    List<T> lists(String text);
    
    boolean insert(T obj);
    
    boolean update(T obj);
    
    boolean deactivate(int id);
    
    boolean activate(int id);
    
     int total();
    
     boolean exists(String text);
    
    
    
}
