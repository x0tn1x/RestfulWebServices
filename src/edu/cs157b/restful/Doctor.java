package edu.cs157b.restful;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Doctor {

    private int id;

    private String name;

    private String specialty;
    
    
    

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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


}
