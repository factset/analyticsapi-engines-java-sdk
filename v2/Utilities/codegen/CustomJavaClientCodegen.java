import org.openapitools.codegen.*;
import org.openapitools.codegen.languages.*;
import org.openapitools.codegen.utils.ProcessUtils;

public class CustomJavaClientCodegen extends JavaClientCodegen {
    
    @Override
    public void processOpts() {
        super.processOpts();

        final String apiFolder = (sourceFolder + '/' + apiPackage).replace(".", "/");
        supportingFiles.add(new SupportingFile("utility_api.mustache", apiFolder, "UtilityApi.java"));

        supportingFiles.add(new SupportingFile("utility_api_doc.mustache", "docs", "UtilityApi.md"));
    }
}