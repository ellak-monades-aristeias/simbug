package gr.aua.simbug.utils;

import gr.aua.simbug.beans.Player;
import gr.aua.simbug.definition.Definition;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class SimbugUtils
{

	static public Definition xmlStringToDefinition(String definitionString)
	{
		JAXBContext jc;
		Unmarshaller unmarshaller;
		Definition definition = null;
		try
		{
			jc = JAXBContext.newInstance(Definition.class);
			unmarshaller = jc.createUnmarshaller();

			InputStream xmlStream = new ByteArrayInputStream(definitionString.getBytes(StandardCharsets.UTF_8));
			definition = (Definition) unmarshaller.unmarshal(xmlStream);
		}
		catch (JAXBException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return definition;
	}

	static public String fileToString(String fileName)
	{
		String definitionString = null;
		try
		{
			FileInputStream fisTargetFile = new FileInputStream(new File(fileName));
			definitionString = org.apache.commons.io.IOUtils.toString(fisTargetFile, "UTF-8");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return definitionString;
	}
	
	static public List<Player> jsonToList(String jsonString)
	{
		System.out.println(jsonString);
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<Player> players = new ArrayList<Player>();
		try
		{
			players = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Player.class));
		}
		catch (JsonParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return players;
	}

}
