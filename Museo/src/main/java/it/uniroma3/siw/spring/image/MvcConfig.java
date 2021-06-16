package it.uniroma3.siw.spring.image;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get("./opera-photos");
		String uploadPath = uploadDir.toFile().getAbsolutePath();

//		if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

		registry.addResourceHandler("/opera-photos/**").addResourceLocations("file:/"+ uploadPath + "/");
	}


//	private void exposeDirectory(ResourceHandlerRegistry registry) {
//		Path uploadDir = Paths.get("./opera-photos");
//		String uploadPath = uploadDir.toFile().getAbsolutePath();
//
//      if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
//
//		registry.addResourceHandler("/opera-photos/**").addResourceLocations("file:/"+ uploadPath + "/");
//	}
}