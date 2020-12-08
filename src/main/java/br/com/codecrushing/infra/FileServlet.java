package br.com.codecrushing.infra;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/system/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().split("/system")[1];
		
		Path source = Paths.get(FileSaver.SERVER_PATH + path);
		FileNameMap map = URLConnection.getFileNameMap();
		String contenType = map.getContentTypeFor("file:"+source);
		
		response.reset();
		response.setContentType(contenType);
		response.setHeader("Content-Lenght", String.valueOf(Files.size(source)));
		response.setHeader("Content-Disposition",
				"filename=\"" +source.getFileName().toString()+ "\"");
		
		FileSaver.transfer(source, 	response.getOutputStream()); 
	}
}
