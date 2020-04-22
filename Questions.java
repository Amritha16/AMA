package com.amma;
import java.util.Date;
/**
 *
 * @author amritha
 */
public class Questions {
    
    String DB_PASSWORD = "";
    String DB_USERNAME = "root";
    String DB_URL = "jdbc:mysql://localhost:3306/Package5thSem";
    
    private int id, createdBy;
    private Date createdOn;
    private String description;
    
    public void setQuestion(int createdBy, String description) {    }

    public int getId() {
        return id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getDescription() {
        return description;
    }
    
}
