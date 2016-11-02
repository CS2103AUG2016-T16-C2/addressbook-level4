package seedu.address.logic.commands;

import java.util.Set;

import seedu.address.model.task.UniqueTaskList.DuplicateTaskException;

/**
 * Lists all tasks that is not completed in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all incomplete tasks";
    
	private static final String LIST_ARG_DEADLINE = "d/";
	private static final String LIST_ARG_PRIORITY = "p/";
	
	private Set<String> keywords;

    public ListCommand() {}
    
	@Override
	public CommandResult execute() {
		if (keywords.contains(LIST_ARG_DEADLINE) || keywords.contains(LIST_ARG_PRIORITY)) {
			try {
				model.updateFilteredListToShowIncompleteTask();
			} catch (DuplicateTaskException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new CommandResult(MESSAGE_SUCCESS);
	}
}
