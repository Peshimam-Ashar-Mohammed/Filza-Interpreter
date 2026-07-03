
public class Interpreter{

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
        else if(code.equals("Chup")){
            System.out.println("G Malkin as U say.");
            System.exit(0);
        } 
        else {

            System.out.println("Unknown command: " + code);

        }

        //    System.out.println(message);
        

       // System.out.println("\nCode finished running :fire");

    }

}