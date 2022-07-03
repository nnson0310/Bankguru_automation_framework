package envfactory;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:cloud_credentials.properties"})
public interface CloudCredentials extends Config {

    @Config.Key("cloudUsername")
    String cloudUsername();

    @Config.Key("cloudPassword")
    String cloudPassword();
}
