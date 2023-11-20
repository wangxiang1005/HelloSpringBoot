package com.paladin.product.server.rpc;

import com.alibaba.fastjson.JSON;
import com.paladin.grpc.server.DeductInventoryRequest;
import com.paladin.grpc.server.ProductServerResponse;
import com.paladin.grpc.server.ProductServiceGrpc;
import com.paladin.product.server.util.ProductUtil;
import com.paladin.product.server.vo.Product;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProductGrpcServer extends ProductServiceGrpc.ProductServiceImplBase {

    @Override
    public void deductProductInventory(DeductInventoryRequest request, StreamObserver<ProductServerResponse> responseObserver) {

        int productId = request.getProductId();
        Product product = ProductUtil.getProductById(productId);
        String jsonData = JSON.toJSONString(product);

        ProductServerResponse productServerResponse = ProductServerResponse.newBuilder()
                .setCode(200)
                .setMessage("")
                .setData(jsonData)
                .build();
        responseObserver.onNext(productServerResponse);
        responseObserver.onCompleted();
    }
}