/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
 * Contact: api@factset.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package factset.analyticsapi.engines.auth;

import factset.analyticsapi.engines.Pair;
import factset.analyticsapi.engines.ApiException;

import com.migcomponents.migbase64.Base64;

import java.net.URI;
import java.util.Map;
import java.util.List;

import java.io.UnsupportedEncodingException;

@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class HttpBasicAuth implements Authentication {
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams, Map<String, String> cookieParams, String payload, String method, URI uri) throws ApiException {
    if (username == null && password == null) {
      return;
    }
    String str = (username == null ? "" : username) + ":" + (password == null ? "" : password);
    try {
      headerParams.put("Authorization", "Basic " + Base64.encodeToString(str.getBytes("UTF-8"), false));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }
}
