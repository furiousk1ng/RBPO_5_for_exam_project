package RBPO_avtomatom_5_na_exam;

public class Invoker {
       
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
 
    public void executeCommand() {
        command.execute();
    }
}
