package SwiftSeleniumWeb;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * This class has been created to hide the complexities of Robot class and
 * provide easy to use methods.
 * 
 * @author Tripti
 * 
 */
class SmartRobot extends Robot {

	public SmartRobot() throws AWTException {
		super();
	}

	/**
	 * This method presses the TAB key and releases the same
	 */
	public void pressTab() {
		keyPress(KeyEvent.VK_TAB);
		keyRelease(KeyEvent.VK_TAB);
	}

	/**
	 * This method presses the ENTER key and releases the same
	 */
	public void pressEnter() {
		keyPress(KeyEvent.VK_ENTER);
		keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * This method copies the required text [username/password] onto the
	 * clipboard
	 * 
	 * @param string
	 *            [username/password]
	 */
	private void writeToClipboard(String s) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = new StringSelection(s);
		clipboard.setContents(transferable, null);
	}

	/**
	 * This method presses Ctrl+V to paste the text on the clipboard
	 */
	public void pasteClipboard() {
		keyPress(KeyEvent.VK_CONTROL);
		keyPress(KeyEvent.VK_V);
		delay(50);
		keyRelease(KeyEvent.VK_V);
		keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 * This method reads and write the required text in the Browser
	 * Authentication Textboxes
	 * 
	 * @param text
	 */
	public void type(String text) {
		writeToClipboard(text);
		pasteClipboard();
	}

}
