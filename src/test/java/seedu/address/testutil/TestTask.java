package seedu.address.testutil;

import seedu.address.model.deadline.Deadline;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.*;

/**
 * A mutable person object. For testing only.
 */
//@@author A0141812R
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Startline startline;
    private Deadline deadlines;
    private Priority priority;
    private UniqueTagList tags;
  //@@author
    
    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setName(Name name) {
        this.name = name;
    }
  //@@author A0144202Y
    public void setStartline(Startline startline){
    	this.startline = startline;
    }

    public void setDeadline(Deadline deadline) {
        this.deadlines = deadline;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
  //@@author
    @Override
    public Name getName() {
        return name;
    }
  //@@author A0139097U
    @Override
    public Priority getPriority() {
        return priority;
    }
    
    @Override
    public Startline getStartline(){
    	return startline;
    }

    @Override
    public Deadline getDeadline() {
        return deadlines;
    }
    //@@author
    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }
  //@@author A0139097U
    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        sb.append("s/" + this.getStartline().value + " ");
        sb.append("d/" + this.getDeadline().value + " ");
        sb.append("p/" + this.getPriority().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
  //@@author 
}
 