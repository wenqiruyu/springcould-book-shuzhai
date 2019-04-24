package com.book.shuzhai.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AliPayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092600598528";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDSucXyfabbad/TPK+9ZQuUoOdw8v0i6B9M7A/bHedjad7TjsdfN2iCwJAB1ZaKoTU27L8viTHfzevwLi3wSwO1/AuyaeeJENirnlz15OvuNQRsRKmeoKneuoEiRHNXM93etHywq6ce8pMVJlv134iGfD/PR5zOlvV65T57QeHT5754RGlzidq+DAV5vsno1wbbLWSl6kt89GNtbNlkROEmjp661Z4m0kYsb4JVqeAgImvOATRhXP6bDfGL4uJ3pyUTn+NWe/H7mTjGBNbOvFAOijMo5KrYQvzdS1dO6lEniBg5+qzHwsn6Bl9E96TJlV9p8RHGA1Hz8A6FETNPaXFNAgMBAAECggEBAM8qkC671US0q7WSXfvG0UHHhoGGgdLFfbA0C8qRdaBIvyYhomQ7puWUxn7nKt8TaaubyMHFEWFDowc3fzZTqUxKhH1cnKImC719LUsZSdGgEb8XKeKndRlhJf84aGR8u01tB4Dw4JYOSQdr9NCeeCwvDtSLRN/djWzK30hyFsAptG0sO42eDEquStTrOrwz+62LuQXvewPDCOUAqcoXKbMQO7rz/3je5HL/sGTx6ORG/lK1I1d8PdoSttT8XtcYpIyHuil7Lla6SV38Y8uo2M106WOrhRXCA+oXOCdoKOyYpzgSK6N7AXvjVYxlyWNFzaL2Reyab26PgIPsXck5LMECgYEA/vj83w9LrYxaBZrsD051MxB/q2MH5jeMFr6LZa8roewmrVIS8yjENmdcMWk2geHl/4fMTdbp2mJq+JIupFdFJynRigCawDA5hd++zwq9qYyA3F9oz/GePjZzcRQscFxlq9JCF9Atv6GF7X+PkS/b9Fd6uUIMVPOgwCixjxHCR4UCgYEA05MksyRy91pbFNg6K9o46ywpe13SRKU2J2GwZwF4PdgzvRN6fsIUlqXmfeAFXSdXoTc6u7Cyze2B0ttRxN0FfJ4Itsx2KY/mH49zKv4jM/beYFQf6vKxtPr0JgfBSS9h8wqrLVhACB36yurQWlIzOGK3J5/dKWMTEQf8LM4QGSkCgYALCJNIw8yukehDXdThRK/lqc0ciYiJ+dViFpTJra7hruptjaOsShNTl11x9e+Z50CgsiuxIp+0B5ZBc3H5Qp2HeFPM+QG5V1zl4izLoNdcQuxAnRBYD0idwJV2Sz0xD46GxO5/QU75f+/fuylnSv7lr/NrmOQXuQM3zYsBee9CUQKBgQCb2PCYsQmqoLkk4p3+/XEd8lrgD906SYRZgAEj7xY2YBLEfRunUak+yxe8sw7gboGDNJ6j8OXVANW3j2xtGe3XvMVoebW0qdX2e4F3MtqVhYyK4ozcUUeLq6u6c83x7eA12i77aN1J3WOGo280iQZHy5dkrcKIoP1Dd2FPU77R+QKBgFBDoovD0feepwDRcrmN9NCepiBMy/CevziZXRMH/G+oCqMQOg6QUcGCCSjbo+vJCPQzKnRKqLpHRN5My5a2Z22S31BMQ707aPYosib39KmfAbweThg2yNDaAIAoMRfAthRkY1yHkEYzyelHv9ZtGhSctdjSzZOJ8fAQdC82udY9";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxjhJhXd7bSCEcApGbKLuqjw/1boTLPk0BIF2tJF9TE27VXrYsyrxo1JM8oGriBFdDl6OEgez3DPfaIbaTzi1ywEjTpoDYOx20Gc1HwH88LA+Fg26BT1FwL7jMYfvJjT1z85lIbjkyQfY3PCL5rOtkQ0Oq7UHVTn/nfyu/k2lhETn+NDGW7Wf6Dvm+HF8bVsLfFpysRuBmI1WzbVqeJufCUOxwbjChzZVnitAVyhHQ0GKp3twQG3HOmOuPhJ3WQUhonosUyjI5/sSSEuSwrlxswYaWhN9DrwXuWi7ghpNXw3kNpexGB+C5JEbVQXhHEDARsGBptBOGZJU1pRsHU4w2QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://2t4316h649.qicp.vip/pay/notify";

    // 页面跳转同步通知页面路径
    public static String return_url = "http://localhost:8080/paySuccess";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\Users\\22341\\Desktop\\毕业设计\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


