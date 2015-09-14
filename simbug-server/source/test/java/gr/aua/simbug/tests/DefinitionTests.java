package gr.aua.simbug.tests;

import gr.aua.simbug.definition.Definition;
import gr.aua.simbug.utils.SimbugUtils;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

;

public class DefinitionTests
{
	private String definitionFile = "source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
	private Definition definition;

	@Before
	public void setUp() throws JAXBException
	{
		String definitionString = SimbugUtils.fileToString(definitionFile);
		definition = SimbugUtils.xmlStringToDefinition(definitionString);
	}

	@Test
	public void testUnMarshalDefinitionfile()
	{
		System.out.println(definition.toString());
		System.out.println(definition.getChoicesToStateAlgorithm());
	}

	@Test
	public void testAlgorithm()
	{
		System.out.println(definition.toString());
		System.out.println(definition.getChoicesToStateAlgorithm());
	}
}
