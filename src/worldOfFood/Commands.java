package worldOfFood;

public enum Commands {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inv"), EAT("eat"), COLLECT("collect"), STATS("stats");

    private String commandName;

    Commands(String commandString) {
        this.commandName = commandString;
    }

    public String toString() {
        return commandName;
    }
}