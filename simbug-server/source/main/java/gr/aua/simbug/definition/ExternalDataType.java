//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.07 at 03:14:52 PM EEST 
//


package gr.aua.simbug.definition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExternalDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{}ParameterType">
 *       &lt;sequence>
 *         &lt;element name="uriLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalDataType", propOrder = {
    "uriLocation"
})
public class ExternalDataType
    extends ParameterType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uriLocation;

    /**
     * Gets the value of the uriLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriLocation() {
        return uriLocation;
    }

    /**
     * Sets the value of the uriLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriLocation(String value) {
        this.uriLocation = value;
    }

}