import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.io.IOException;

public class Main {
    
    static Map<String, Integer> romanInput = new HashMap();
    static List validOperations = new LinkedList<String>();
    static {
        for (int i = 1; i<=10; i++){
            romanInput.put(intToRoman(i), i);
        }
        
        validOperations.add("+");
        validOperations.add("-");
        validOperations.add("*");
        validOperations.add("/");}
    
    public static void main(String[] args) throws IOException,
                                                  InvalidInputException,
                                                  OutOfRangeException,
                                                  InvalidOperationException{
        System.out.println(calc(new BufferedReader(new InputStreamReader(System.in)).readLine()));
    }
    
    public static String calc(String input) throws 
                              InvalidInputException,
                              OutOfRangeException,
                              InvalidOperationException{
                                  String[] splittedInput = input.split(" ");
                                  for (int i = 0; i < splittedInput.length; i++){
                                      System.out.println(splittedInput[i]);
                                  }
                                  if (inputIsValid(splittedInput)){
                                      if (splittedInput[0].matches("\\d+")){
                                          return Integer.toString(doMath(Integer.parseInt(splittedInput[0]),
                                                                         Integer.parseInt(splittedInput[2]),
                                                                         splittedInput[1]));
                                      } else {
                                          return intToRoman(doMath(romanInput.get(splittedInput[0]).intValue(),
                                                                       romanInput.get(splittedInput[2]).intValue(),
                                                                       splittedInput[1]));
                                      }
                                  } else {throw new InvalidInputException();}
    }
    
    static int doMath(int a, int b, String operation){
        int result;
        switch (operation) {
            case "+":    result = a + b;
                    break;
            case "-":    result = a - b;
                    break;
            case "*":   result = a * b;
                    break;
            case "/":   result = Math.floorDiv(a, b);
                    break;
            default: System.out.println("MATH ERROR! NO SUCH CASE ("+operation+")");
                     result = 666;
        }
        return result;
    }
      
    static boolean inputIsValid(String[] splittedInput) throws
                                              InvalidInputException,
                                              OutOfRangeException,
                                              InvalidOperationException{
        if (
            (splittedInput.length == 3)&&
            (validOperations.contains(splittedInput[1]))){
            if (splittedInput[0].matches("\\d+")&&
                splittedInput[2].matches("\\d+")
                ){
                    if (
                        (Integer.parseInt(splittedInput[0])<=10)&&
                        (1<=Integer.parseInt(splittedInput[0]))&&
                        (Integer.parseInt(splittedInput[2])<=10)&&
                        (1<=Integer.parseInt(splittedInput[2]))
                        ){
                            return true;
                        } else{
                            throw(new OutOfRangeException());
                        }
            } else if (
                       romanInput.containsKey(splittedInput[0])&&
                       romanInput.containsKey(splittedInput[2])
                      ){
                           if (
                               splittedInput[1].equals("-")&&
                               ((int)romanInput.get(splittedInput[0])<=
                               (int)romanInput.get(splittedInput[2]))
                               ){
                                   throw new InvalidOperationException();
                           } else{
                               return true;
                           }
                               
            } else{
                throw(new InvalidInputException());
            }
        } else {
            throw(new InvalidInputException());
        }
    }
    
    static String intToRoman(int number) {
        return String.join("", Collections.nCopies(number, "I"))
            .replace("IIIII", "V")
            .replace("IIII", "IV")
            .replace("VV", "X")
            .replace("VIV", "IX")
            .replace("XXXXX", "L")
            .replace("XXXX", "XL")
            .replace("LL", "C")
            .replace("LXL", "XC")
            .replace("CCCCC", "D")
            .replace("CCCC", "CD")
            .replace("DD", "M")
            .replace("DCD", "CM");
    }
    
    static class InvalidInputException extends Exception{}
    static class OutOfRangeException extends Exception{}
    static class InvalidOperationException extends Exception{}
                                    }
