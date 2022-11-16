package worldOfFood.textUI;

import java.util.Scanner;

import worldOfFood.Command;
import worldOfFood.Game;

public class Parser {
    private final Game game;
    private Scanner scan;

    public Parser(Game game) {
        this.game = game;
        this.scan = CommandLineClient.scan();
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = scan.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }
        tokenizer.close();
        return game.getCommand(word1, word2);
    }
}
