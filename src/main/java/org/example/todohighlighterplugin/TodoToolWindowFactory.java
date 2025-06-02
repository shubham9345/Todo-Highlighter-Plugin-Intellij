package org.example.todohighlighterplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.util.List;

public class TodoToolWindowFactory implements ToolWindowFactory {
    private static JBList<String> listComponent;

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        listComponent = new JBList<>(new DefaultListModel<>());
        listComponent.addListSelectionListener(e -> {
            int index = listComponent.getSelectedIndex();
            if (index >= 0 && currentTodos != null && index < currentTodos.size()) {
                int line = currentTodos.get(index).line;
                EditorUtil.navigateToLine(project, line);
            }
        });

        JBScrollPane pane = new JBScrollPane(listComponent);
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(pane, "", false);
        toolWindow.getContentManager().addContent(content);


    }

    private static List<TodoScanner.TodoItem> currentTodos;

    public static void updateTodos(Project project, List<TodoScanner.TodoItem> todos) {
        currentTodos = todos;
        SwingUtilities.invokeLater(() -> {
            DefaultListModel<String> model = new DefaultListModel<>();
            for (TodoScanner.TodoItem item : todos) {
                model.addElement("Line " + (item.line + 1) + ": " + item.text);
            }
            listComponent.setModel(model);
        });
    }
}