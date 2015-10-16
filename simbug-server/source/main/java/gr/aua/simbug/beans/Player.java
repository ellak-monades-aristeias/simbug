package gr.aua.simbug.beans;

import java.util.Map;

public class Player
{
	private String uuid; 
	
	private Map<String, String> choiceVariables;

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
		String str = "Player [uuid=" + uuid + ",choiceVariables[";
		if (choiceVariables != null)
		{
			for (Map.Entry<String, String> entry : choiceVariables.entrySet()) 
			{
				str += entry.getKey() + " : " + entry.getValue() + ",";
			}
		}
		str += "] ]";
		return str;
	}

	public Map<String, String> getChoiceVariables() {
		return choiceVariables;
	}

	public void setChoiceVariables(Map<String, String> choiceVariables) {
		this.choiceVariables = choiceVariables;
	}
	
	
}
