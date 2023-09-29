package mini_project_java.java_exceptions;
import java.util.Scanner;

public class JavaException {

    class Encrypter{
        public static String encryptMessage(String name) throws InvalidMessageException{
            if(Validator.validate(name)){
                StringBuilder reversed = new StringBuilder(name.toLowerCase());
                reversed.reverse();
                return reversed.toString();
            }
            else{
                throw new InvalidMessageException("Try again with valid message");
            }
        }
    }

    static class InvalidMessageException extends Exception {
        public InvalidMessageException(String message){
            super(message);
        }

        @Override
        public String toString(){
            return "InvalidMessageException: "+getMessage();
        }
    }

    class Validator {
        public static boolean validate(String message) {
            return message.matches("[A-Za-z0-9 ]+");
        }
    }

    public class Solution {
        private static final Scanner INPUT_READER = new Scanner(System.in);

        public static void main(String[] args) {
            String message = INPUT_READER.nextLine();

            try {
                String encrypted_message = Encrypter.encryptMessage(message);
                if(! encrypted_message.startsWith("InvalidMessageException"))
                    System.out.println(encrypted_message);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
