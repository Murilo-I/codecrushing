package br.com.codecrushing.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder;

public class PassGenerator {

	public static void main(String[] args) {
		System.out.println(new PassGenerator().generate("mustbeadministrator"));
	}
	
	public String generate(String password) {
		try {
			byte[] digest = MessageDigest.getInstance("sha-256").digest(password.getBytes());
			return Base64Encoder.encode(digest);
		} catch (NoSuchAlgorithmException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
