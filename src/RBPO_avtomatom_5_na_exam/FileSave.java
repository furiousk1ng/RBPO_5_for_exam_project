package RBPO_avtomatom_5_na_exam;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FileSave implements Strategy{
	private String proverka;

    @Override
    public void doOperation(JTextArea textArea, String fileName,JFrame frame) {
        
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(textArea.getText());
        } 
        catch (IOException ex) {
            Logger.getLogger(NotepadEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setTitle(fileName);
    }
}
