package RBPO_avtomatom_5_na_exam;

import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotUndoException;

public class TextDocument {
    
    private ArrayList<String> arrayList = new ArrayList<String>();
    private UndoManager manager;
    private JTextArea textArea;

    public TextDocument(JTextArea textArea,UndoManager manager) {
        this.textArea = textArea;
        this.manager = manager;
    }
    
    public void letterUndo(){
        try {
            if (manager.canUndo()) {
                manager.undo();
            }
        }
        catch (CannotUndoException e) {
        }
    }
}
