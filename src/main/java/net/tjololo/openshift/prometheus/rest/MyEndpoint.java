package net.tjololo.openshift.prometheus.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by veg on 10.02.2017.
 */
@RestController
public class MyEndpoint {
    public static boolean ok = true;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/toggle", method = RequestMethod.GET)
    public void toggle() {
        MyEndpoint.ok = !MyEndpoint.ok;
    }
}
