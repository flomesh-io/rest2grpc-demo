package io.flomesh.rest2grpc;

import io.flomesh.rest2grpc.api.GreetingGrpc;
import io.flomesh.rest2grpc.api.HelloReply;
import io.flomesh.rest2grpc.api.HelloRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import io.grpc.StatusRuntimeException;

@Service
public class GrpcClientService {

    @GrpcClient("rest2grpc-server")
    private GreetingGrpc.GreetingBlockingStub greetingStub;

    public String sendMessage(final String name, final int latency) {
        try {
            final HelloReply response = this.greetingStub.sayHello(HelloRequest.newBuilder().setName(name).setLatency(latency).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }

}
