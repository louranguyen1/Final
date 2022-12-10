package com.company;

public class Token {        //class for token
    public static String lexeme;   //string for lexeme representation
    public static int tokenCode;       //int for token code

    public Token(String lexeme, int tokenCode) {
        this.lexeme = lexeme;
        this.tokenCode = tokenCode;
    }
}

/* #7 - use denotational semantics to define expression statement
    public static int tokenCode;
    tokenCode can't be fraction or decimal numbers
    M_e(<expr>, s)
    <expr> --> <var> | <binary_expr>

    M_e(<expr>, s) --> if <expr> == <var>
                            if VARMAP[<var>, s] == undef
                                return error
                            else
                                return VARMAP[<var>, s]
                       else if <expr> == <binary_expr>
                            return M_bin(<binary_expr>)
                       else
                            return error

    #8 use denotational semantics to redefine expression statement so it can return boolean solution
    M_e(<expr>, s) --> if <expr> == <var>
                            if VARMAP[<var>, s] == undef
                                bool(false)
                            else
                                bool(true)
                       else if <expr> == <binary_expr>
                            bool(true)
                       else
                            bool(false)
 */
