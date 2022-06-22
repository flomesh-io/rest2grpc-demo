package io.flomesh.rest2grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcClientController {

    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping("/client-server")
    public String printMessage(@RequestParam(defaultValue = "Flomesh") String name, @RequestParam(defaultValue = "0") int latency) {
        return grpcClientService.sendMessage(name, latency);
    }

    @RequestMapping("/client-only")
    public String returnMessage(@RequestParam(defaultValue = "Flomesh") String name, @RequestParam(defaultValue = "0") int latency) {
        if (latency > 0) {
            try {
                Thread.sleep(latency);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return "Hello Client: " + name;
    }
}
