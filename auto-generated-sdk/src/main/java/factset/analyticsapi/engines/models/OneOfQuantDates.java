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
import factset.analyticsapi.engines.models.QuantDateList;
import factset.analyticsapi.engines.models.QuantFdsDate;
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
@JsonDeserialize(using = OneOfQuantDates.OneOfQuantDatesDeserializer.class)
@JsonSerialize(using = OneOfQuantDates.OneOfQuantDatesSerializer.class)
public class OneOfQuantDates extends AbstractOpenApiSchema implements Serializable {
    private static final Logger log = Logger.getLogger(OneOfQuantDates.class.getName());

    public static class OneOfQuantDatesSerializer extends StdSerializer<OneOfQuantDates> {
        public OneOfQuantDatesSerializer(Class<OneOfQuantDates> t) {
            super(t);
        }

        public OneOfQuantDatesSerializer() {
            this(null);
        }

        @Override
        public void serialize(OneOfQuantDates value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeObject(value.getActualInstance());
        }
    }

    public static class OneOfQuantDatesDeserializer extends StdDeserializer<OneOfQuantDates> {
        public OneOfQuantDatesDeserializer() {
            this(OneOfQuantDates.class);
        }

        public OneOfQuantDatesDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public OneOfQuantDates deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            Object deserialized = null;
            boolean typeCoercion = ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS);
            int match = 0;
            JsonToken token = tree.traverse(jp.getCodec()).nextToken();
            // deserialize QuantDateList
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantDateList.class.equals(Integer.class) || QuantDateList.class.equals(Long.class) || QuantDateList.class.equals(Float.class) || QuantDateList.class.equals(Double.class) || QuantDateList.class.equals(Boolean.class) || QuantDateList.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantDateList.class.equals(Integer.class) || QuantDateList.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantDateList.class.equals(Float.class) || QuantDateList.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantDateList.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantDateList.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantDateList.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantDateList'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantDateList'", e);
            }

            // deserialize QuantFdsDate
            try {
                boolean attemptParsing = true;
                // ensure that we respect type coercion as set on the client ObjectMapper
                if (QuantFdsDate.class.equals(Integer.class) || QuantFdsDate.class.equals(Long.class) || QuantFdsDate.class.equals(Float.class) || QuantFdsDate.class.equals(Double.class) || QuantFdsDate.class.equals(Boolean.class) || QuantFdsDate.class.equals(String.class)) {
                    attemptParsing = typeCoercion;
                    if (!attemptParsing) {
                        attemptParsing |= ((QuantFdsDate.class.equals(Integer.class) || QuantFdsDate.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                        attemptParsing |= ((QuantFdsDate.class.equals(Float.class) || QuantFdsDate.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                        attemptParsing |= (QuantFdsDate.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                        attemptParsing |= (QuantFdsDate.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                    }
                }
                if (attemptParsing) {
                    deserialized = tree.traverse(jp.getCodec()).readValueAs(QuantFdsDate.class);
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'QuantFdsDate'");
                }
            } catch (Exception e) {
                // deserialization failed, continue
                log.log(Level.FINER, "Input data does not match schema 'QuantFdsDate'", e);
            }

            if (match == 1) {
                OneOfQuantDates ret = new OneOfQuantDates();
                ret.setActualInstance(deserialized);
                return ret;
            }
            throw new IOException(String.format("Failed deserialization for OneOfQuantDates: %d classes match result, expected 1", match));
        }

        /**
         * Handle deserialization of the 'null' value.
         */
        @Override
        public OneOfQuantDates getNullValue(DeserializationContext ctxt) throws JsonMappingException {
            throw new JsonMappingException(ctxt.getParser(), "OneOfQuantDates cannot be null");
        }
    }

    // store a list of schema names defined in oneOf
    public static final Map<String, GenericType> schemas = new HashMap<String, GenericType>();

    public OneOfQuantDates() {
        super("oneOf", Boolean.FALSE);
    }

    public OneOfQuantDates(QuantDateList o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    public OneOfQuantDates(QuantFdsDate o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("QuantDateList", new GenericType<QuantDateList>() {
        });
        schemas.put("QuantFdsDate", new GenericType<QuantFdsDate>() {
        });
        JSON.registerDescendants(OneOfQuantDates.class, Collections.unmodifiableMap(schemas));
        // Initialize and register the discriminator mappings.
        Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
        mappings.put("DateList", QuantDateList.class);
        mappings.put("FdsDate", QuantFdsDate.class);
        mappings.put("QuantDateList", QuantDateList.class);
        mappings.put("QuantFdsDate", QuantFdsDate.class);
        mappings.put("OneOfQuantDates", OneOfQuantDates.class);
        JSON.registerDiscriminator(OneOfQuantDates.class, "source", mappings);
    }

    @Override
    public Map<String, GenericType> getSchemas() {
        return OneOfQuantDates.schemas;
    }

    /**
     * Set the instance that matches the oneOf child schema, check
     * the instance parameter is valid against the oneOf child schemas:
     * QuantDateList, QuantFdsDate
     *
     * It could be an instance of the 'oneOf' schemas.
     * The oneOf child schemas may themselves be a composed schema (allOf, anyOf, oneOf).
     */
    @Override
    public void setActualInstance(Object instance) {
        if (JSON.isInstanceOf(QuantDateList.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        if (JSON.isInstanceOf(QuantFdsDate.class, instance, new HashSet<Class<?>>())) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be QuantDateList, QuantFdsDate");
    }

    /**
     * Get the actual instance, which can be the following:
     * QuantDateList, QuantFdsDate
     *
     * @return The actual instance (QuantDateList, QuantFdsDate)
     */
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantDateList`. If the actual instanct is not `QuantDateList`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantDateList`
     * @throws ClassCastException if the instance is not `QuantDateList`
     */
    public QuantDateList getQuantDateList() throws ClassCastException {
        return (QuantDateList)super.getActualInstance();
    }

    /**
     * Get the actual instance of `QuantFdsDate`. If the actual instanct is not `QuantFdsDate`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `QuantFdsDate`
     * @throws ClassCastException if the instance is not `QuantFdsDate`
     */
    public QuantFdsDate getQuantFdsDate() throws ClassCastException {
        return (QuantFdsDate)super.getActualInstance();
    }

}
