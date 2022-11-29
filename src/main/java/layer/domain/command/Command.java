
package layer.domain.command;

public interface Command {

    Commands getCommandName();

    String getCommandValue();

    boolean hasCommandValue();

    boolean isUnknown();
}
