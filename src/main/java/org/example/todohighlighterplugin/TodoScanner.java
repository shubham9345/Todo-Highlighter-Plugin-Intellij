package org.example.todohighlighterplugin;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoScanner {

    public static void setupListener(Project project) {
        EditorFactory.getInstance().getEventMulticaster().addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                Document document = event.getDocument();
                VirtualFile file = FileEditorManager.getInstance(project).getSelectedFiles()[0];
                if (file != null && (file.getName().endsWith(".kt") || file.getName().endsWith(".java"))) {
                    String text = document.getText();
                    List<TodoItem> todos = findTodos(text);
                    TodoToolWindowFactory.updateTodos(project, todos);
                    TodoHighlighter.highlightTodos(project, document, todos);
                }
            }
        }, project);
    }

    public static List<TodoItem> findTodos(String text) {
        List<TodoItem> todos = new ArrayList<>();
        Pattern pattern = Pattern.compile("(//|/\\*)\\s*TODO:(.*)");
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Matcher matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                todos.add(new TodoItem(i, matcher.group(1).trim()));
            }
        }
        return todos;
    }

    public static class TodoItem {
        public int line;
        public String text;

        public TodoItem(int line, String text) {
            this.line = line;
            this.text = text;
        }
    }
}
