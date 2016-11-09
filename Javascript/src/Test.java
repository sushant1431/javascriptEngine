import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Test {
	public static void main(String[] args) {
		Test t = new Test();
		String hex = "QmS9guZLEeaqHtGSzhCuanoax6PWg7ApjTDXegL3CHcg4s";
		t.downloadIPFS(hex);
	} 
	static {
	    final TrustManager[] trustAllCertificates = new TrustManager[] {
	        new X509TrustManager() {
	            @Override
	            public X509Certificate[] getAcceptedIssuers() {
	                return null; // Not relevant.
	            }
	            @Override
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing. Just allow them all.
	            }
	            @Override
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing. Just allow them all.
	            }
	        }
	    };

	    try {
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCertificates, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    } catch (GeneralSecurityException e) {
	        throw new ExceptionInInitializerError(e);
	    }
	}
	public File downloadIPFS(String hex){
		File file = null;
		 try {
			URL link = new URL("https://gateway.ipfs.io/ipfs/"+hex);
			ReadableByteChannel rbc = Channels.newChannel(link.openStream());
			FileOutputStream fos = new FileOutputStream("D:\\temp\\"+hex);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			file = new File("D:\\temp\\"+hex);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return file;
		 
	}
}
