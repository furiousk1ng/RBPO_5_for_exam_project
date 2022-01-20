package RBPO_avtomatom_5_na_exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FileOpen implements Strategy {

    @Override
    public void doOperation(JTextArea textArea, String fileName,JFrame frame) {
        try(Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            String content="";
            
            while (scanner.hasNextLine()) {
                content+=scanner.nextLine()+ "\n";
            }
            
            textArea.setText(content);
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(NotepadEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setTitle(fileName);
    }
}
