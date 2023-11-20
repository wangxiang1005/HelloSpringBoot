package com.paladin.coupon.server.rpc;

import com.alibaba.fastjson.JSON;
import com.paladin.coupon.server.util.CouponUtil;
import com.paladin.coupon.server.vo.Coupon;
import com.paladin.grpc.server.CouponServerResponse;
import com.paladin.grpc.server.CouponServiceGrpc;
import com.paladin.grpc.server.DeductCouponRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CouponGrpcServer extends CouponServiceGrpc.CouponServiceImplBase {

    @Override
    public void deductProductInventory(DeductCouponRequest request, StreamObserver<CouponServerResponse> responseObserver) {
        int couponId = request.getCouponId();
        //查找优惠券详细信息
        Coupon coupon = CouponUtil.getCouponById(couponId);
        String jsonData = JSON.toJSONString(coupon);
        CouponServerResponse couponServerResponse = CouponServerResponse.newBuilder()
                .setCode(200)
                .setMessage("")
                .setData(jsonData)
                .build();
        responseObserver.onNext(couponServerResponse);
        responseObserver.onCompleted();
    }
}