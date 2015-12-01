import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class XMLDataExtractor {

	//xml to parse
	private String[] xmlFiles = null;
	
	public XMLDataExtractor(String[] xmlFiles) {
		this.xmlFiles = xmlFiles;
	}
	
	public void parseXMLForCountriesBasedOnMap(){
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			for(String xmlFileName:xmlFiles){
				saxParser.parse(xmlFileName, new SAXHandler());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
