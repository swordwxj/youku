package com.example.protal.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016082600311350";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCetQxU64Ytj8vdkn7wjpDqJLJJt1B0eJP1SI3qTPvzMKekpIpSKb/N76svPtmSTYhC46/B5/s06+ZpVyRCpFHUmb1H/alhcAKeQ7ezp1KIRI54FHXGUrMqxpeQ+chs8WLC0vL+DGYpKjADC59z/RM8z8LUGnr44ii94eg8xAsg7GBd02w10lOE41S+mJUl+mhfaxSvxtnileN9tcGYuAEEhLFD3t4dWhFzTLgkVtZHj+zVhLzX2mH7+LrhWk7dyFit3yEfthV64nCJYNxMV7ChuPd5NUak2NKUKW7P6USbY2Ow8IBm7SbvIHuXyISGlWlAxosiRdNaBQfDS5BLXEcvAgMBAAECggEBAJsb0QCR62JOHm3fxkZu33ym/sSC75PVYK2iCmkKclc8nyuPUEx61yV67nBPWa7eLaVKB7VYdzBCub8MVrj3Ba7lzpM0e71qDnUFM93WUxgxAJilTnHU05yj2O/s35Bx3hcQukHGdnYW8DOZwW5H0exmFUDc3ICv8CecSTBSXZn/QQvhqlyPteASWPpkFCzNR7pgHJPg/NYXyC4sSVBnOem0sK5EMdF5NNeTvRegzACJGOUVCrunwy556lidvoiQ03gaUZPR49CDPO9OkxJfLuWpoDraJQdpnt4zuZaZjA+TwHWBWJ32OUZEjBxo3+HHhkf390mgGvkQ7W2suMpk3QkCgYEAz1VhFVMUfrCUQNa5X7UqALgZ19Une96IoIvYvAO3SCi9Qh7RRcxpt0WFt7ZJ0yc3DwkWC0J7Q+BonABLD96ag8iD3jxEQzOTv652OC7dOj/YM2IROvIBca/mKgpTSN2aPwp7uoer6vGddv+9XMhU3dn3xlkBriwk6VE3+Uc/e90CgYEAw/W6CYvi7psMtNm9K2vP72YXBz2iJGW9TuEjiidVAXPzjKlQ8u/xrpOQ38dHuRBlPB1cM39GcifeaC2427sMQhvzKFM3uaP8PiT1o4gl+gexF6dld05+o9vnLs/gi5kkxyrtZ4GM1UlBkpS451PwcKxEcSBiGVYA/qgSqSlAlHsCgYEAneVRuCG7O/hSD0b3ibG4OS/7TmmgVi9VMZ8PAbB6xlNwPoilq1hPMimZ+hyzAEGfJq4uwYhxw3GFeTin8X//gFkWRAr1mZAP9yxwD71w8Ulp50fKA3AGNYRV7rJKVvWc4GGldJqaDKTQ3K1Kkvxl1UaH8eAa7X/k5GLcE9V0vWkCgYEArP/JKBhpVgFobFk2Wpkpz84vUmMlPEDdqj+04ojq0QeO4oWwQ2l10ovQgN8sAMPqu/GnrocUqD9uaEbFRqeRoOOP/e/J6IhPboWunprZ/CzCOYG9YGDaCMthA8DeGiAvie3p/VEwBjRQQZouy3sdcDuFiOHv+TImWqwDHXif3PUCgYAQURH7CZZVbh8/RKXtfDxDEoEOP4+yC0JENDk5292ub9E1Yw/AbhhBjaeqWVlCPyWoPh7bV/bhmeL5nMtTi/qLiKazIcr3xiPIFqM/V9usTEtcI99CFtnPXftxyLfXPRXwhTRZZAMBdfa2ACdoKjqVdEUiqD+p6nH/L66GjkXs9g==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx6SVtmGlUegQAP83aoi4huAPBgaz4vjFueikewvVKRbQIYCJJAblOi8sFmT+gUxLzUl+ebNLjsbVSE16I8CMQWE7KoLQwSIwq68sFoNtk0DWVbGwKZXDEC7YVRlz8IdMZRp9UuJmVmBx/MxA0vY6UH2uh3dsAEWNPMNWV1ebr8+jWEpBmjYJ9UxijcHJo1vtzZ75UlbvKdovdDROSeJ4azPz6RXXkXLJ7qIPxPSr0oPzcbCwmoUcxbwRAPLRZR5eRYCrrmp/7qgsJnTrhLE1FAXcOHBxgEg/PRaiA7gQdWq2qk1ta/8YN2iTNj4mO1C8sA9AmPvs888vu5KuF2qZHwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8082/back";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8082/back";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

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

