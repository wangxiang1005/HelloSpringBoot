Order接口测试1：<post>
http://localhost:8082/order/confirm
{
"totalAmount":12899,
"payType":"PC",
"productId":1,
"couponRecordId":2
}

Order接口测试2：<post>
http://localhost:8082/order/search

mvn install:install-file 
-Dfile=HelloSpringBoot\hello-springboot-grpc\hello-springboot-grpc-common\target\hello-springboot-grpc-common-1.0-SNAPSHOT.jar 
-DgroupId=com.paladin 
-DartifactId=hello-springboot-grpc-common 
-Dversion=1.0-SNAPSHOT 
-Dpackaging=jar