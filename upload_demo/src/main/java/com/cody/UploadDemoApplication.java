package com.cody;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UploadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadDemoApplication.class, args);
	}

    /**
     * 解决上传文件大于10M出现连接重置问题，此异常内容GlobalException也捕获不到。
     *
     * @return
     */
	@Bean
	public TomcatServletWebServerFactory tomcatEmbedded()
    {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connecter -> {
            if ((connecter.getProtocolHandler() instanceof AbstractHttp11Protocol<?>))
            {
                ((AbstractHttp11Protocol) connecter.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });

        return tomcat;
    }
}
