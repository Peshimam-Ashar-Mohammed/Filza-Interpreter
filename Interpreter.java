import java.util.*;

public class Interpreter{

    HashMap<String, String> variables = new HashMap<>();

    public void run(String code){
        
        code = code.trim();

        // Ignore blank lines
        if (code.isEmpty()) {
            return;
        }

       

        // Handle print command
        if (code.startsWith("Farmayen ")) {

            // String message = code.substring(6);
            // System.out.println(message);

            for(int i=9; i<code.length(); i++){
                if(code.charAt(i)=='\\' && i+1<code.length() && code.charAt(i+1)=='n'){
                    System.out.println();
                    i++;
                } else {
                    System.out.print(code.charAt(i));
                }
            }

           // System.out.println();

        }
        else if(code.startsWith("Farmayenn ")){
            for(int i=10; i<code.length(); i++){
                if(code.charAt(i)=='\\' && i+1<code.length() && code.charAt(i+1)=='n'){
                    System.out.println();
                    i++;
                } else {
                    System.out.print(code.charAt(i));
                }
            }

            System.out.println();
        }
        else if(code.startsWith("Arry ")){

            String[] parts=code.split(" ");
            if(parts.length <4){
                System.out.println("Invalid Arry command. Usage: Arry <variable_name> <value>");
                return;
            }
            variables.put(parts[1], parts[3]);
        }
        else if(code.startsWith("& ")){

            String[] parts=code.split(" ");

            if(variables.containsKey(parts[1])){
                System.out.println(variables.get(parts[1]));
            } else {
                System.out.println("Variable not found: " + code.substring(2));
            }

        }
        else if(code.startsWith("# ")){
            return;
        }
        else if(code.equals("Chup")){

            System.out.println();
            System.out.print("Chup ho gaya");
            System.out.println(" Malkin as U said.");
            System.exit(0);
        
        }
         else if(code.startsWith("Btaen ")){

            String part2[] = code.split(" ");

            variables.put(part2[1],part2[3]);

          }
        else if(code.contains("& ")){

          String[] parts=code.split(" ");
         
        //   for(int i=0;i<parts.length;i++){
        //     System.out.println(parts[i]);
        //   }

          if(variables.containsKey(parts[0])){
            // System.out.println(variables.get(parts[0]));
            String temp = variables.get(parts[3]);
            variables.put(parts[0], temp);
          }
          else
            System.out.println("Variable not found: " + code.substring(2));
          


        }
       // else if(code.startsWith("Btaen "))
        else {

            System.out.println("Unknown command: " + code);

        }

        //    System.out.println(message);
        

       // System.out.println("\nCode finished running :fire");

    }

}