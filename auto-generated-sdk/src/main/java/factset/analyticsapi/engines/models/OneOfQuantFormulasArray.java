/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
 * Contact: analytics.api.support@factset.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package factset.analyticsapi.engines.models;

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import factset.analyticsapi.engines.models.QuantAllUniversalScreenParameters;
import factset.analyticsapi.engines.models.QuantFqlExpression;
import factset.analyticsapi.engines.models.QuantScreeningExpression;
import factset.analyticsapi.engines.models.QuantUniversalScreenParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;

import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import factset.analyticsapi.engines.JSON;

@javax.annotation.Generated(value = "CustomJavaClientCodegen")
@JsonDeserialize(using = OneOfQuantFormulasArray.OneOfQuantFormulasArrayDeserializer.class)
@JsonSerialize(using = OneOfQuantFormulasArray.OneOfQuantFormulasArraySerializer.class)
public class OneOfQuantFormulasArray extends AbstractOpenApiSchema, Serializable {
    private static final Logger log = Logger.getLogger(OneOfQuantFormulasArray.class.getName());

    public static class OneOfQuantFormulasArraySerializer extends StdSerializer<OneOfQuantFormulasArray> {
        public OneOfQuantFormulasArraySerializer(Class<OneOfQuantFormulasArray> t) {
            super(t);
        }

        public OneOfQuantFormulasArraySerializer() {
            this(null);
        }

        @Override
        public void serialize(OneOfQuantFormulasArray value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeObject(value.getActualInstance());
        }
    }

    public static class OneOfQuantFormulasArrayDeserializer extends StdDeserializer<OneOfQuantFormulasArray> {
        public OneOfQuantFormulasArrayDeserializer() {
            this(OneOfQuantFormulasArray.class);
        }

        public OneOfQuantFormulasArrayDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public OneOfQuantFormulasArray deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            Object deserialized = null;
            OneOfQuantFormulasArray newOneOfQuantFormulasArray = new OneOfQuantFormulasArray();
            Map<String,Object> result2 = tree.traverse(jp.getCodec()).readValueAs(new TypeReference<Map<String, Object>>() {});
            String discriminatorValue = (String)result2.get("source");
            switch (discriminatorValue) {
                case "AllUniversalScreenParameters":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantAllUniversalScreenParameters.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "FqlExpression":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantFqlExpression.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "QuantAllUniversalScreenParameters":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantAllUniversalScreenParameters.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "QuantFqlExpression":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantFqlExpression.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "QuantScreeningExpression":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantScreeningExpression.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "QuantUniversalScreenParameter":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantUniversalScreenParameter.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "ScreeningExpression":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantScreeningExpression.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                case "UniversalScreenParameter":
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantUniversalScreenParameter.class);
                    newOneOfQuantFormulasArray.setActualInstance(deserialized);
                    return newOneOfQuantFormulasArray;
                default:
                    log.log(Level.WARNING, String.format("Failed to lookup discriminator value `%s` for OneOfQuantFormulasArray. Possible values: AllUniversalScreenParameters FqlExpression QuantAllUniversalScreenParameters QuantFqlExpression QuantScreeningExpression QuantUniversalScreenParameter ScreeningExpression UniversalScreenParameter", discriminatorValue));
            }

            boolean typeCoercion = ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS);
            int match = 0;
            JsonToken token = tree.traverse(jp.getCodec()).nextToken();
            // deserialize QuantAllUniversalScreenParameters
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantAllUniversalScreenParameters.class.equals(Integer.class) || QuantAllUniversalScreenParameters.class.equals(Long.class) || QuantAllUniversalScreenParameters.class.equals(Float.class) || QuantAllUniversalScreenParameters.class.equals(Double.class) || QuantAllUniversalScreenParameters.class.equals(Boolean.class) || QuantAllUniversalScreenParameters.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantAllUniversalScreenParameters.class.equals(Integer.class) || QuantAllUniversalScreenParameters.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantAllUniversalScreenParameters.class.equals(Float.class) || QuantAllUniversalScreenParameters.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantAllUniversalScreenParameters.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantAllUniversalScreenParameters.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantAllUniversalScreenParameters.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantAllUniversalScreenParameters'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantAllUniversalScreenParameters'", e);
            }

            // deserialize QuantFqlExpression
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantFqlExpression.class.equals(Integer.class) || QuantFqlExpression.class.equals(Long.class) || QuantFqlExpression.class.equals(Float.class) || QuantFqlExpression.class.equals(Double.class) || QuantFqlExpression.class.equals(Boolean.class) || QuantFqlExpression.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantFqlExpression.class.equals(Integer.class) || QuantFqlExpression.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantFqlExpression.class.equals(Float.class) || QuantFqlExpression.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantFqlExpression.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantFqlExpression.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantFqlExpression.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantFqlExpression'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantFqlExpression'", e);
            }

            // deserialize QuantScreeningExpression
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantScreeningExpression.class.equals(Integer.class) || QuantScreeningExpression.class.equals(Long.class) || QuantScreeningExpression.class.equals(Float.class) || QuantScreeningExpression.class.equals(Double.class) || QuantScreeningExpression.class.equals(Boolean.class) || QuantScreeningExpression.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantScreeningExpression.class.equals(Integer.class) || QuantScreeningExpression.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantScreeningExpression.class.equals(Float.class) || QuantScreeningExpression.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantScreeningExpression.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantScreeningExpression.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantScreeningExpression.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantScreeningExpression'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantScreeningExpression'", e);
            }

            // deserialize QuantUniversalScreenParameter
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantUniversalScreenParameter.class.equals(Integer.class) || QuantUniversalScreenParameter.class.equals(Long.class) || QuantUniversalScreenParameter.class.equals(Float.class) || QuantUniversalScreenParameter.class.equals(Double.class) || QuantUniversalScreenParameter.class.equals(Boolean.class) || QuantUniversalScreenParameter.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantUniversalScreenParameter.class.equals(Integer.class) || QuantUniversalScreenParameter.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantUniversalScreenParameter.class.equals(Float.class) || QuantUniversalScreenParameter.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantUniversalScreenParameter.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantUniversalScreenParameter.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantUniversalScreenParameter.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantUniversalScreenParameter'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantUniversalScreenParameter'", e);
            }

            if (match == 1) {
                OneOfQuantFormulasArray ret = new OneOfQuantFormulasArray();
                ret.setActualInstance(deserialized);
                return ret;
            }
            throw new IOException(String.format("Failed deserialization for OneOfQuantFormulasArray: %d classes match result, expected 1", match));
        }

        /**
         * Handle deserialization of the 'null' value.
         */
        @Override
        public OneOfQuantFormulasArray getNullValue(DeserializationContext ctxt) throws JsonMappingException {
            throw new JsonMappingException(ctxt.getParser(), "OneOfQuantFormulasArray cannot be null");
        }
    }

    // store a list of schema names defined in oneOf
    public static final Map<String, GenericType> schemas = new HashMap<String, GenericType>();

    public OneOfQuantFormulasArray() {
        super("oneOf", Boolean.FALSE);
    }
  /**
   * A container for additional, undeclared properties.
   * This is a holder for any undeclared properties as specified with
   * the 'additionalProperties' keyword in the OAS document.
   */
  private Map<String, Object> additionalProperties;

  /**
   * Set the additional (undeclared) property with the specified name and value.
   * If the property does not already exist, create it otherwise replace it.
   */
  @JsonAnySetter
  public OneOfQuantFormulasArray putAdditionalProperty(String key, Object value) {
    if (this.additionalProperties == null) {
        this.additionalProperties = new HashMap<String, Object>();
    }
    this.additionalProperties.put(key, value);
    return this;
  }

  /**
   * Return the additional (undeclared) property.
   */
  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  /**
   * Return the additional (undeclared) property with the specified name.
   */
  public Object getAdditionalProperty(String key) {
    if (this.additionalProperties == null) {
        return null;
    }
    return this.additionalProperties.get(key);
  }

    /**
     * Return true if this OneOfQuantFormulasArray object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && Objects.equals(this.additionalProperties, ((OneOfQuantFormulasArray)o).additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActualInstance(), isNullable(), getSchemaType(), additionalProperties);
    }
    public OneOfQuantFormulasArray(QuantAllUniversalScreenParameters o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public OneOfQuantFormulasArray(QuantFqlExpression o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public OneOfQuantFormulasArray(QuantScreeningExpression o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public OneOfQuantFormulasArray(QuantUniversalScreenParameter o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("QuantAllUniversalScreenParameters", new GenericType<QuantAllUniversalScreenParameters>() {
        });
        schemas.put("QuantFqlExpression", new GenericType<QuantFqlExpression>() {
        });
        schemas.put("QuantScreeningExpression", new GenericType<QuantScreeningExpression>() {
        });
        schemas.put("QuantUniversalScreenParameter", new GenericType<QuantUniversalScreenParameter>() {
        });
        JSON.registerDescendants(OneOfQuantFormulasArray.class, Collections.unmodifiableMap(schemas));
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("AllUniversalScreenParameters", QuantAllUniversalScreenParameters.class);
        mappings.put("FqlExpression", QuantFqlExpression.class);
        mappings.put("QuantAllUniversalScreenParameters", QuantAllUniversalScreenParameters.class);
        mappings.put("QuantFqlExpression", QuantFqlExpression.class);
        mappings.put("QuantScreeningExpression", QuantScreeningExpression.class);
        mappings.put("QuantUniversalScreenParameter", QuantUniversalScreenParameter.class);
        mappings.put("ScreeningExpression", QuantScreeningExpression.class);
        mappings.put("UniversalScreenParameter", QuantUniversalScreenParameter.class);
        mappings.put("OneOfQuantFormulasArray", OneOfQuantFormulasArray.class);
        JSON.registerDiscriminator(OneOfQuantFormulasArray.class, "source", mappings);
    }

    @Override
    public Map<String, GenericType> getSchemas() {
        return OneOfQuantFormulasArray.schemas;
    }

    /**
     * Set the instance that matches the oneOf child schema, check
     * the instance parameter is valid against the oneOf child schemas:
     * QuantAllUniversalScreenParameters, QuantFqlExpression, QuantScreeningExpression, QuantUniversalScreenParameter
     *
     * It could be an instance of the 'oneOf' schemas.
     * The oneOf child schemas may themselves be a composed schema (allOf, anyOf, oneOf).
     */
    @Override
    public void setActualInstance(Object instance) {
        if (JSON.isInstanceOf(QuantAllUniversalScreenParameters.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(QuantFqlExpression.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(QuantScreeningExpression.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(QuantUniversalScreenParameter.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be QuantAllUniversalScreenParameters, QuantFqlExpression, QuantScreeningExpression, QuantUniversalScreenParameter");
    }

    /**
     * Get the actual instance, which can be the following:
     * QuantAllUniversalScreenParameters, QuantFqlExpression, QuantScreeningExpression, QuantUniversalScreenParameter
     *
     * @return The actual instance (QuantAllUniversalScreenParameters, QuantFqlExpression, QuantScreeningExpression, QuantUniversalScreenParameter)
     */
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantAllUniversalScreenParameters`. If the actual instance is not `QuantAllUniversalScreenParameters`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantAllUniversalScreenParameters`
     * @throws ClassCastException if the instance is not `QuantAllUniversalScreenParameters`
     */
    public QuantAllUniversalScreenParameters getQuantAllUniversalScreenParameters() throws ClassCastException {
        return (QuantAllUniversalScreenParameters)super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantFqlExpression`. If the actual instance is not `QuantFqlExpression`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantFqlExpression`
     * @throws ClassCastException if the instance is not `QuantFqlExpression`
     */
    public QuantFqlExpression getQuantFqlExpression() throws ClassCastException {
        return (QuantFqlExpression)super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantScreeningExpression`. If the actual instance is not `QuantScreeningExpression`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantScreeningExpression`
     * @throws ClassCastException if the instance is not `QuantScreeningExpression`
     */
    public QuantScreeningExpression getQuantScreeningExpression() throws ClassCastException {
        return (QuantScreeningExpression)super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantUniversalScreenParameter`. If the actual instance is not `QuantUniversalScreenParameter`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantUniversalScreenParameter`
     * @throws ClassCastException if the instance is not `QuantUniversalScreenParameter`
     */
    public QuantUniversalScreenParameter getQuantUniversalScreenParameter() throws ClassCastException {
        return (QuantUniversalScreenParameter)super.getActualInstance();
    }

}

