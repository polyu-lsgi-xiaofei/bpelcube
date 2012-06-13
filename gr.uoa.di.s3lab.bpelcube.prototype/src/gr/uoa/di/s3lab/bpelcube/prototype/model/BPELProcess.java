package gr.uoa.di.s3lab.bpelcube.prototype.model;

import java.io.Serializable;

public class BPELProcess implements Serializable {

	private static final long serialVersionUID = 201206131918L;
	
	private String id;
	
	private String name;
	
	public BPELProcess() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
