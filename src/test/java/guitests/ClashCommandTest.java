package guitests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

//@@author A0139097U
public class ClashCommandTest extends TaskManagerGuiTest {
	
	@Test
	public void clash_nonEmptyList(){
		commandBox.runCommand("clash");
		assertTrue(personListPanel.isListMatching(td.fiona, td.george));
		assertListSize(2);
	}
	
}
