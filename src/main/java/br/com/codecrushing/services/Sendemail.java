package br.com.codecrushing.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.codecrushing.daos.PurchaseDao;
import br.com.codecrushing.infra.MailSender;
import br.com.codecrushing.models.Purchase;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", 
				propertyValue = "java:/jms/topics/PurchaseCarTopic"),
		@ActivationConfigProperty(propertyName = "destinationType",
				propertyValue = "javax.jms.Topic")
})
public class Sendemail implements MessageListener {
	
	@Inject
	private MailSender mail;
	@Inject
	private PurchaseDao dao;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;	
			Purchase p = dao.findByUUID(textMessage.getText());
		
			String sender = "administrator@codecrushing.com";
			String subject = "Compra na Casa do Código";
			String messageBody = "Pagamento efetuado com sucesso para o pedido "+p.getUUID();
			
			mail.send(sender,p.getUser().getEmail(),subject,messageBody);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
