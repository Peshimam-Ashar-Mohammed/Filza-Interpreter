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

            ExecutePrint(code);

           // System.out.println();

        }
        else if(code.startsWith("Farmayenn ")){
            ExecutePrintln(code);
        }// Handle variable assignment
        else if(code.startsWith("Arry ") && (code.contains(" + ")==false) && 
        (code.contains(" - ")==false) && 
        (code.contains(" * ")==false) && 
        (code.contains(" / ")==false) &&
        (code.contains(" % ")==false) &&
        (code.contains(" > ")==false)){

            ExecuteVariableAssignment(code);
            
        }
        else if(code.startsWith("& ")){

            //  if(variables.containsKey(parts[1]) && variables.get(parts[1]).equals("sachi")){
            //      System.out.println("true");
                 
            //  } else {
            //      System.out.println("false");
            //  }

            ExecuteVariableLookup(code);

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

            ExecuteBooleanCheck(code);

          }
        else if(code.contains("& ")){

          ExecuteVariableLookupMiddle(code);

        }
        else if(code.contains(" + ")){

            ExecuteAddition(code);

        } // Subtraction logic starts here
        else if(code.contains(" - ")){

                ExecuteSubtraction(code);
            
        }// Subtraction logic ends here
        else if(code.contains(" * ")){ //Multiplication logic starts here

                ExecuteMultiplication(code);

        }// multiplication logic ends here
        else if(code.contains(" / ")){ //Division logic starts here

            ExecuteDivision(code);

        }// Divison logic ends here
        else if(code.contains(" % ")){ //Modulo logic starts here

            ExecuteModulo(code);

        }//modulo logic ends here
        else if(code.contains(" > ")){

            ExceuteGreaterThan(code);

        }
        else if(code.startsWith("pakka ")){

            createConstant(code);

        }
        else {

                   // else if(code.startsWith("Btaen "))

            System.out.println("Unknown command: " + code);

        }

        //    System.out.println(message);
        

       // System.out.println("\nCode finished running :fire");

    }








// Functions for executing commands












    public void ExecutePrint(String code){

        for(int i=9; i<code.length(); i++){
                if(code.charAt(i)=='\\' && i+1<code.length() && code.charAt(i+1)=='n'){
                    System.out.println();
                    i++;
                } else {
                    System.out.print(code.charAt(i));
                }
            }

    }

    public void ExecutePrintln(String code){
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

    public void ExecuteVariableAssignment(String code){

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
            v.isConstant = false;
            variables.put(parts[1], v);
        

    }

    public void ExecuteVariableLookup(String code){

        String[] parts=code.split(" ");
        
            if(variables.containsKey(parts[1])){
                System.out.println(variables.get(parts[1]).value +" Its of type "+variables.get(parts[1]).type);
                
            }
            // else if(variables.containsKey(parts[1]) && variables.get(parts[1]).isConstant==true){
            //     System.out.println("Cannot lookup constant variable: " + parts[1]);
            // }
            else {
                System.out.println("Variable not found: " + code.substring(2));
            }
    }

    public void ExecuteBooleanCheck(String code){

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

    public void ExecuteVariableLookupMiddle(String code){

        String[] parts=code.split(" ");
         
        //   for(int i=0;i<parts.length;i++){
        //     System.out.println(parts[i]);
        //   }
        
        if (variables.get(parts[0]).isConstant) { 

            System.out.println("Cannot modify constant variable: " + parts[0]); 
            return; 
            
        }

          if(variables.containsKey(parts[0]) ){
            // System.out.println(variables.get(parts[0]));
            Value temp = variables.get(parts[3]);
            variables.put(parts[0], temp);
          }
        //   else if(variables.containsKey(parts[0]) && variables.get(parts[3]).isConstant==true){
        //     System.out.println("Cannot assign constant variable: " + parts[3]);
        //   }
          else
            System.out.println("Variable not found: " + code.substring(2));
          

    }

    public void ExecuteAddition(String code){

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

    }//Additon ends here

    public void ExecuteSubtraction(String code){

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


    }//Subtraction ends here

    public void ExecuteMultiplication(String code){

        String parts[]=code.split(" "); // 3,4

            if(parts[0].equals("Arry")){
                if(variables.containsKey(parts[3]) && variables.containsKey(parts[5])){

                    if(variables.get(parts[3]).type.equals("Integer") && variables.get(parts[5]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int product = Integer.parseInt(variables.get(parts[3]).value) * Integer.parseInt(variables.get(parts[5]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(product);

                        variables.put(parts[1], result);

                    }
                    else if(variables.get(parts[3]).type.equals("Double") && variables.get(parts[5]).type.equals("Double")){

                        double product = Double.parseDouble(variables.get(parts[3]).value) * Double.parseDouble(variables.get(parts[5]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(product);
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
                        
                        int product = Integer.parseInt(variables.get(parts[2]).value) * Integer.parseInt(variables.get(parts[4]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(product);
                        variables.put(parts[0], result);

                    }
                    else if(variables.get(parts[2]).type.equals("Double") && variables.get(parts[4]).type.equals("Double")){

                        double product = Double.parseDouble(variables.get(parts[2]).value) * Double.parseDouble(variables.get(parts[4]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(product);
                        variables.put(parts[0], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform Subtraction on non-integer values.");
                    }
                }    

            
                
            }


    }//Multiplication ends here

    public void ExecuteDivision(String code){

        String parts[]=code.split(" "); // 3,4

            if(parts[0].equals("Arry")){
                if(variables.containsKey(parts[3]) && variables.containsKey(parts[5])){

                    if(variables.get(parts[3]).type.equals("Integer") && variables.get(parts[5]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int quotient = Integer.parseInt(variables.get(parts[3]).value) / Integer.parseInt(variables.get(parts[5]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(quotient);

                        variables.put(parts[1], result);

                    }
                    else if(variables.get(parts[3]).type.equals("Double") && variables.get(parts[5]).type.equals("Double")){

                        double quotient = Double.parseDouble(variables.get(parts[3]).value) / Double.parseDouble(variables.get(parts[5]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(quotient);
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
                        
                        int quotient = Integer.parseInt(variables.get(parts[2]).value) / Integer.parseInt(variables.get(parts[4]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(quotient);
                        variables.put(parts[0], result);

                    }
                    else if(variables.get(parts[2]).type.equals("Double") && variables.get(parts[4]).type.equals("Double")){

                        double quotient = Double.parseDouble(variables.get(parts[2]).value) / Double.parseDouble(variables.get(parts[4]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(quotient);
                        variables.put(parts[0], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform Division on non-integer values.");
                    }
                }    

            
                
            }


    }//Division ends here


    public void ExecuteModulo(String code){

        String parts[]=code.split(" "); // 3,4

            if(parts[0].equals("Arry")){
                if(variables.containsKey(parts[3]) && variables.containsKey(parts[5])){

                    if(variables.get(parts[3]).type.equals("Integer") && variables.get(parts[5]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int remainder = Integer.parseInt(variables.get(parts[3]).value) % Integer.parseInt(variables.get(parts[5]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(remainder);

                        variables.put(parts[1], result);

                    }
                    else if(variables.get(parts[3]).type.equals("Double") && variables.get(parts[5]).type.equals("Double")){

                        double remainder = Double.parseDouble(variables.get(parts[3]).value) % Double.parseDouble(variables.get(parts[5]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(remainder);
                        variables.put(parts[1], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform Modulo on non-integer values.");
                    }
                    


                }
            }
            else{

                if(variables.containsKey(parts[2]) && variables.containsKey(parts[4])){

                    if(variables.get(parts[2]).type.equals("Integer") && variables.get(parts[4]).type.equals("Integer") /*&& variables.get(parts[1]).type.equals("Integer")*/){
                        
                        int remainder = Integer.parseInt(variables.get(parts[2]).value) % Integer.parseInt(variables.get(parts[4]).value);
                        
                        Value result = new Value();
                        
                        result.type = "Integer";
                        result.value = String.valueOf(remainder);
                        variables.put(parts[0], result);

                    }
                    else if(variables.get(parts[2]).type.equals("Double") && variables.get(parts[4]).type.equals("Double")){

                        double remainder = Double.parseDouble(variables.get(parts[2]).value) % Double.parseDouble(variables.get(parts[4]).value);
                        Value result = new Value();
                        result.type = "Double";
                        result.value = String.valueOf(remainder);
                        variables.put(parts[0], result);
                        
                    }
                    else{
                        System.out.println("Type mismatch: Cannot perform Modulo on non-integer values.");
                    }
                }    

            
                
            }

    }// Modulo ends here

    public void createConstant(String code){

        ExecuteVariableAssignment(code);

        String[] parts=code.split(" ");
        Value res = variables.get(parts[1]);
        res.isConstant = true;

        variables.put(parts[1], res);


    }

    public void ExceuteGreaterThan(String code){

        // System.out.println("Called this shit yes or no testing");

        String parts[]= code.split(" ");

        // for (String s : parts) {
        //     System.out.println(s);
        // }

        if(variables.containsKey(parts[2]) && variables.containsKey(parts[4])){

            if(variables.get(parts[2]).type.equals("Integer") && variables.get(parts[4]).type.equals("Integer")){

                int num1=Integer.parseInt(variables.get(parts[2]).value);
                int num2=Integer.parseInt(variables.get(parts[4]).value);

                if(num1>num2){

                    Value res = new Value();
                    
                    res.type = "Boolean";
                    res.value = "suchi";
                    res.isConstant = false;
                    variables.put(parts[0], res);

                } else {
                    
                    Value res = new Value();
                    
                    res.type = "Boolean";
                    res.value = "jhoot";
                    res.isConstant = false;
                    variables.put(parts[0], res);

                }


            }
            else if(variables.get(parts[2]).type.equals("Double") && variables.get(parts[4]).type.equals("Double")){

                double num1=Double.parseDouble(variables.get(parts[2]).value);
                double num2=Double.parseDouble(variables.get(parts[4]).value);

                if(num1>num2){

                    Value res = new Value();
                    
                    res.type = "Boolean";
                    res.value = "suchi";
                    res.isConstant = false;
                    variables.put(parts[0], res);

                } else {
                    
                    Value res = new Value();
                    
                    res.type = "Boolean";
                    res.value = "jhoot";
                    res.isConstant = false;
                    variables.put(parts[0], res);

                }

            }
        }
        else {

            // System.out.println("Both variables must be of type Integer or Double for comparison.");
            System.out.println("Variables dont even exist lil bro or your syntax is wring check that shit womp womp");
            
        }


    }


}