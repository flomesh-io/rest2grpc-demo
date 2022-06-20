package io.flomesh.rest2grpc;

import io.flomesh.rest2grpc.api.GreetingGrpc;
import io.flomesh.rest2grpc.api.HelloReply;
import io.flomesh.rest2grpc.api.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServerService extends GreetingGrpc.GreetingImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        if (req.getLatency() > 0) {
            try {
                Thread.sleep(req.getLatency());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello: " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
