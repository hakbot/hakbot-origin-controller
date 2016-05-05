package net.continuumsecurity;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import net.continuumsecurity.v5.ReportClientV5;
import net.continuumsecurity.v5.ScanClientV5;
import net.continuumsecurity.v6.ReportClientV6;
import net.continuumsecurity.v6.ScanClientV6;

import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;

/**
 * Created by stephen on 23/02/2014.
 */
public class ClientFactory {
	public static ClientBuilder createInsecureSSLClient(final boolean acceptAllHostNames) {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
		} };
		HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if(acceptAllHostNames)
					return true;
				if(hostname.equals("localhost")){
					return true;
				}
				return false;
			}
		});
		// Install the all-trusting trust manager
		SSLContext sc = null;
		try{
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}catch(Exception e){
			e.printStackTrace();
		}
		return ClientBuilder.newBuilder().sslContext(sc);
	}

	public static Client createV6Client(final boolean acceptAllHostNames) {
		return createInsecureSSLClient(acceptAllHostNames).register(JacksonFeatures.class).build();
	}

	public static Client createV5Client(final boolean acceptAllHostNames) {
		return createInsecureSSLClient(acceptAllHostNames).build();
	}

	public static ScanClient createScanClient(String nessusUrl, int version, boolean acceptAllHostNames) {
		switch(version){
			case 5:
				return new ScanClientV5(nessusUrl, acceptAllHostNames);
			case 6:
				return new ScanClientV6(nessusUrl, acceptAllHostNames);
		}
		throw new RuntimeException("Only Nessus version 5 and 6 are supported.");
	}

	public static ReportClient createReportClient(String nessusUrl, int version, boolean acceptAllHostNames) {
		switch(version){
			case 5:
				return new ReportClientV5(nessusUrl, acceptAllHostNames);
			case 6:
				return new ReportClientV6(nessusUrl, acceptAllHostNames);
		}
		throw new RuntimeException("Only Nessus version 5 and 6 are supported.");
	}
}