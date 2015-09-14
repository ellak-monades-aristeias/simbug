package gr.aua.simbug.beans;

public class Player
{
	private String uuid; 

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	@Override
	public String toString()
	{
		return "Player [uuid=" + uuid + "]";
	}
	
	
}
