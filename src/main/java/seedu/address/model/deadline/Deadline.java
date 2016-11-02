package seedu.address.model.deadline;

import seedu.address.model.task.Startline;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's deadline in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline extends Startline {
    
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Task deadline must be in ddmmyy or dd-MM-yy format.";
    public static final String DEADLINE_VALIDATION_REGEX = "\\d+";
    public static final String DEADLINE_DASH_VALIDATION_REGEX = "[\\d]+-[\\d]+-[\\d]+";

    private static final int COMPARE_DEADLINE_NULL = 0;
    private static final int COMPARE_DEADLINE_SMALLER = -1;
    private static final int COMPARE_DEADLINE_BIGGER = 1;

	public String deadlineDate;
	public boolean isOverdue = false;
	
    /**
     * Validates given deadline.
     *
     * @throws IllegalValueException if given date string is invalid.
     */
    public Deadline(String deadline) throws IllegalValueException {
    	super(deadline);

    }
    
    public void setOverdue(boolean value){
    	this.isOverdue = value;
    }
    
    /**
     * Compares given deadlines.
     * returns 0 if the deadlines are equal
     * returns 1 if the deadlineDate compared with is later
     * return -1 if the deadlineDate compared with is earlier
     */
	public int compareTo(Deadline deadlineA) {
		if (this.deadlineDate == null) {
            return COMPARE_DEADLINE_NULL;
        } else if (Integer.parseInt(this.deadlineDate) > Integer.parseInt(deadlineA.deadlineDate)) {
            return COMPARE_DEADLINE_BIGGER;
        } else {
           return COMPARE_DEADLINE_SMALLER;
        }
	}

}