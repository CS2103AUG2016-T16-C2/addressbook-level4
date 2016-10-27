package seedu.address.testutil;


import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deadline.Deadline;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.*;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }
  //@@author A0144202Y
    public TaskBuilder withStartline(String startline) throws IllegalValueException{
    	this.task.setStartline(new Startline(startline));
    	return this;
    }

    public TaskBuilder withDeadline(String deadline) throws IllegalValueException {
    	this.task.setDeadline(new Deadline(deadline));
        return this;
    }

    public TaskBuilder withPriority(String priority) throws IllegalValueException {
        this.task.setPriority(new Priority(priority));
        return this;
    }
  //@@author
    public TestTask build() {
        return this.task;
    }

}
