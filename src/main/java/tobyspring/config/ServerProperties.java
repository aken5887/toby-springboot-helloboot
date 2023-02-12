package tobyspring.config;

@MyConfigurationProperties(prefix="server")
public class ServerProperties {
  String contextPath;
  int serverPort;

  public String getContextPath() {
    return contextPath;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }

  public int getServerPort() {
    return serverPort;
  }

  public void setServerPort(int serverPort) {
    this.serverPort = serverPort;
  }
}
