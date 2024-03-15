package kimlamdo.my_project_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.Category;
import kimlamdo.my_project_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {
    private String url = "http://localhost:3000";

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
        // config.exposeIdsFor(Category.class);

        // CORS configuration
//        cors.addMapping("/**")
//                .allowedOrigins(url)
//                .allowedMethods("GET", "POST", "PUT", "DELETE");

//        HttpMethod[] disabledMethods = {
//                HttpMethod.POST,
//                HttpMethod.PUT,
//                HttpMethod.DELETE,
//                HttpMethod.PATCH,
//        };
//        disableHttpMethods(Category.class, config, disabledMethods);
//
//        // Chặn các method DELETE
//        HttpMethod[] deleteMethod = {
//                HttpMethod.DELETE
//        };
//        disableHttpMethods(User.class, config,deleteMethod );

        // Allow ID while return JSON
//        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
//        disableHttpMethods(Brand.class, config, disabledMethods);
    }

    private void disableHttpMethods(Class c, RepositoryRestConfiguration config, HttpMethod[] methods) {
        config.getExposureConfiguration().forDomainType(c).withItemExposure((metdata, httpMethods) -> httpMethods.disable(methods)).withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methods));
    }
}