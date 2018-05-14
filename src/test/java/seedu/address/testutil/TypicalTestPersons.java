package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TaskManager;
import seedu.address.model.task.*;

/**
 *
 */
public class TypicalTestPersons {

    public static TestTask alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestPersons() {
        try {
            alice =  new TaskBuilder().withName("Alice Pauline").withStartline("101213 14:15").withDeadline("12-36-08 23:44")
                    .withPriority("1")
                    .withTags("friends").build();
            benson = new TaskBuilder().withName("Benson Meier").withStartline("101213 14:15").withDeadline("311202 23:44")
                    .withPriority("2")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withName("Carl Kurz").withPriority("3").withStartline("101213 14:15").withDeadline("121212 23:44").build();
            daniel = new TaskBuilder().withName("Daniel Meier").withPriority("4").withStartline("101213 14:15").withDeadline("101010 23:44").build();
            elle = new TaskBuilder().withName("Elle Meyer").withPriority("5").withStartline("101213 14:15").withDeadline("111111 23:44").build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withPriority("2").withStartline("101213 14:15").withDeadline("000000 23:44").build();
            george = new TaskBuilder().withName("George Best").withPriority("1").withStartline("101213 14:15").withDeadline("000000 23:44").build();

            //Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withPriority("1").withStartline("101213 14:15").withDeadline("000000 23:44").build();
            ida = new TaskBuilder().withName("Ida Mueller").withPriority("1").withStartline("101213 14:15").withDeadline("999999 23:44").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {

        try {
            ab.addTask(new Task(alice));
            ab.addTask(new Task(benson));
            ab.addTask(new Task(carl));
            ab.addTask(new Task(daniel));
            ab.addTask(new Task(elle));
            ab.addTask(new Task(fiona));
            ab.addTask(new Task(george));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalPersons() {
        return new TestTask[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public TaskManager getTypicalTaskManager(){
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}
