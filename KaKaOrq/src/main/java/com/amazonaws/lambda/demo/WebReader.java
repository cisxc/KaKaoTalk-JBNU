package com.amazonaws.lambda.demo;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.net.URL;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.*;

public class WebReader {
	String html="";
	
	private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
 
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
 
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
  
	
	public String ReadUrl(String url)
	{
		try{
			  URL webUrl = new URL(url);
			  if(url.contains("https"))
				  disableSslVerification();		  
			  URLConnection urlConn = webUrl.openConnection();
			  urlConn.setConnectTimeout(4000);//연결시간재기
			  urlConn.setReadTimeout(4000);
			  urlConn.setRequestProperty("User-Agent", "Mozilla/5.0"); 		 
			  BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),Charset.forName("UTF-8")));
			   String inputLine;
			  
			   
			         while ((inputLine = in.readLine()) != null){
			          html+=inputLine+"\n";
			         }			         
			         in.close();
			        
			         //System.out.println(html);
			  }catch(Exception e){
				  return "err";

			  }
		return html;
		
	}
}
