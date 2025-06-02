package org.example.todohighlighterplugin;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.project.Project;

public class EditorUtil {
    public static void navigateToLine(Project project, int line) {
        Editor editor = EditorFactory.getInstance().getAllEditors()[0];
        if (editor != null) {
            editor.getCaretModel().moveToVisualPosition(new VisualPosition(line, 0));
            editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
        }
    }
}