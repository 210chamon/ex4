package pubSub;

import javax.naming.InitialContext;
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;
import javax.jms.DeliveryMode;
import javax.jms.TopicSession;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

public class Publisher
{
    public static void main(String[] args) throws NamingException, JMSException
    {
       // get the initial context
       InitialContext ctx = new InitialContext();

       // lookup the topic object
       Topic topic = (Topic) ctx.lookup("Example.Library.Publication");

       // lookup the topic connection factory
       TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
           lookup("topic/connectionFactory");

       // create a topic connection
       TopicConnection topicConn = connFactory.createTopicConnection();

       // create a topic session
       TopicSession topicSession = topicConn.createTopicSession(false,
           Session.AUTO_ACKNOWLEDGE);

       // create a topic publisher
       TopicPublisher topicPublisher = topicSession.createPublisher(topic);
       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

       // create the text message from XML
       private static Document parseXml() throws Exception {
		       DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				       .newInstance();
		       DocumentBuilder documentBuilder = documentBuilderFactory
				       .newDocumentBuilder();
		       return documentBuilder.parse(JmsXmlAsTextMessage.class
				       .getResourceAsStream("excercise-1.xml"));
	}

	     public static String getXmlAsString(Document document) throws Exception {
		       TransformerFactory tf = TransformerFactory.newInstance();
		       Transformer transformer = tf.newTransformer();
		       transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		       StringWriter writer = new StringWriter();
		       transformer
				        .transform(new DOMSource(document), new StreamResult(writer));
		       String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		       return output;
	}

	     public static Document getXmlAsDOMDocument(String xmlString) throws Exception {
		       DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				       .newInstance();
		       DocumentBuilder documentBuilder = documentBuilderFactory
				       .newDocumentBuilder();
		       return documentBuilder.parse(
           new InputSource(new StringReader(xmlString)));
	}
}

       // publish the messages
       topicPublisher.publish(message);

       // print what we did
       System.out.println("Message published: " + message.getText());

       // close the topic connection
       topicConn.close();
    }
}
