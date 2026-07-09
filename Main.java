import java.nio.file.*;

public class Main{

    public static void main(String args[]) throws Exception{

        String code = Files.readString(Path.of("First.flz"));
        
        Interpreter interpreter = new Interpreter();
        String[] lines = code.split("\\R");

        System.out.println("Calling run(String[])...");
        interpreter.run(lines);
        System.out.println("Returned from run(String[])...");

        // while(currentLine<lines.length){
        //     String line = lines[currentLine];
        //     interpreter.run(line, currentLine);
        //     currentLine++;
        // }

        // for(int i=0;i<lines.length;i++){
        //     interpreter.run(lines[i]);
        // }




    }

}