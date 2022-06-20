package io.flomesh.rest2grpc;

import io.flomesh.rest2grpc.api.GreetingGrpc;
import io.flomesh.rest2grpc.api.HelloReply;
import io.flomesh.rest2grpc.api.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Michael (yidongnan@gmail.com)
 * @since 2016/11/8
 */

@GrpcService
public class GrpcServerService extends GreetingGrpc.GreetingImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello: " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
