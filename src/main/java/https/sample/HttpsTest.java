package https.sample;


import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.config.SocketConfig.Builder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpsTest {
  /**
   * Create HttpClient with SSL
   */
  public static CloseableHttpClient createHttpsClient() {

    Registry<ConnectionSocketFactory> sslSocketFactoryRegistry = createAcceptAllSSLSocketFactoryRegistry();
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(sslSocketFactoryRegistry);
    cm.setMaxTotal(1);
    cm.setDefaultMaxPerRoute(1);
    Builder scBuilder = SocketConfig.copy(SocketConfig.DEFAULT);
    scBuilder.setSoTimeout(5000);
    cm.setDefaultSocketConfig(scBuilder.build());
    
//    RequestConfig defaultReqConfig = 
//        RequestConfig.copy(RequestConfig.DEFAULT)
//        .setSocketTimeout(20000)            // timeout for waiting for data or, put differently, a maximum period inactivity between two consecutive data packets). (default/0: infinite)
//        .setConnectTimeout(15000)           // timeout in milliseconds until a connection is established. (default/0: infinite)
//        .setConnectionRequestTimeout(0)     // timeout in milliseconds used when requesting a connection from the connection manager. (default/0: infinite)
//        .build();
//
//    CloseableHttpClient httpClient = HttpClientBuilder
//            .create()
//            .setDefaultRequestConfig(defaultReqConfig)
//            .setConnectionManager(cm)
//            .build();
//    
//    return httpClient;

    return HttpClients.custom().setConnectionManager(cm).build();
  }
  
  /**
   * Create a registry in which the HTTPS scheme is associated with a custom SSL
   * connections socket factory that accepts all certificates and does not
   * verify host names.
   * 
   * @return SSL socket factory registry instance.
   */
  private static Registry<ConnectionSocketFactory> createAcceptAllSSLSocketFactoryRegistry() {
    SSLConnectionSocketFactory sslConnSockFactory = createAcceptAllSSLSocketFactory();

    // Create the registry
    return RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslConnSockFactory).build();
  }

  /**
   * Create a custom SSL connections socket factory that accepts all
   * certificates and host names.
   * 
   * @return SSL socket factory instance.
   */
  private static SSLConnectionSocketFactory createAcceptAllSSLSocketFactory() {
    SSLConnectionSocketFactory sslConnSockFactory = null;

    try {
      // Create a trust strategy that accepts all certificates
      SSLContext sslContext = createAcceptsAllCertsSSLContext();

      // Create a host name verifier that accepts all host names
      X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

      // Create the SSL connections socket factory
      sslConnSockFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1","TLSv1.1","TLSv1.2" }, null, hostnameVerifier);
    } catch (Exception e) {
      // Do nothing
    }

    return sslConnSockFactory;
  }
  
  /**
   * Create a SSL context that can be used to accept all certificates.
   * 
   * @return SSL context that can be used to accept all certificates.
   * @throws KeyManagementException
   *           When exception occurs while managing key(s).
   * @throws NoSuchAlgorithmException
   *           When cryptographic algorithm not available.
   * @throws KeyStoreException
   *           When exception occurs while managing key store(s).
   */
  private static SSLContext createAcceptsAllCertsSSLContext()
      throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    return (new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
      public boolean isTrusted(X509Certificate[] certificate, String authType) throws CertificateException {
        return true;
      }
    }).useTLS().build());
  }
  
  
  public static void main(String[] args) {
    try{
    CloseableHttpClient httpClient = createHttpsClient();
    HttpGet getMethod = new HttpGet("https://tongdagufen.cn/onemap");
   

    CloseableHttpResponse rtiResponse = httpClient.execute(getMethod);
    HttpEntity entity = rtiResponse.getEntity();
    String respJsonString;
    if (entity != null) {
        respJsonString = EntityUtils.toString(entity);
        System.out.println(respJsonString);
    } 
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}
