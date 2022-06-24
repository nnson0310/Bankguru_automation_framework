package serverconfig;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${server}.properties"})
public interface ServerConfig extends Config {

    @Key("appUrl")
    String url();
}
