package com.amma;
import java.util.Date;
/**
 *
 * @author amritha
 */
public class Answers {
    private int id, questionID, createdBy;
    private Date cretedOn;
    private String description;

    public int getId() {
        return id;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public Date getCretedOn() {
        return cretedOn;
    }

    public String getDescription() {
        return description;
    }
    
    public void setAnswer(int questionID, int createdBy, String description) {}
}
