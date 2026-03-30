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
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoListController {
    private MainFrame view;
    private TaskDAO dao;

    public ToDoListController(MainFrame view, TaskDAO model) {
        this.view = view;
        this.dao = model;
        
        initListeners();
        loadDataToView();
    }

    private void initListeners() {
        // Gắn Listener cho nút Thêm Task
        this.view.addCreateTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toàn bộ logic khi bấm nút giờ nằm ở đây!
                String content = javax.swing.JOptionPane.showInputDialog(view, "Nhập nội dung:");
                if (content != null && !content.trim().isEmpty()) {
                    Task newTask = new Task(0, content.trim(), "Chưa thực hiện");
                    if (dao.createTask(newTask)) {
                        view.displayNotation("Thành công!", true);
                        view.displayData(dao.getAllTasks());
                    } else {
                        view.displayNotation("Thất bại!", false);
                    }
                }
            }
        });
        
        // --- 2. Lắng nghe nút THAY ĐỔI TRẠNG THÁI (SỬA) ---
        this.view.addUpdateStatusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Controller "hỏi" View xem ID nào đang được chọn
                int taskId = view.getSelectedTaskId();
                
                if (taskId == -1) {
                    view.displayNotation("Vui lòng chọn một công việc để thay đổi trạng thái!", false);
                    return;
                }

                String currentStatus = view.getSelectedTaskStatus();
                String[] options = {"Chưa thực hiện", "Đang thực hiện", "Đã xong"};

                String newStatus = (String) javax.swing.JOptionPane.showInputDialog(
                        view, "Chọn trạng thái mới:", "Thay đổi trạng thái",
                        javax.swing.JOptionPane.QUESTION_MESSAGE, null, options, currentStatus
                );

                if (newStatus != null && !newStatus.equals(currentStatus)) {
                    if (dao.updateStatus(taskId, newStatus)) {
                        view.displayNotation("Cập nhật trạng thái thành công!", true);
                        view.displayData(dao.getAllTasks());
                    } else {
                        view.displayNotation("Cập nhật thất bại!", false);
                    }
                }
            }
        });

        // --- 3. Lắng nghe nút XÓA TASK ---
        this.view.addDeleteTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int taskId = view.getSelectedTaskId();
                
                if (taskId == -1) {
                    view.displayNotation("Vui lòng chọn một công việc cần xóa!", false);
                    return;
                }

                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        view, "Bạn có chắc chắn muốn xóa công việc này không?", "Xác nhận xóa",
                        javax.swing.JOptionPane.YES_NO_OPTION
                );

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    if (dao.deleteTask(taskId)) {
                        view.displayNotation("Xóa thành công!", true);
                        view.displayData(dao.getAllTasks());
                    } else {
                        view.displayNotation("Xóa thất bại!", false);
                    }
                }
            }
        });
    }

    public void loadDataToView() {
        List<Task> danhSachMoi = dao.getAllTasks();
        view.displayData(danhSachMoi);
    }
}
