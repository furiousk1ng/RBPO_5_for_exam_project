package RBPO_avtomatom_5_na_exam;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Context {
    private Strategy strategy;
    
    public Context(Strategy strategy){
        this.strategy=strategy;
    }
    
    public void executeStrategy(JTextArea textArea, String fileName,JFrame frame){
        strategy.doOperation(textArea, fileName, frame);
    }
}
