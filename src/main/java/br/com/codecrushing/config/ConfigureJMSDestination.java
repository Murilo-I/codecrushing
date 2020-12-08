package br.com.codecrushing.config;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(
	destinationName = "java:/jms/topics/PurchaseCarTopic",
	name = "java:/jms/topics/PurchaseCarTopic",
	interfaceName = "javax.jms.Topic"
)
@Singleton
public class ConfigureJMSDestination {
	
}
