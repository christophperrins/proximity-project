package uk.co.bpdts.proximity.mapping;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import uk.co.bpdts.proximity.models.Person;

/**
 * Instructions to turn a PersonJSON object into a Person POJO
 * @author chris
 *
 */
public class PersonDeserialiser extends StdDeserializer<Person> {

	public PersonDeserialiser() {
		this(null);
	}

	public PersonDeserialiser(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Person deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		Long id = node.get("id").asLong();
		
		String firstName = node.get("first_name").asText();
		firstName = firstName == null ||  firstName.isEmpty() ? null : firstName;
		
		String lastName = node.get("last_name").asText();
		lastName = lastName == null ||  lastName.isEmpty() ? null : lastName;
		
		String email = node.get("email").asText();
		email = email == null ||  email.isEmpty() ? null : email;
		
		String ipAddress = node.get("ip_address").asText();
		ipAddress = ipAddress == null || ipAddress.isEmpty() ? null : ipAddress;
		
		Double latitude = node.get("latitude").asDouble();
		Double longitude = node.get("longitude").asDouble();
		return new Person(id, firstName, lastName, email, ipAddress, latitude, longitude);

	}
}
