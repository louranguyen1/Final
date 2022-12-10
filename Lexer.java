import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    static String input;
    static List<String> tokens;

    Lexer(String input) {       //#3: takes in a string in its constructor
        this.input = input;
        this.tokens = null;
        this.getToken(input);
    }

    List<String> tokenObject() {
        tokens = new ArrayList<>();
        return tokens;
    }

    public void getToken(String input) {
        String[] separation = input.split("(\\s+|(?=\\p{Punct})|(?<=\\p{Punct}))" +
                "(?=([^\"]*\"[^\"]*\")*[^\"]*$)(?=([^*/]*/\\*[^/*]*\\*/)*[^*/]*$)");
        //splitting by whitespace, punctuation, and " "
        String[] array = new String[separation.length];
        for (int i = 0; i < separation.length; i++) {
            System.out.println(separation[i].charAt(separation[i].length() - 1));
            if (separation[i] != "") {
                if ((i + 1 < separation.length) && separation[i].equals(separation[i + 1])) {
                    tokens.set(i, separation[i].trim() + separation[i + 1].trim());
                    i++;                                                                        //add 1 so the function can skip (i + 1)
                }
                else if ((i + 1 < separation.length) && separation[i + 1].matches("=") && separation[i].matches("\\p{Punct}")) {
                    tokens.set(i, separation[i].trim() + separation[i + 1].trim());
                    i++;                                                                        //add 1 so the function can skip (i + 1)
                }
                //code to ignore line comment
                else if (separation[i].charAt(0) == '/' && separation[i].charAt(1) == '/') {
                    System.out.println();
                    System.exit(0);     //exit if there are errors
                }
                //code to ignore block comment
                else if (separation[i].charAt(separation[i].length() - 1) == '/' && separation[i].charAt(separation[i].length() - 2) == '*') {    // */
                    System.exit(0);
                }
                else if (separation[i].charAt(separation[i].length() - 1) == '*' && separation[i].charAt(separation[i].length() - 2) == '/') {    // /*
                    String[] array1 = Arrays.copyOfRange(separation, 0, separation.length - 2);
                    for (int j = 0; j < separation[i].length(); i++) {
                        tokens.add(i, separation[i]);
                    }
                }
                else {
                    lex(separation[i].trim());              //print out all tokens that arent ++, --, !=, ==, -=, +=, *=, /=
                }                                           //calls on lex function so that the lexeme can be labeled
            }
        }
    }

    public static void lex(String input) {
        char array[] = input.toCharArray();         //put in a char array. this code is mainly for boolean bool
        boolean bool = Character.isDigit(array[0]); //check on the first digit to see if it's a number
        boolean letter = true;
        if (input.startsWith("\"") && input.endsWith("\"")) {   //check if the input is in " "
            letter = false;
        }
        if (!letter) {           //if input is in " "
            tokens.add(input);
        }
        else if (input.matches("for|while|if|else")) {     //keyword and not in " "
            tokens.add(input);
        }
        else if (input.matches("\\p{Punct}")) {    //punctuations/operations (like + that combines 2 strings)
            tokens.add(input);
        }
        else if (input.matches("[A-Za-z0-9_]+$") && (!bool)) { //identifier (letters, digits, underscores)
            tokens.add(input);
        }
        else if (input.matches("[0-9]*[A-Za-z_]")) {    //identifier that starts with a number
            System.out.println(input + " - Lexical Error (Identifier can't start with a number)");
            System.exit(0);
        }
        else if (input.matches("[0-9]+$")) {        //numbers
            tokens.add(input);
        }
        else {
            System.out.println(input + " - Error");
            System.exit(0);
        }
    }
}

/* #5 - use denotational semantics to define selection statement
    (The above selection statement: if-else)
    M_e(<expr>, s)
    <lex> --> if <stmnt>
              [else <stmt>]

    M_e(<expr>, s) --> if <expr> == <boolean>
                            return <input>
                       else if <expr> == <for> | <while> | <if> | <else>
                            return < input>
                       else if <expr> == <punctuation>
                            return <input>
                       else if <expr> == <letters>
                            return <input>
                       else if <expr> == <digit><letters>
                            return error
                       else if <expr> == <digits>
                            return <input>
                       else
                            return error
*/

/*tokens
#define REAL_LIT    //represents fractional number
#define NAT_LIT     //represents whole numbers and 0
#define BOOL_LIT    //bool literal
#define CHAR_LIT    //char literal
#define STRING_LIT  //string literal
#define SELECT_STM  //selection statement
#define LOOP_STM    //loop statement
#define VAR_DEC     //variable declaration
#define STR
#define NAT
#define CHAR
#define REAL
#define BOOL

//special symbols
#define ADD
#define SUB
#define MUL
#define DIV
#define EXP
#define GRT_THAN
#define LSS_THAN
#define GRT_EQU
#define LSS_EQU
#define PARENTHESE
#define EQUAL
#define NOT_EQU
#define UNARY_NEG
#define LOG_NOT
#define LOG_AND
#define LOG_OR
 */
