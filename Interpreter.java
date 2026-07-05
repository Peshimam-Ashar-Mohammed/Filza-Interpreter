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
        else if(code.startsWith("Arry ") && (code.contains(" + ")==false) && 
        (code.contains(" - ")==false)){

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
                try {
                    Double.parseDouble(parts[3]);
                    v.type = "Double";
                } catch (Exception e2) {
                        if(parts[3].equals("suchi") || parts[3].equalsIgnoreCase("jhoot"))
                            v.type = "Boolean";
                        else
                            v.type = "String";
                }
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

            if(part2[3].equals("suchi") || part2[3].equalsIgnoreCase("jhoot"))
                            v.type = "Boolean";
                        else
                            v.type = "String";

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
        else if(code.contains(" + ")){

            String parts[]=code.split(" "); // 3,4

            if(parts[0].equals("Arry")){
                if(variables.containsKey(parts[3]) && variables.containsKey(parts[5])){

                    if(variables.get(parts[3]).type.equals("Integer") && variables.get(parts[5]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int sum = Integer.parseInt(variables.get(parts[3]).value) + Integer.parseInt(variables.get(parts[5]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(sum);

                        variables.put(parts[1], result);

                    }
                    else if(variables.get(parts[3]).type.equals("Double") && variables.get(parts[5]).type.equals("Double")){

                        double sum = Double.parseDouble(variables.get(parts[3]).value) + Double.parseDouble(variables.get(parts[5]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(sum);
                        variables.put(parts[1], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform addition on non-integer values.");
                    }
                    


                }
            }
            else{

                if(variables.containsKey(parts[2]) && variables.containsKey(parts[4])){

                    if(variables.get(parts[2]).type.equals("Integer") && variables.get(parts[4]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int sum = Integer.parseInt(variables.get(parts[2]).value) + Integer.parseInt(variables.get(parts[4]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(sum);

                        variables.put(parts[0], result);

                    }
                    else if(variables.get(parts[2]).type.equals("Double") && variables.get(parts[4]).type.equals("Double")){

                        double sum = Double.parseDouble(variables.get(parts[2]).value) + Double.parseDouble(variables.get(parts[4]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(sum);
                        variables.put(parts[0], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform addition on non-integer values.");
                    }
                }    

            
                
            }

        } // Subtraction logic starts here
        else if(code.contains(" - ")){

            String parts[]=code.split(" "); // 3,4

            if(parts[0].equals("Arry")){
                if(variables.containsKey(parts[3]) && variables.containsKey(parts[5])){

                    if(variables.get(parts[3]).type.equals("Integer") && variables.get(parts[5]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int diff = Integer.parseInt(variables.get(parts[3]).value) - Integer.parseInt(variables.get(parts[5]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(diff);

                        variables.put(parts[1], result);

                    }
                    else if(variables.get(parts[3]).type.equals("Double") && variables.get(parts[5]).type.equals("Double")){

                        double diff = Double.parseDouble(variables.get(parts[3]).value) - Double.parseDouble(variables.get(parts[5]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(diff);
                        variables.put(parts[1], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform Subtraction on non-integer values.");
                    }
                    


                }
            }
            else{

                if(variables.containsKey(parts[2]) && variables.containsKey(parts[4])){

                    if(variables.get(parts[2]).type.equals("Integer") && variables.get(parts[4]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int diff = Integer.parseInt(variables.get(parts[2]).value) - Integer.parseInt(variables.get(parts[4]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(diff);

                        variables.put(parts[0], result);

                    }
                    else if(variables.get(parts[2]).type.equals("Double") && variables.get(parts[4]).type.equals("Double")){

                        double diff = Double.parseDouble(variables.get(parts[2]).value) - Double.parseDouble(variables.get(parts[4]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(diff);
                        variables.put(parts[0], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform Subtraction on non-integer values.");
                    }
                }    

            
                
            }

        }// Subtraction logic ends here
        else {

                   // else if(code.startsWith("Btaen "))

            System.out.println("Unknown command: " + code);

        }

        //    System.out.println(message);
        

       // System.out.println("\nCode finished running :fire");

    }

}