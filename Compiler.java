import java.util.*;
import java.io.*;

public class Compiler {     //compiler class

    public Compiler() {
        //empty since there isnt anything that needs to be initialized
    }

    public static String importFile(File file) throws Exception {
        String str = "";
        Scanner input = new Scanner(file);      //takes in an input file
        while(input.hasNextLine()){
            str += input.nextLine();     //converts it to 1 input str
            Lexer lex = new Lexer(str);  //instance of lexer class
            List<String> lexO = lex.tokenObject();
            for (String token : lexO) {
                System.out.println(Token.lexeme + " " + Token.tokenCode);
                Syntax syntax = new Syntax(lexO);   //instance of parse class
                syntax.parse();
            }
        }
        return str;
    }
}
