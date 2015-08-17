package gr.aua.simbug;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Definition")
public class Definition {
	
	private List<ConfigurationParameters> configuration;
	
	@XmlElement
	public List<ConfigurationParameters> getConfiguration() {
		return this.configuration;
	}

}


class ConfigurationParameters {
	
	
}
