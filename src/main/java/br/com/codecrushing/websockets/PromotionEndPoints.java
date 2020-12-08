package br.com.codecrushing.websockets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.codecrushing.models.Promotion;

@ServerEndpoint(value = "/chanel/promotions")
public class PromotionEndPoints {
	
	@Inject
	private SessionUser su;
	
	@OnOpen
	public void onMessage(Session session) {
		su.add(session);
	}
	
	public void send(Promotion p) {
		List<Session> sessions = su.getUsers();
		for (Session session : sessions) {
			if(session.isOpen()) {
				try {
					session.getBasicRemote().sendText(p.toJson());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}			
		}
	}
	
	@OnClose
	public void close(Session session, CloseReason reason) {
		su.remove(session);
	}
}
