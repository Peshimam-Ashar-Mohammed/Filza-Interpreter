import java.util.*;

public class Interpreter{

    HashMap<String, Value> variables = new HashMap<>();

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

            Value v = new Value();
            try {
                Integer.parseInt(parts[3]);
                v.type = "Integer";
            } catch (Exception e) {
                v.type = "String";
            }
            v.value = parts[3];
            
            variables.put(parts[1], v);
        
        }
        else if(code.startsWith("& ")){

            String[] parts=code.split(" ");

            //  if(variables.containsKey(parts[1]) && variables.get(parts[1]).equals("sachi")){
            //      System.out.println("true");
                 
            //  } else {
            //      System.out.println("false");
            //  }

            if(variables.containsKey(parts[1])){
                System.out.println(variables.get(parts[1]).value +" Its of type "+variables.get(parts[1]).type);
                
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

            Value v = new Value();
            v.type = "String";
            v.value = part2[3];
            variables.put(part2[1], v);

          }
        else if(code.contains("& ")){

          String[] parts=code.split(" ");
         
        //   for(int i=0;i<parts.length;i++){
        //     System.out.println(parts[i]);
        //   }

          if(variables.containsKey(parts[0])){
            // System.out.println(variables.get(parts[0]));
            Value temp = variables.get(parts[3]);
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