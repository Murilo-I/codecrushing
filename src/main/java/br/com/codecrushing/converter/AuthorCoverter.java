package br.com.codecrushing.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.codecrushing.models.Author;

@FacesConverter("authorConverter")
public class AuthorCoverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.trim().isEmpty()) 
	        return null;

	    Author author = new Author();
	    author.setId(Integer.valueOf(id));

	    return author;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null)
	        return null;

	    Author author = (Author) value;
	    return author.getId().toString();
	}

}
