package org.example;


import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(
                "localhost:6000"
        ).usePlaintext().build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder().setName("Alice")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);
        System.out.println(response);

        while (response.hasNext()) {
            System.out.println(response.next());
        }

        channel.shutdownNow();
    }
}
