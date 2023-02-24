package com.example.hotsearch.http;

import com.example.hotsearch.util.ServiceURI;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.ssl.SSLContexts;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.concurrent.*;
/**
 * @author qiwendong
 */

@Slf4j
@Data
public abstract class HttpClient {
        private String secret;

        private String appId;

        protected String getHmacSign(String content) {
            byte[] result = null;
            try {
                SecretKeySpec signinKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
                Mac mac = Mac.getInstance("HmacSHA1");
                mac.init(signinKey);
                byte[] rawHmac = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
                result = Base64.getEncoder().encode(rawHmac);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (null != result) {
                return new String(result);
            } else {
                return null;
            }
        }

        private static final Client client;

        static {
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
            ClientConfig httpConfig = new ClientConfig();
            httpConfig.property(ClientProperties.FOLLOW_REDIRECTS, true);
            httpConfig.property(ClientProperties.ASYNC_THREADPOOL_SIZE, 8);
            httpConfig.register(MultiPartFeature.class);
            ClientBuilder clientBuilder = ClientBuilder.newBuilder()
                    .withConfig(httpConfig)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .register(JacksonFeature.class);
            client = clientBuilder.build();
        }

        protected final WebTarget webTarget;

        public HttpClient(String appId, String secret, String baseUrl) {
            this.appId = appId;
            this.secret = secret;
            ServiceURI serviceUri = ServiceURI.create(baseUrl);
            webTarget = client.target(String.format("%s://%s"
                    , serviceUri.getServiceScheme()
                    , serviceUri.getServiceHosts()[ThreadLocalRandom.current()
                            .nextInt(serviceUri.getServiceHosts().length)]));
        }

    public HttpClient(String baseUrl) {
        ServiceURI serviceUri = ServiceURI.create(baseUrl);
        webTarget = client.target(String.format("%s://%s"
                , serviceUri.getServiceScheme()
                , serviceUri.getServiceHosts()[ThreadLocalRandom.current()
                        .nextInt(serviceUri.getServiceHosts().length)]));
    }

        private static class NoHostnameVerifier implements HostnameVerifier {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        }


        private static class SslContextUtils {
            public static SSLContext getSslContext() {
                SSLContext ctx = SSLContexts.createDefault();
                X509TrustManager tm = new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] xcs, String str) {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] xcs, String str) {

                    }
                };
                try {
                    ctx.init(null, new TrustManager[]{tm}, new SecureRandom());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return ctx;
            }
        }

        private CompletableFuture<Invocation.Builder> requestAsync(final WebTarget target) {
            CompletableFuture<Invocation.Builder> builderFuture = new CompletableFuture<>();
            try {
                try {
                    SSLContext ssl = SslContextUtils.getSslContext();
                    Invocation.Builder builder = ClientBuilder.newBuilder()
                            .register(MultiPartFeature.class)
                            .register(HttpClientRequestFilter.class)
                            .sslContext(ssl)
                            .hostnameVerifier(new NoHostnameVerifier())
                            .executorService(ForkJoinPool.commonPool())
                            .build().target(target.getUri()).request();

                    builderFuture.complete(builder);
                } catch (Throwable t) {
                    builderFuture.completeExceptionally(new Exception(t));
                }
            } catch (Throwable t) {
                builderFuture.completeExceptionally(new Exception(t));
            }
            return builderFuture;
        }


        private Invocation.Builder request(final WebTarget target) throws Exception {
            try {
                return requestAsync(target).get();
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

        protected CompletableFuture<Response> asyncPostRequest(final WebTarget target, String data) {
            final CompletableFuture<Response> future = new CompletableFuture<>();
            try {
                Invocation.Builder builder = request(target);
                builder.async().post(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE), new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response response) {
                        future.complete(response);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        log.warn("[{}] Failed to perform http post request: {}", target.getUri(), throwable.getMessage());
                        future.completeExceptionally(new Exception(throwable.getCause()));
                    }
                });
            } catch (Exception cae) {
                future.completeExceptionally(cae);
            }
            return future;
        }
    public CompletableFuture<Response> asyncGetRequest(final WebTarget target) {
        final CompletableFuture<Response> future = new CompletableFuture<>();
        try {
            Invocation.Builder builder = request(target);
            builder.async().get(new InvocationCallback<Response>() {
                @Override
                public void completed(Response response) {
                    future.complete(response);
                }

                @Override
                public void failed(Throwable throwable) {
                    log.warn("[{}] Failed to perform http post request: {}", target.getUri(), throwable.getMessage());
                    future.completeExceptionally(new Exception(throwable.getCause()));
                }
            });
        } catch (Exception cae) {
            future.completeExceptionally(cae);
        }
        return future;
    }
}
