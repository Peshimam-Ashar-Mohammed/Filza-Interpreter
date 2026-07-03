import java.nio.file.*;

public class Main{

    public static void main(String args[]) throws Exception{

        String code = Files.readString(Path.of("First.flz"));
        
        Interpreter interpreter = new Interpreter();

        String[] lines = code.split("\n");

        for(String line:lines){
            interpreter.run(line);
        }

        // interpreter.run(code);


    }

}