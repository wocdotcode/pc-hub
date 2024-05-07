
package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
//import java.util.Optional;
import javax.swing.JOptionPane;
import model.Category;


public class CategoryDao extends AbstractDao implements Dao<Category> {

    private boolean resp;
    
    
    
    @Override
    public List<Category> lists(String text) {
       List<Category> categories = Collections.emptyList();
       
       String sql = "SELECT * FROM category WHERE name like ?";
       try(
              Connection con = getConnection(); 
               Statement stmt = con.createStatement();
               ResultSet rset = stmt.executeQuery(sql);
               ){
           
           categories = new ArrayList<>();
           
           while(rset.next()) {
               Category category = new Category();
               category.setId(rset.getInt("id"));
               category.setName(rset.getString("name"));
               category.setDescription(rset.getString("description"));
               category.setActive(rset.getBoolean("active"));
               
               categories.add(category);
           }
           
       } catch (SQLException sqe) {
          JOptionPane.showMessageDialog(null, sqe.getMessage());
       }
       
        return categories;
    }
    @Override
    public boolean insert(Category category) {
        resp = false; 
        String sql = "INSERT INTO category (name,description,active) VALUES (?, ?, 1)";
        
        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
                
                ) {
           
            prepStmt.setString(1, category.getName());
            prepStmt.setString(2, category.getDescription());
             if(prepStmt.executeUpdate()> 0) {
                 resp = true;
             }
            

            
        }catch(SQLException sqe){
            JOptionPane.showMessageDialog(null, sqe.getMessage());
        }
        
        
        return resp;
    }

    @Override
    public boolean update(Category category) {
        resp = false; 
        
        String sql = "UPDATE category SET name = ?, description = ? WHERE id = ?";
        
        try(
            Connection con = getConnection();
            PreparedStatement prepStmt = con.prepareStatement(sql);
                ){
            prepStmt.setString(1, category.getName());
            prepStmt.setString(2, category.getDescription());
            prepStmt.setInt(3, category.getId());
            
            if(prepStmt.executeUpdate() >0 ){
                resp = true;
            }
                 
            
        }catch(SQLException sqe){
           JOptionPane.showMessageDialog(null, "Error upodating: " + sqe.getMessage());
        }
        
        
        return resp;
    }

    @Override
    public boolean deactivate(int id) {
        resp = false;
        
        
        String sql = "UPDATE category set active = 0 WHERE id = ?";
        
        try(
               Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ){
            
                prepStmt.setInt(1,id);
                
            if (prepStmt.executeUpdate() > 0) {
                resp = true;
            }
            
        }catch(SQLException sqe) {
             JOptionPane.showMessageDialog(null, sqe.getMessage());
        }
        
        return resp;
    }

    @Override
    public boolean activate(int id) {
        resp = false;
        
        String sql = "UPDATE category SET active = 1 WHERE id = ?";
        
        try(
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
                ){
                prepStmt.setInt(1, id);
                if(prepStmt.executeUpdate() > 0 ){
                    resp = true;
                }
            
        }catch(SQLException sqe){
            JOptionPane.showMessageDialog(null, sqe.getMessage());
        }
        
        
        return resp;
    }

    @Override
    public int total() {
        int totalRecords = 0;
        
        String sql = "SELECT COUNT(id) FROM category";
        
        try (
                Connection conn = getConnection();
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                ResultSet rset =  prepStmt.executeQuery();
                ){
           
            while(rset.next()){
                totalRecords = rset.getInt("COUNT(id)");
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return 1;
    }

    @Override
    public boolean exists(String text) {
        
        resp = false;
        
        String sql = "SELECT name FROM category WHERE name = ?";
        
        try(
                Connection conn = getConnection();
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                ResultSet rset = prepStmt.executeQuery();
             
                ){
            
                prepStmt.setString(1, text);
                rset.last();
                if(rset.getRow() > 0) {
               
                   resp = true;
                 }
                
        }catch(SQLException sqe){
            JOptionPane.showMessageDialog(null, sqe.getMessage());
        }  
        
        return resp;
    }
    
    
}
