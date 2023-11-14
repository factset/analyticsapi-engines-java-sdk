/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
 * Contact: testapi@factset.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package factset.analyticsapi.engines.models;

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
import factset.analyticsapi.engines.models.QuantIdentifierUniverse;
import factset.analyticsapi.engines.models.QuantScreeningExpressionUniverse;
import factset.analyticsapi.engines.models.QuantUniversalScreenUniverse;
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
@JsonDeserialize(using = OneOfQuantUniverse.OneOfQuantUniverseDeserializer.class)
@JsonSerialize(using = OneOfQuantUniverse.OneOfQuantUniverseSerializer.class)
public class OneOfQuantUniverse extends AbstractOpenApiSchema implements Serializable {
    private static final Logger log = Logger.getLogger(OneOfQuantUniverse.class.getName());

    public static class OneOfQuantUniverseSerializer extends StdSerializer<OneOfQuantUniverse> {
        public OneOfQuantUniverseSerializer(Class<OneOfQuantUniverse> t) {
            super(t);
        }

        public OneOfQuantUniverseSerializer() {
            this(null);
        }

        @Override
        public void serialize(OneOfQuantUniverse value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeObject(value.getActualInstance());
        }
    }

    public static class OneOfQuantUniverseDeserializer extends StdDeserializer<OneOfQuantUniverse> {
        public OneOfQuantUniverseDeserializer() {
            this(OneOfQuantUniverse.class);
        }

        public OneOfQuantUniverseDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public OneOfQuantUniverse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            Object deserialized = null;
            boolean typeCoercion = ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS);
            int match = 0;
            JsonToken token = tree.traverse(jp.getCodec()).nextToken();
            // deserialize QuantIdentifierUniverse
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantIdentifierUniverse.class.equals(Integer.class) || QuantIdentifierUniverse.class.equals(Long.class) || QuantIdentifierUniverse.class.equals(Float.class) || QuantIdentifierUniverse.class.equals(Double.class) || QuantIdentifierUniverse.class.equals(Boolean.class) || QuantIdentifierUniverse.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantIdentifierUniverse.class.equals(Integer.class) || QuantIdentifierUniverse.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantIdentifierUniverse.class.equals(Float.class) || QuantIdentifierUniverse.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantIdentifierUniverse.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantIdentifierUniverse.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantIdentifierUniverse.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantIdentifierUniverse'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantIdentifierUniverse'", e);
            }

            // deserialize QuantScreeningExpressionUniverse
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantScreeningExpressionUniverse.class.equals(Integer.class) || QuantScreeningExpressionUniverse.class.equals(Long.class) || QuantScreeningExpressionUniverse.class.equals(Float.class) || QuantScreeningExpressionUniverse.class.equals(Double.class) || QuantScreeningExpressionUniverse.class.equals(Boolean.class) || QuantScreeningExpressionUniverse.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantScreeningExpressionUniverse.class.equals(Integer.class) || QuantScreeningExpressionUniverse.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantScreeningExpressionUniverse.class.equals(Float.class) || QuantScreeningExpressionUniverse.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantScreeningExpressionUniverse.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantScreeningExpressionUniverse.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantScreeningExpressionUniverse.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantScreeningExpressionUniverse'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantScreeningExpressionUniverse'", e);
            }

            // deserialize QuantUniversalScreenUniverse
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantUniversalScreenUniverse.class.equals(Integer.class) || QuantUniversalScreenUniverse.class.equals(Long.class) || QuantUniversalScreenUniverse.class.equals(Float.class) || QuantUniversalScreenUniverse.class.equals(Double.class) || QuantUniversalScreenUniverse.class.equals(Boolean.class) || QuantUniversalScreenUniverse.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantUniversalScreenUniverse.class.equals(Integer.class) || QuantUniversalScreenUniverse.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantUniversalScreenUniverse.class.equals(Float.class) || QuantUniversalScreenUniverse.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantUniversalScreenUniverse.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantUniversalScreenUniverse.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantUniversalScreenUniverse.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantUniversalScreenUniverse'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantUniversalScreenUniverse'", e);
            }

            if (match == 1) {
                OneOfQuantUniverse ret = new OneOfQuantUniverse();
                ret.setActualInstance(deserialized);
                return ret;
            }
            throw new IOException(String.format("Failed deserialization for OneOfQuantUniverse: %d classes match result, expected 1", match));
        }

        /**
         * Handle deserialization of the 'null' value.
         */
        @Override
        public OneOfQuantUniverse getNullValue(DeserializationContext ctxt) throws JsonMappingException {
            throw new JsonMappingException(ctxt.getParser(), "OneOfQuantUniverse cannot be null");
        }
    }

    // store a list of schema names defined in oneOf
    public static final Map<String, GenericType> schemas = new HashMap<String, GenericType>();

    public OneOfQuantUniverse() {
        super("oneOf", Boolean.FALSE);
    }

    public OneOfQuantUniverse(QuantIdentifierUniverse o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public OneOfQuantUniverse(QuantScreeningExpressionUniverse o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public OneOfQuantUniverse(QuantUniversalScreenUniverse o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("QuantIdentifierUniverse", new GenericType<QuantIdentifierUniverse>() {
        });
        schemas.put("QuantScreeningExpressionUniverse", new GenericType<QuantScreeningExpressionUniverse>() {
        });
        schemas.put("QuantUniversalScreenUniverse", new GenericType<QuantUniversalScreenUniverse>() {
        });
        JSON.registerDescendants(OneOfQuantUniverse.class, Collections.unmodifiableMap(schemas));
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("IdentifierUniverse", QuantIdentifierUniverse.class);
        mappings.put("QuantIdentifierUniverse", QuantIdentifierUniverse.class);
        mappings.put("QuantScreeningExpressionUniverse", QuantScreeningExpressionUniverse.class);
        mappings.put("QuantUniversalScreenUniverse", QuantUniversalScreenUniverse.class);
        mappings.put("ScreeningExpressionUniverse", QuantScreeningExpressionUniverse.class);
        mappings.put("UniversalScreenUniverse", QuantUniversalScreenUniverse.class);
        mappings.put("OneOfQuantUniverse", OneOfQuantUniverse.class);
        JSON.registerDiscriminator(OneOfQuantUniverse.class, "source", mappings);
    }

    @Override
    public Map<String, GenericType> getSchemas() {
        return OneOfQuantUniverse.schemas;
    }

    /**
     * Set the instance that matches the oneOf child schema, check
     * the instance parameter is valid against the oneOf child schemas:
     * QuantIdentifierUniverse, QuantScreeningExpressionUniverse, QuantUniversalScreenUniverse
     *
     * It could be an instance of the 'oneOf' schemas.
     * The oneOf child schemas may themselves be a composed schema (allOf, anyOf, oneOf).
     */
    @Override
    public void setActualInstance(Object instance) {
        if (JSON.isInstanceOf(QuantIdentifierUniverse.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(QuantScreeningExpressionUniverse.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(QuantUniversalScreenUniverse.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be QuantIdentifierUniverse, QuantScreeningExpressionUniverse, QuantUniversalScreenUniverse");
    }

    /**
     * Get the actual instance, which can be the following:
     * QuantIdentifierUniverse, QuantScreeningExpressionUniverse, QuantUniversalScreenUniverse
     *
     * @return The actual instance (QuantIdentifierUniverse, QuantScreeningExpressionUniverse, QuantUniversalScreenUniverse)
     */
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantIdentifierUniverse`. If the actual instanct is not `QuantIdentifierUniverse`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantIdentifierUniverse`
     * @throws ClassCastException if the instance is not `QuantIdentifierUniverse`
     */
    public QuantIdentifierUniverse getQuantIdentifierUniverse() throws ClassCastException {
        return (QuantIdentifierUniverse)super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantScreeningExpressionUniverse`. If the actual instanct is not `QuantScreeningExpressionUniverse`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantScreeningExpressionUniverse`
     * @throws ClassCastException if the instance is not `QuantScreeningExpressionUniverse`
     */
    public QuantScreeningExpressionUniverse getQuantScreeningExpressionUniverse() throws ClassCastException {
        return (QuantScreeningExpressionUniverse)super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantUniversalScreenUniverse`. If the actual instanct is not `QuantUniversalScreenUniverse`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantUniversalScreenUniverse`
     * @throws ClassCastException if the instance is not `QuantUniversalScreenUniverse`
     */
    public QuantUniversalScreenUniverse getQuantUniversalScreenUniverse() throws ClassCastException {
        return (QuantUniversalScreenUniverse)super.getActualInstance();
    }

}
