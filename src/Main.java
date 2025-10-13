import com.ua.rush.CaesarCipher.Runner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            Runner.consoleInterfaceStart();
        } else if (args.length > 0) {
            Runner.consoleStart(args);
        }
    }
}