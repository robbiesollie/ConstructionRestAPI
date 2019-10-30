package hellorestapi.hellorestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Robbie Sollie - HelloController.java - EGR327 - CBU - 2019-10-14
 */
@RestController
public class HelloController {

    @RequestMapping(value="/newgreeting", method= RequestMethod.POST)
    public Greeting newGreeting() {
        return new Greeting(1, "Hello");
    }

    @RequestMapping(value="/sameGreeting", method= RequestMethod.POST)
    public Greeting sameGreeting(@RequestBody Greeting greeting) {
        return greeting;
    }

    @RequestMapping("/greetingByName")
    public String greetingByName(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value="/getHighestGreeting", method = RequestMethod.POST)
    public Greeting getHighestGreeting(@RequestBody List<Greeting> list) {
        if (list.size() == 0) {
            return null;
        }
        Greeting highest = list.get(0);
        for (Greeting g : list) {
            if (g.getId() > highest.getId()) {
                highest = g;
            }
        }
        return highest;
    }

    @RequestMapping(value = "/updateGreeting", method=RequestMethod.PUT)
    public Greeting updateGreeting(@RequestBody String newMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String message = FileUtils.readFileToString(new File("message.txt"), StandardCharsets.UTF_8);

        Greeting greeting = mapper.readValue(message, Greeting.class);

        greeting.setContent(newMessage);

        mapper.writeValue(new File("message.txt"), greeting);

        return greeting;
    }

    @RequestMapping(value = "/deleteGreeting", method = RequestMethod.DELETE)
    public void deleteGreeting(@RequestBody int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String message = FileUtils.readFileToString(new File("message.txt"), StandardCharsets.UTF_8.name());

        Greeting greeting = mapper.readValue(message, Greeting.class);
        if (greeting.getId() == id) {
            FileUtils.writeStringToFile(new File("message.txt"), "", StandardCharsets.UTF_8.name());
        }
    }

    @RequestMapping(value = "/createGreeting", method = RequestMethod.POST)
    public Greeting createGreeting(@RequestBody String name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Greeting greeting = new Greeting(1, name);

        mapper.writeValue(new File("message.txt"), greeting);

        return greeting;
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String message = FileUtils.readFileToString(new File("message.txt"), StandardCharsets.UTF_8.name());

        Greeting greeting = mapper.readValue(message, Greeting.class);

        return greeting;
    }

}
