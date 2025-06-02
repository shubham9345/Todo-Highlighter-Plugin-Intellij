package org.example.todohighlighterplugin;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.List;

public class TodoHighlighter {

    public static void highlightTodos(Project project, Document document, List<TodoScanner.TodoItem> todos) {
        Editor editor = EditorFactory.getInstance().getEditors(document, project)[0];
        MarkupModel markupModel = editor.getMarkupModel();
        markupModel.removeAllHighlighters();

        for (TodoScanner.TodoItem todo : todos) {
            int startOffset = document.getLineStartOffset(todo.line);
            TextAttributes attr = new TextAttributes(JBColor.RED, null, null, null, Font.BOLD);
            markupModel.addLineHighlighter(todo.line, HighlighterLayer.WARNING, attr);
        }
    }
}