# Migration Guide: Analytics SDK → Enterprise SDK (Java)

**From**: com.factset.analyticsapi:engines-sdk (Deprecated)  
**To**: com.factset.sdk:FactSetAnalyticsAPI (Recommended)  
**⚠️ CRITICAL**: All Analytics SDK versions removed from Maven Central on Sep 1, 2026

---

## Quick Start

### Step 1: Update Project File

**In your pom.xml:**

```xml
<!-- REMOVE -->
<dependency>
    <groupId>com.factset.analyticsapi</groupId>
    <artifactId>engines-sdk</artifactId>
    <version>6.3.1</version>
</dependency>

<!-- ADD -->
<dependency>
    <groupId>com.factset.sdk</groupId>
    <artifactId>FactSetAnalyticsAPI</artifactId>
    <version>1.0.0</version>
</dependency>
```

Or via CLI:

```bash
# Remove old
mvn dependency:tree | grep analyticsapi-engines-sdk

# Remove in pom.xml, then add new dependency
```

### Step 2: Update Imports

Find and replace in your code:

```java
// OLD
import com.factset.analyticsapi.engines.api.*;
import com.factset.analyticsapi.engines.model.*;

// NEW
import com.factset.sdk.FactSetAnalyticsAPI.api.*;
import com.factset.sdk.FactSetAnalyticsAPI.model.*;
```

### Step 3: Update API Calls

Refer to specific API sections below for changes.

---

## Namespace Mapping

| Analytics SDK | Enterprise SDK |
|---|---|
| `com.factset.analyticsapi.engines` | `com.factset.sdk.FactSetAnalyticsAPI` |
| `com.factset.analyticsapi.engines.api` | `com.factset.sdk.FactSetAnalyticsAPI.api` |
| `com.factset.analyticsapi.engines.model` | `com.factset.sdk.FactSetAnalyticsAPI.model` |
| `com.factset.analyticsapi.engines.client` | `com.factset.sdk.FactSetAnalyticsAPI.client` |

---

## API-Specific Migration

### PA Engine API

**Before (Analytics SDK):**
```java
import com.factset.analyticsapi.engines.api.PAEngineApi;
import com.factset.analyticsapi.engines.model.PACalculationParameters;

PAEngineApi apiInstance = new PAEngineApi();
PACalculationParameters paCalculationParameters = new PACalculationParameters();
String result = apiInstance.postAndCalculate(paCalculationParameters);
```

**After (Enterprise SDK):**
```java
import com.factset.sdk.FactSetAnalyticsAPI.api.PAEngineApi;
import com.factset.sdk.FactSetAnalyticsAPI.model.PACalculationParameters;

PAEngineApi apiInstance = new PAEngineApi();
PACalculationParameters paCalculationParameters = new PACalculationParameters();
String result = apiInstance.postAndCalculate(paCalculationParameters);
```

### SPAR Engine API

**Before (Analytics SDK):**
```java
import com.factset.analyticsapi.engines.api.SPAREngineApi;
import com.factset.analyticsapi.engines.model.SPARCalculationParameters;

SPAREngineApi apiInstance = new SPAREngineApi();
SPARCalculationParameters sparCalculationParameters = new SPARCalculationParameters();
String result = apiInstance.postAndCalculate(sparCalculationParameters);
```

**After (Enterprise SDK):**
```java
import com.factset.sdk.FactSetAnalyticsAPI.api.SPAREngineApi;
import com.factset.sdk.FactSetAnalyticsAPI.model.SPARCalculationParameters;

SPAREngineApi apiInstance = new SPAREngineApi();
SPARCalculationParameters sparCalculationParameters = new SPARCalculationParameters();
String result = apiInstance.postAndCalculate(sparCalculationParameters);
```

### Vault API

**Before (Analytics SDK):**
```java
import com.factset.analyticsapi.engines.api.VaultApi;
import com.factset.analyticsapi.engines.model.VaultCalculationParameters;

VaultApi apiInstance = new VaultApi();
VaultCalculationParameters vaultCalculationParameters = new VaultCalculationParameters();
String result = apiInstance.postAndCalculate(vaultCalculationParameters);
```

**After (Enterprise SDK):**
```java
import com.factset.sdk.FactSetAnalyticsAPI.api.VaultApi;
import com.factset.sdk.FactSetAnalyticsAPI.model.VaultCalculationParameters;

VaultApi apiInstance = new VaultApi();
VaultCalculationParameters vaultCalculationParameters = new VaultCalculationParameters();
String result = apiInstance.postAndCalculate(vaultCalculationParameters);
```

---

## Configuration Changes

### Authentication

Both SDKs use similar authentication patterns:

```java
// Analytics SDK
ApiClient defaultClient = Configuration.getDefaultApiClient();
HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
basicAuth.setUsername("username");
basicAuth.setPassword("password");

// Enterprise SDK (same pattern)
ApiClient defaultClient = Configuration.getDefaultApiClient();
HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
basicAuth.setUsername("username");
basicAuth.setPassword("password");
```

---

## Building and Testing

### Build the Project

```bash
mvn clean package
```

The compiler will show warnings for any remaining old SDK references.

### Run Tests

```bash
mvn test
```

Verify all tests pass with the new SDK.

### Address Compiler Warnings

If you see warnings like:
```
[WARNING] com.factset.analyticsapi.engines.api.PAEngineApi is deprecated
```

Make sure you've:
1. Removed all old package references
2. Updated all import statements
3. No old SDK code remains

---

## Troubleshooting

### Problem: "Cannot find symbol 'PAEngineApi'"

**Solution**: Verify package is installed:
```bash
mvn dependency:tree | grep FactSetAnalyticsAPI
```

Should show: `com.factset.sdk:FactSetAnalyticsAPI`

### Problem: "Package com.factset.analyticsapi.engines does not exist"

**Solution**: Update import statements:
```java
// Change from:
import com.factset.analyticsapi.engines.api.*;

// To:
import com.factset.sdk.FactSetAnalyticsAPI.api.*;
```

### Problem: "Cannot resolve method"

**Solution**: Clear Maven cache and rebuild:
```bash
mvn clean install -U
```

### Problem: "Artifact not found"

**Solution**: Verify Maven Central has the new package:
```bash
# Check if package is available
mvn dependency:resolve -DincludeClassifiers=sources
```

---

## Migration Checklist

- [ ] Backup current project
- [ ] Update pom.xml: Remove old dependency
- [ ] Update pom.xml: Add new dependency
- [ ] Run `mvn clean install`
- [ ] Update all import statements
- [ ] Update API calls to use new namespaces
- [ ] Build project (`mvn clean package`)
- [ ] Fix any compiler errors
- [ ] Run tests (`mvn test`)
- [ ] Test application functionality
- [ ] Verify authentication works
- [ ] Check error handling
- [ ] Performance test (optional)
- [ ] Document changes (optional)
- [ ] Deploy to staging environment
- [ ] Verify in staging
- [ ] Deploy to production

---

## Critical Timeline - NO GRACE PERIOD

| Date | Action | Impact |
|------|--------|--------|
| **Aug 31, 2026** | ⏰ FINAL DAY | Last day to use Analytics SDK |
| **Sep 1, 2026** | 🚫 COMPLETE REMOVAL | ALL versions deleted from Maven Central |
| **Sep 1+** | 📌 ENTERPRISE SDK ONLY | Only option for new installations |

**IMPORTANT**: There is NO grace period. Migration MUST be complete by Aug 31, 2026.

From Sep 1, 2026:
- ❌ Cannot download Analytics SDK
- ❌ Cannot restore package  
- ❌ Cannot add to new projects
- ✅ Enterprise SDK is the ONLY option

---

## ⚠️ MANDATORY REQUIREMENT

**Migration MUST be completed by August 31, 2026**

After Aug 31:
- ❌ Analytics SDK becomes completely unavailable
- ❌ No new installations possible
- ❌ No support or patches
- ✅ Enterprise SDK is the ONLY available option

---

## Getting Help - URGENT MIGRATION SUPPORT

### Critical Support
- **Email**: analytics.api.support@factset.com (Priority: MIGRATION)
- **Subject**: [URGENT] Analytics SDK Migration Required by Aug 31, 2026

### Documentation
- [Enterprise SDK GitHub](https://github.com/factset/enterprise-sdk/tree/main/code/java)
- [FactSet Developer Portal](https://developer.factset.com)
- [API Reference](https://developer.factset.com/api-catalog)

### Support
- **GitHub Issues**: https://github.com/factset/enterprise-sdk/issues

### Examples
- [Enterprise SDK Examples](https://github.com/factset/enterprise-sdk/tree/main/code/java/examples)
- [API Documentation](https://developer.factset.com)

### ⚠️ ACTION REQUIRED
**Do NOT wait** - Start migration immediately. Analytics SDK will be completely removed from Maven Central on Sep 1, 2026.

---

## What's Different?

### Improvements in Enterprise SDK
✅ Better performance  
✅ Smaller package size  
✅ More frequent updates  
✅ Better error messages  
✅ Modern Java support (Java 8+)  
✅ Comprehensive examples  
✅ Active support team  

### Key Changes
- Package name changed from `com.factset.analyticsapi` to `com.factset.sdk.FactSetAnalyticsAPI`
- Namespace changed from `com.factset.analyticsapi.engines` to `com.factset.sdk.FactSetAnalyticsAPI`
- Same API classes and methods (backward compatible in functionality)
- Better error handling and validation

---

## Success Criteria

After migration, you should be able to:
✓ Build successfully with no warnings  
✓ All tests pass  
✓ Application runs without errors  
✓ API calls return expected results  
✓ Error handling works correctly  
✓ Performance is equal or better  

---

**Migration Guide Version**: 1.0  
**Last Updated**: August 6, 2026  
**Status**: Ready for Use
