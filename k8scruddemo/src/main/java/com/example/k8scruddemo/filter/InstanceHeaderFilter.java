package com.example.k8scruddemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class InstanceHeaderFilter implements Filter {
    private static final String TOKEN_URL = "http://169.254.169.254/latest/api/token";
    private static final String META_URL = "http://169.254.169.254/latest/meta-data/instance-id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletResponse res = (HttpServletResponse) response;
        String instanceId = "UNKNOWN";

        try {
            // 1️⃣ Fetch IMDSv2 Token
            HttpURLConnection tokenConn = (HttpURLConnection) new URL(TOKEN_URL).openConnection();
            tokenConn.setRequestMethod("PUT");
            tokenConn.setRequestProperty("X-aws-ec2-metadata-token-ttl-seconds", "21600");
            tokenConn.setDoOutput(true);

            BufferedReader tokenReader = new BufferedReader(
                    new InputStreamReader(tokenConn.getInputStream()));
            String token = tokenReader.readLine();
            tokenReader.close();

            // 2️⃣ Fetch Instance ID with Token
            HttpURLConnection metaConn = (HttpURLConnection) new URL(META_URL).openConnection();
            metaConn.setRequestMethod("GET");
            metaConn.setRequestProperty("X-aws-ec2-metadata-token", token);

            BufferedReader metaReader = new BufferedReader(
                    new InputStreamReader(metaConn.getInputStream()));
            instanceId = metaReader.readLine();
            metaReader.close();

        } catch (Exception ex) {
            ex.printStackTrace(); // fallback if needed
        }

        res.addHeader("X-Instance-Id", instanceId);
        chain.doFilter(request, response);
    }

}
