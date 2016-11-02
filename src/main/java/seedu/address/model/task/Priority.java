package seedu.address.model.task;


import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's priority in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_PRIORITY_CONSTRAINTS =
            "Task priority should be an integer between 1 and 5";
    public static final String PRIORITY_VALIDATION_REGEX = "\\d+";
    
    private static final int COMPARE_PRIORITY_EQUAL = 0;
    private static final int COMPARE_PRIORITY_SMALLER = -1;
    private static final int COMPARE_PRIORITY_BIGGER = 1;

    public String value;
    public int priorityValue;
    
    /**
     * Validates given priority.
     *
     * @throws IllegalValueException if given priority string is invalid.
     */

    public Priority(String priority) throws IllegalValueException {
        assert priority != null;
        priority = priority.trim();
        if (!isValidPriority(priority)) {
            throw new IllegalValueException(MESSAGE_PRIORITY_CONSTRAINTS);
        }
        this.value = priority;
        
        priorityValue = Integer.parseInt(priority);
    }

    /**
     * Returns if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
    	if(Integer.parseInt(test) >= 0 && Integer.parseInt(test) < 6) {
    		return test.matches(PRIORITY_VALIDATION_REGEX);
    	}
    	return true;
    }
    
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && this.value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compares given deadlines.
     * returns 0 if the priorities are equal
     * returns 1 if the priority value compared with is bigger
     * return -1 if the priority value compared with is smaller
     */
	public int compareTo(Priority priorityA) {
		if (this.priorityValue > priorityA.priorityValue) {
            return COMPARE_PRIORITY_BIGGER;
        } else if (this.priorityValue < priorityA.priorityValue) {
            return COMPARE_PRIORITY_SMALLER;
        } else {
            return COMPARE_PRIORITY_EQUAL;
        }
    }

}

    
