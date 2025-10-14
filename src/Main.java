import com.ua.rush.CaesarCipher.CLI;
import com.ua.rush.CaesarCipher.Runner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            CLI interfaceConsole = new CLI();
            interfaceConsole.startProject();
        } else if (args.length > 0) {
            Runner.consoleStart(args);
        }
    }
}