/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author SONY
 */
public class Task {
    private int id;
    private String content;
    private String status;
    
    public Task(int id, String content, String status){
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }
}
