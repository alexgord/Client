/*********************************XMLStatHandler CLASS INFO*****************************************

This class is responsible for parsing the XML sent to it. This class places the 
messages it extracts from the XML into an arraylist of Message objects.  

 *********************************************************************************************/
package tmp.test.pkg;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLStatHandler implements ErrorHandler, ContentHandler
{
	private boolean error;
	private ArrayList<String> messages;
	private String toAdd;

	public boolean hasError() { return error; }
	private ArrayList<Message> objMessages;
	private Message currentMessage;

	public ArrayList<Message> getObjMessages()
	{
		return objMessages;
	}

	public XMLStatHandler()
	{
		messages = new ArrayList<String>();
		objMessages = new ArrayList<Message>();
	}

	/* ==========================================================================
	 * 	   * ContentHandler 
	 * 	   * --------------------------------------------------------------------------
	 * 	   */

	@Override
	public void error(SAXParseException exception) throws SAXException
	{
		errorHelper("ERROR", exception.getLineNumber(), exception.getMessage());
		error = true;
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException
	{
		errorHelper("FATAL", exception.getLineNumber(), exception.getMessage());
		System.exit(1);
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException
	{
		errorHelper("WARNING", exception.getLineNumber(), exception.getMessage());		
	}

	private void errorHelper(String heading, int line, String message)
	{
		System.err.println("[" + heading + "] line " + line + ": " + message);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		String contents = String.copyValueOf(ch, start, length);
		if(!contents.trim().equals(""))
		{
			toAdd += contents;
			currentMessage.setMessage(contents);
		}
	}

	@Override
	public void endDocument() throws SAXException
	{
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if (localName.equals( "message"))
		{
			getMessages().add(toAdd);
			objMessages.add(currentMessage);
		}
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException
	{
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException
			{
			}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException
			{
			}

	@Override
	public void setDocumentLocator(Locator arg0)
	{
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException
	{
	}

	@Override
	public void startDocument() throws SAXException
	{
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
	{
		toAdd = "";
		if (qName.equals( "message"))
		{
			String user = atts.getValue(atts.getIndex("user"));
			toAdd = "[" + user + "] ";
			currentMessage = new Message();
			currentMessage.setName(user);
			currentMessage.setTime(atts.getValue(atts.getIndex("time")));
		}
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException
			{
			}

	public ArrayList<String> getMessages()
	{
		return messages;
	}

}

