/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author SONY
 */
import DatabaseAccess.TaskDAO;
import Model.Task;
import java.util.List;

public class ToDoListController {
    private TaskDAO dao;

    public ToDoListController() {
        this.dao = new TaskDAO();
    }

    public List<Task> loadDataToView() {
        // 1. Controller yêu cầu Model lấy dữ liệu
        List<Task> danhSach = dao.getAllTasks();
        return danhSach;
    }
    
    // Hàm xử lý logic thêm Task
    public boolean implementCreateTask(String content) {
        // Có thể thêm logic kiểm tra dữ liệu hợp lệ ở đây trước khi đẩy xuống DB
        if (content == null || content.trim().isEmpty()) {
            return false; 
        }
        
        Task newTask = new Task(0, content.trim(), "Chưa thực hiện");
        return dao.createTask(newTask);
    }
    
    // Xử lý cập nhật trạng thái
    public boolean implementUpdateStatus(int id, String newStatus) {
        // Có thể thêm logic kiểm tra dữ liệu ở đây (ví dụ: newStatus không được rỗng)
        if (newStatus == null || newStatus.trim().isEmpty()) {
            return false;
        }
        return dao.updateStatus(id, newStatus);
    }

    // Xử lý xóa Task
    public boolean implemetDeleteTask(int id) {
        // Kiểm tra ID hợp lệ (ID trong Oracle sinh ra thường > 0)
        if (id <= 0) {
            return false;
        }
        return dao.deleteTask(id);
    }
    
    
}
