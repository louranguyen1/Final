import java.util.List;

public class Syntax {       //takes in a list of token object in its constructor
    static List<String> tokens;

    Syntax(List<String> tokens) {
        this.tokens = tokens;
    }

    boolean parse() {
        function();
        return true;
    }
    /* top down parser grammar rules
    function
    <function>  --> for (<expr>)
                    [if (<boolexpr> | <boolexpr>) <statement>]
                --> [if (<boolexpr>) <statement>]
     [] - 0 or 1
     */
    public static void function() {
        int count_b = 0;
        int count_p = 0;
        int count_o = 0;
        int count_q = 0;
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.equals('"') || tokens.equals('\'')) {    //check to see how many " or '
                count_q++;
            }
            if (tokens.equals('[') || tokens.equals(']')) {     //check to see how many [ or ]
                count_b++;
            }
            if (tokens.equals('{') || tokens.equals('}')) {     //check to see how many { or }
                count_p++;
            }
            if (tokens.equals('(') || tokens.equals(')')) {     //check to see how many ( or )
                count_o++;
            }
        }

        //if it's odd, then that means they aren't close since it should be in pairs
        if (count_q % 2 != 0) {
            System.out.println("Syntax Error (quotations aren't close)");
        }

        if (count_b % 2 != 0) {
            System.out.println("Syntax Error (brackets aren't close)");
        }
        if (count_p % 2 != 0) {
            System.out.println("Syntax Error (braces aren't close)");
        }
        if (count_o % 2 != 0) {
            System.out.println("Syntax Error (parentheses aren't close)");
        }
    }
}

/* #6 use denotational semantics to define loop statement
    (The above for loop)
    M_e(<expr>, s)
    <function>  --> for (<expr>)
                    [if (<boolexpr> | <boolexpr>) <statement>]
                --> [if (<boolexpr>) <statement>]

    M_e(<expr>, s) --> for (<expr>)
                        if (<boolexpr> | <boolexpr>) <statement>
                            return count1
                        if (<boolexpr> | <boolexpr>) <statement>
                            return count2
                        if (<boolexpr> | <boolexpr>) <statement>
                            return count3
                        if (<boolexpr> | <boolexpr>) <statement>
                            return count4
 */
