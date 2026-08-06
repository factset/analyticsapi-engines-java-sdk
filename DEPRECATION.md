# Analytics SDK Java - Deprecation Notice

**Status**: ⚠️ **DEPRECATED** - Effective August 31, 2026

---

## Important Notice

The **FactSet Analytics SDK for Java** (`com.factset.analyticsapi:engines-sdk`) is **officially deprecated** as of **August 31, 2026**.

### Timeline

| Date | Status | Action |
|------|--------|--------|
| **Until Aug 31, 2026** | Active | Limited security fixes only |
| **Aug 31, 2026** | Final Release | Last day of support - FINAL DATE |
| **Sep 1, 2026+** | Removed | ALL versions removed from Maven Central - Enterprise SDK REQUIRED |

### What This Means

✅ **Existing Projects**: Continue to work (installed before Sep 1)  
❌ **New Projects**: Cannot install Analytics SDK from Sep 1, 2026 onwards  
❌ **New Installs**: IMPOSSIBLE after Aug 31, 2026 - Enterprise SDK ONLY  
⚠️ **Security Issues**: NO patches after Aug 31, 2026  
🔄 **Migration REQUIRED**: Must complete migration by Aug 31, 2026

---

## Migration Required

### Why Migrate?

- **Long-term Support**: Enterprise SDK is the actively maintained solution
- **New Features**: Enterprise SDK receives regular updates and features
- **Security**: Security patches provided for years to come
- **Support**: Dedicated support team for enterprise deployments

### How to Migrate

#### Step 1: Review Current Usage

```java
// Identify all code using the Analytics SDK
import com.factset.analyticsapi.engines.api.*;
import com.factset.analyticsapi.engines.model.*;
```

#### Step 2: Update Project File

```xml
<!-- OLD: Analytics SDK (Deprecated) -->
<dependency>
    <groupId>com.factset.analyticsapi</groupId>
    <artifactId>engines-sdk</artifactId>
    <version>6.3.1</version>
</dependency>

<!-- NEW: Enterprise SDK -->
<dependency>
    <groupId>com.factset.sdk</groupId>
    <artifactId>FactSetAnalyticsAPI</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Step 3: Update Imports

```java
// OLD: Analytics SDK
import com.factset.analyticsapi.engines.api.*;
import com.factset.analyticsapi.engines.model.*;

// NEW: Enterprise SDK
import com.factset.sdk.FactSetAnalyticsAPI.api.*;
import com.factset.sdk.FactSetAnalyticsAPI.model.*;
```

#### Step 4: Update API Calls

Refer to the [Migration Guide](./MIGRATION.md) for specific API changes.

### Installation Methods

**Maven:**
```xml
<dependency>
    <groupId>com.factset.sdk</groupId>
    <artifactId>FactSetAnalyticsAPI</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle:**
```gradle
implementation 'com.factset.sdk:FactSetAnalyticsAPI:1.0.0'
```

---

## Key Dates

### FINAL DATE: August 31, 2026
**LAST DAY** for Analytics SDK support and availability

### From September 1, 2026
- ✂️ **ALL versions REMOVED from Maven Central**
- 🚫 **NO new installations possible**
- 🚫 **NO package discovery on Maven Central**
- ⚠️ **Only Enterprise SDK available**
- 📦 **Existing installations continue to work (cached versions)**

### Migration Window
- **Start**: Now
- **Deadline**: August 31, 2026
- **After Deadline**: Enterprise SDK REQUIRED for any new projects or installations

---

## FAQ

### Q: Will my existing project break after Aug 31?
**A**: No, existing installations will continue to work. However, you won't be able to install new versions or restore on new machines without having cached versions.

### Q: What if I don't migrate by Aug 31?
**A**: CRITICAL - After Aug 31, you CANNOT:
- ❌ Install Analytics SDK from Maven Central (removed)
- ❌ Add package to new projects
- ❌ Restore on new build agents without cached version
- ❌ Get any security patches or support

**Only Option**: Migrate to Enterprise SDK immediately after Aug 31

### Q: Is the Enterprise SDK a replacement?
**A**: Yes, the Enterprise SDK is the recommended replacement. It provides:
- Same functionality
- Better performance
- Active maintenance
- Long-term support

### Q: How long do I have to migrate?
**A**: You have until **August 31, 2026** ONLY:
- ✅ Until Aug 31: Analytics SDK available from Maven Central
- ❌ From Sep 1: ALL versions REMOVED from Maven Central
- ⚠️ No grace period - complete removal on Sep 1

### Q: Do I need to change my code?
**A**: Minor changes may be required. See [Migration Guide](./MIGRATION.md).

### Q: What about support?
**A**: Limited support available until Aug 31, 2026. After that date, support team will focus on Enterprise SDK.

---

## Support & Resources

### Migration Assistance
- **Enterprise SDK GitHub**: https://github.com/factset/enterprise-sdk/tree/main/code/java
- **Migration Guide**: https://developer.factset.com
- **Email Support**: analytics.api.support@factset.com

### Repositories
- **Analytics SDK (This Repo)**: https://github.com/factset/analyticsapi-engines-java-sdk
- **Enterprise SDK**: https://github.com/factset/enterprise-sdk
- **API Documentation**: https://developer.factset.com/api-catalog

---

## Affected APIs

This deprecation affects the following FactSet Analytics APIs:

- **PA Engine API** - Portfolio Analysis
- **SPAR Engine API** - Style Performance Attribution
- **Vault API** - Portfolio Management

All functionality is available in the Enterprise SDK with improved performance and support.

---

## Questions?

If you have questions about the deprecation or migration:

1. Check the [Migration Guide](./MIGRATION.md)
2. Review [Deprecation Documentation](./DEPRECATION.md)
3. Contact: **analytics.api.support@factset.com**

---

**Last Updated**: August 6, 2026  
**Deprecation Effective Date**: August 31, 2026  
**Status**: In Effect
