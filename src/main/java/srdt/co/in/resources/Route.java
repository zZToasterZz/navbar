package srdt.co.in.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import srdt.co.in.models.ResponceMessage;

@RestController
@RequestMapping("/route")
public class Route {

	@Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;
	
    @GetMapping("/test")
	public ResponceMessage test()
	{
		return new ResponceMessage("Route ="+serverAddress+":"+serverPort);
	}
}
