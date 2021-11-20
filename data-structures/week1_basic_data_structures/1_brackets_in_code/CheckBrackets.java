import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    char type;
    int position;

    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }
}

class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> openingBracketsStack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                openingBracketsStack.add(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                Bracket openingBracket;

                if (openingBracketsStack.isEmpty()) {
                    openingBracket = null;
                } else {
                    openingBracket = openingBracketsStack.pop();
                }

                if (openingBracket == null || !openingBracket.match(next)) {
                    System.out.println(position + 1);
                    return;
                }
            }
        }

        if (!openingBracketsStack.isEmpty()) {
            Bracket firstUnmatched = new Bracket('x', -1);

            while (!openingBracketsStack.isEmpty()) {
                firstUnmatched = openingBracketsStack.pop();
            }

            System.out.println(firstUnmatched.position + 1);
            return;
        }

        System.out.println("Success");
    }
}
