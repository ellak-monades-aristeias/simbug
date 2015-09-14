package gr.aua.simbug.utils;

import static org.junit.Assert.assertEquals;
import gr.aua.simbug.beans.Player;
import gr.aua.simbug.definition.Definition;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimbugUtilsTest
{
	private String fileName = "source/test/java/gr/aua/simbug/tests/hello_world.def.xml";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testXmlStringToDefinition()
	{
		String definitionString = SimbugUtils.fileToString(fileName);
		Definition definition = SimbugUtils.xmlStringToDefinition(definitionString);
		System.out.println(definition.toString());
		System.out.println(definition.getChoicesToStateAlgorithm());
		System.out.println(definition.getConfiguration().getClass());
		System.out.println(definition.getExternalParameters());
		System.out.println(definition.getPlayerChoiceVariables().getPlayerChoiceVariable().get(0));
		System.out.println(definition.getPlayerStateVariables().getPlayerStateVariable().get(0));
		System.out.println(definition.getRandomNumberGenerators());
		System.out.println(definition.getWorldStateVariables());
	}

	@Test
	public void testFileToString()
	{
		String res = SimbugUtils.fileToString(fileName);
		System.out.println(res);
	}

	@Test
	public void jsonToListTest() 
	{
		String jsonString = "[{\"uuid\":\"1\"},{\"uuid\":\"2\"},{\"uuid\":\"3\"},{\"uuid\":\"4\"},{\"uuid\":\"5\"}]";
		List<Player> list = SimbugUtils.jsonToList(jsonString);

		System.out.println(list.get(0));
		
	    assertEquals(5, list.size());
	}

}
