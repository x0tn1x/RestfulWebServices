package edu.cs157b.restful;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class Patient {

	private int id;
    
    private String name;

    private int age;
    
    private String pcondition;
    
    private int d_id;
    
    
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@JsonProperty("pcondition")
	public String getCondition() {
		return pcondition;
	}

	public void setCondition(String pcondition) {
		this.pcondition = pcondition;
	}
	
	@JsonProperty("d_id")
	public Integer getDID() {
		return d_id;
	}

	public void setDID(int d_id) {
		this.d_id = d_id;
	}
}
