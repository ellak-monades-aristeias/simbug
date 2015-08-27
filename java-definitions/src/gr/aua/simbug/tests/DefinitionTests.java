package gr.aua.simbug.tests;

import static org.junit.Assert.fail;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import gr.aua.simbug.definition.*;;

public class DefinitionTests {
	
	private String definitionFile = "src/gr/aua/simbug/examples/hello_world/hello_world.def.xml";
	private Definition definition;
	
	private File xml;
	
	private JAXBContext jc;
	private Unmarshaller unmarshaller;


	@Before
	public void setUp() throws JAXBException {
		jc = JAXBContext.newInstance(Definition.class);
		unmarshaller = jc.createUnmarshaller();
		xml = new File(definitionFile);
		definition = (Definition) unmarshaller.unmarshal(xml);
	}

	@Test
	public void testUnMarshalDefinitionfile()  {
		System.out.println(definition.toString());
		System.out.println(definition.getChoicesToStateAlgorithm());
	}
	
	@Test
	public void testAlgorithm() {
		System.out.println(definition.toString());
		System.out.println(definition.getChoicesToStateAlgorithm());
	}
}
