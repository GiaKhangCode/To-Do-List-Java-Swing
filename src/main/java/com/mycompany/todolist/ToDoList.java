/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.todolist;

import Controller.ToDoListController;
import DatabaseAccess.TaskDAO;
import View.MainFrame;

/**
 *
 * @author SONY
 */
public class ToDoList {
    public static void main(String[] args) {
        // Chạy giao diện trên luồng Event Dispatch Thread của Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame view = new MainFrame();
            TaskDAO model = new TaskDAO();
            
            new ToDoListController(view, model);
            view.setVisible(true);
        });
    }
}