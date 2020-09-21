package gasi.ewf.request;

import javax.validation.constraints.NotBlank;

public class ClientRequest {

	private String id;
	
	@NotBlank(message = "Please provide a code")
	private String code;

	@NotBlank(message = "Please provide a name")
    private String name;

	@NotBlank(message = "Please provide a url")
    private String url;

	@NotBlank(message = "Please provide a image")
    private String image;
	
	@NotBlank(message = "Please provide a secret")
    private String secret;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
    
}
