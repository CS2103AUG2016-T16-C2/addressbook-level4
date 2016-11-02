package seedu.address.ui;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.TaskManager;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private static final String FXML = "PersonListPanel.fxml";    
    private static final int IMPOSSIBLE_HIGH_PRIORITY_VALUE = 10;
    private static final int LOW_POSITION = 1;
    private VBox panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<ReadOnlyTask> personListView;

    public PersonListPanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static PersonListPanel load(Stage primaryStage, AnchorPane personListPlaceholder,
                                       ObservableList<ReadOnlyTask> personList) {
        PersonListPanel personListPanel =
                UiPartLoader.loadUiPart(primaryStage, personListPlaceholder, new PersonListPanel());
        personListPanel.configure(personList);
        return personListPanel;
    }

    private void configure(ObservableList<ReadOnlyTask> personList) {
        setConnections(personList);
        addToPlaceholder();
    }
    
    /**
     * Compares given deadlines.
     * Compares priorities if the deadlines are equal
     * Sets priority value of a floating task to impossible high priority value
     * Sorts the floating tasks of the same deadlines as other tasks to lower position in the list
     */
    private void setConnections(ObservableList<ReadOnlyTask> personList) {
    	FilteredList<ReadOnlyTask> filteredData = new FilteredList<>(personList, p -> true);
    	SortedList<ReadOnlyTask> sortedData = new SortedList<>(filteredData);
    	sortedData.setComparator(new Comparator<ReadOnlyTask>() {
			@Override
			public int compare(ReadOnlyTask A, ReadOnlyTask B) {
				if (A.getDeadline().deadlineDate == null || B.getDeadline().deadlineDate == null) {
					return LOW_POSITION;
				}
				else {
				int compareNo = (A.getDeadline().calendar).compareTo(B.getDeadline().calendar);
				if(compareNo == 0) {
					Integer a = Integer.parseInt(A.getPriority().value);
					Integer b = Integer.parseInt(B.getPriority().value);
					if (a == 0) {
						a = IMPOSSIBLE_HIGH_PRIORITY_VALUE;
					}
					compareNo = (a).compareTo(b);
				}
				return compareNo;
				}
			}
		});
        personListView.setItems(sortedData);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }
    
    private ObservableValue<? extends Comparator<? super ReadOnlyTask>> observableDeadline(ObservableList<ReadOnlyTask> personList){
    	personList.sort(new Comparator<ReadOnlyTask>() {
			@Override
			public int compare(ReadOnlyTask A, ReadOnlyTask B) {
				return (A.getDeadline()).compareTo(B.getDeadline());
			}
		});
    	return null;
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        personListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                raise(new PersonPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            personListView.scrollTo(index);
            personListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class PersonListViewCell extends ListCell<ReadOnlyTask> {

        public PersonListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyTask person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(PersonCard.load(person, getIndex() + 1).getLayout());
            }
        }
    }

}
