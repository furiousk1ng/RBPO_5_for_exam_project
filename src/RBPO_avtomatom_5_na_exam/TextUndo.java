package RBPO_avtomatom_5_na_exam;

public class TextUndo implements Command{

    private TextDocument textDocument;
    
    public TextUndo (TextDocument textDocument) {
        this.textDocument = textDocument;
    }
    
    @Override
    public void execute(){
        textDocument.letterUndo();
    }
}
