package readXML;
import java.io.FileWriter;
import java.io.File;

import javax.xml.parsers.*;  
import org.w3c.dom.*;  
import org.xml.sax.*;  

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		FileWriter writer;
        try  
        {  
            DocumentBuilder db = dbf.newDocumentBuilder();  
            String path = "./";
            File file = new File(path);
            File[] array = file.listFiles();
            writer = new FileWriter("output.txt");
            
            for(int j=0;j<array.length;j++){
            	 if(array[j].isFile() &&  array[j].getName().contains(".xml")){
            		 
            		 Document doc = db.parse(array[j].getName());   
            		 NodeList dogList = doc.getElementsByTagName("item");  
            		 for (int i = 0; i < dogList.getLength(); i++)  
                     {  
                         Node dog = dogList.item(i);  
                         for (Node node = dog.getFirstChild(); node != null; node = node.getNextSibling())  
                         {  
                             if (node.getNodeType() == Node.ELEMENT_NODE && node.getFirstChild() != null)  
                             {  
                                // String name = node.getNodeName();
                            	 String value = "";
                            	 if(node.getFirstChild().getNodeValue()!= null)
                            	 {
                            		 value = node.getFirstChild().getNodeValue();  
                            	 }
                                 value = value.replace("\n", "");
                                 writer.write(value + ";");
                             }
                             else{
                            	 writer.write(" ");
                             }
                         }  
                         writer.write("\r\n"); 
                     }
            		
            	 }
            }           
            writer.flush();
            writer.close();
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  

	}

}
