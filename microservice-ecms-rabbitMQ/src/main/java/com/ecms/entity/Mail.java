package com.ecms.entity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Entity class that contains mail related information (to,from,subject,content)
 *
 * @author nagpalh
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Mail.class)
public class Mail {

	private String from;
	private String to;
	private String subject;
	private String content;
	private Map<String, String> templateVariableMap;

	public Mail() {
		super();
	}

	public Mail(String from, String to, String subject, String content, Map<String, String> templateVariableMap) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.templateVariableMap = templateVariableMap;
	}

	public Mail(String from, String to, String subject) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getTemplateVariableMap() {
		return templateVariableMap;
	}

	public void setTemplateVariableMap(Map<String, String> templateVariableMap) {
		this.templateVariableMap = templateVariableMap;
	}

	
	/**
	 * Manual toString() function For object conversion.
	 */
	@Override
	public String toString() {
		JSONObject jsonInfo = new JSONObject();
		try {
			jsonInfo.put("from", this.from);
			jsonInfo.put("to", this.to);
			jsonInfo.put("subject", this.subject);
			jsonInfo.put("content", this.content);
			StringWriter out = new StringWriter();
			JSONValue.writeJSONString(templateVariableMap, out);
			String jsonText = out.toString();
			jsonInfo.put("templateVariableMap", jsonText);
		} catch (IOException | JSONException e) {
			e.getStackTrace();
			e.getLocalizedMessage();
			e.getMessage();
		}
		return jsonInfo.toString();
	}

}
