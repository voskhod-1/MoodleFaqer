import Console.ConsoleInterface;
import GUI.Login;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].startsWith("--GUI")) Login.main(args);
        }else{
            ConsoleInterface.start();
        }
    }
}