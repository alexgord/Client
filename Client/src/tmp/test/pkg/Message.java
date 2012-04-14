package tmp.test.pkg;

public class Message
{
	private String name;
	private String message;
	private String time;
	
	Message()
	{
		name = "";
		message = "";
		time = "";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}
}
