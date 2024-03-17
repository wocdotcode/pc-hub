
package repository;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {
    
    List<T> toList(String text);
    
    boolean insert(T obj);
    
    boolean update(T obj);
    
    Optional<T> deactivate(int id);
    
    Optional<T> activate(int id);
    
     int total();
    
     boolean exists(String text);
    
    
    
}
