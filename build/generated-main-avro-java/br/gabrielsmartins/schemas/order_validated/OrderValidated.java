/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package br.gabrielsmartins.schemas.order_validated;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class OrderValidated extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5737030762496619328L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderValidated\",\"namespace\":\"br.gabrielsmartins.schemas.order_validated\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"customer_id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"created_at\",\"type\":{\"type\":\"long\",\"logicalType\":\"local-timestamp-millis\"}},{\"name\":\"validated_at\",\"type\":{\"type\":\"long\",\"logicalType\":\"local-timestamp-millis\"}},{\"name\":\"fraud\",\"type\":\"boolean\"},{\"name\":\"total_amount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":9,\"scale\":2}},{\"name\":\"total_discount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":9,\"scale\":2}},{\"name\":\"items\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Item\",\"fields\":[{\"name\":\"product_id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"quantity\",\"type\":\"int\"},{\"name\":\"amount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":9,\"scale\":2}}]}}},{\"name\":\"payment_methods\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"PaymentMethod\",\"fields\":[{\"name\":\"payment_type\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentType\",\"symbols\":[\"CREDIT_CARD\",\"CASH\",\"INTERNET_BANKING\",\"PAYPAL\"]}},{\"name\":\"amount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":9,\"scale\":2}}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();
static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.LocalTimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<OrderValidated> ENCODER =
      new BinaryMessageEncoder<OrderValidated>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderValidated> DECODER =
      new BinaryMessageDecoder<OrderValidated>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<OrderValidated> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<OrderValidated> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<OrderValidated> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderValidated>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this OrderValidated to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a OrderValidated from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a OrderValidated instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static OrderValidated fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long id;
  @Deprecated public java.lang.String customer_id;
  @Deprecated public java.time.LocalDateTime created_at;
  @Deprecated public java.time.LocalDateTime validated_at;
  @Deprecated public boolean fraud;
  @Deprecated public java.math.BigDecimal total_amount;
  @Deprecated public java.math.BigDecimal total_discount;
  @Deprecated public java.util.List<br.gabrielsmartins.schemas.order_validated.Item> items;
  @Deprecated public java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> payment_methods;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderValidated() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param customer_id The new value for customer_id
   * @param created_at The new value for created_at
   * @param validated_at The new value for validated_at
   * @param fraud The new value for fraud
   * @param total_amount The new value for total_amount
   * @param total_discount The new value for total_discount
   * @param items The new value for items
   * @param payment_methods The new value for payment_methods
   */
  public OrderValidated(java.lang.Long id, java.lang.String customer_id, java.time.LocalDateTime created_at, java.time.LocalDateTime validated_at, java.lang.Boolean fraud, java.math.BigDecimal total_amount, java.math.BigDecimal total_discount, java.util.List<br.gabrielsmartins.schemas.order_validated.Item> items, java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> payment_methods) {
    this.id = id;
    this.customer_id = customer_id;
    this.created_at = created_at;
    this.validated_at = validated_at;
    this.fraud = fraud;
    this.total_amount = total_amount;
    this.total_discount = total_discount;
    this.items = items;
    this.payment_methods = payment_methods;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return customer_id;
    case 2: return created_at;
    case 3: return validated_at;
    case 4: return fraud;
    case 5: return total_amount;
    case 6: return total_discount;
    case 7: return items;
    case 8: return payment_methods;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      new org.apache.avro.data.TimeConversions.LocalTimestampMillisConversion(),
      new org.apache.avro.data.TimeConversions.LocalTimestampMillisConversion(),
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
      new org.apache.avro.Conversions.DecimalConversion(),
      null,
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: customer_id = value$ != null ? value$.toString() : null; break;
    case 2: created_at = (java.time.LocalDateTime)value$; break;
    case 3: validated_at = (java.time.LocalDateTime)value$; break;
    case 4: fraud = (java.lang.Boolean)value$; break;
    case 5: total_amount = (java.math.BigDecimal)value$; break;
    case 6: total_discount = (java.math.BigDecimal)value$; break;
    case 7: items = (java.util.List<br.gabrielsmartins.schemas.order_validated.Item>)value$; break;
    case 8: payment_methods = (java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public long getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'customer_id' field.
   * @return The value of the 'customer_id' field.
   */
  public java.lang.String getCustomerId() {
    return customer_id;
  }


  /**
   * Sets the value of the 'customer_id' field.
   * @param value the value to set.
   */
  public void setCustomerId(java.lang.String value) {
    this.customer_id = value;
  }

  /**
   * Gets the value of the 'created_at' field.
   * @return The value of the 'created_at' field.
   */
  public java.time.LocalDateTime getCreatedAt() {
    return created_at;
  }


  /**
   * Sets the value of the 'created_at' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.LocalDateTime value) {
    this.created_at = value;
  }

  /**
   * Gets the value of the 'validated_at' field.
   * @return The value of the 'validated_at' field.
   */
  public java.time.LocalDateTime getValidatedAt() {
    return validated_at;
  }


  /**
   * Sets the value of the 'validated_at' field.
   * @param value the value to set.
   */
  public void setValidatedAt(java.time.LocalDateTime value) {
    this.validated_at = value;
  }

  /**
   * Gets the value of the 'fraud' field.
   * @return The value of the 'fraud' field.
   */
  public boolean getFraud() {
    return fraud;
  }


  /**
   * Sets the value of the 'fraud' field.
   * @param value the value to set.
   */
  public void setFraud(boolean value) {
    this.fraud = value;
  }

  /**
   * Gets the value of the 'total_amount' field.
   * @return The value of the 'total_amount' field.
   */
  public java.math.BigDecimal getTotalAmount() {
    return total_amount;
  }


  /**
   * Sets the value of the 'total_amount' field.
   * @param value the value to set.
   */
  public void setTotalAmount(java.math.BigDecimal value) {
    this.total_amount = value;
  }

  /**
   * Gets the value of the 'total_discount' field.
   * @return The value of the 'total_discount' field.
   */
  public java.math.BigDecimal getTotalDiscount() {
    return total_discount;
  }


  /**
   * Sets the value of the 'total_discount' field.
   * @param value the value to set.
   */
  public void setTotalDiscount(java.math.BigDecimal value) {
    this.total_discount = value;
  }

  /**
   * Gets the value of the 'items' field.
   * @return The value of the 'items' field.
   */
  public java.util.List<br.gabrielsmartins.schemas.order_validated.Item> getItems() {
    return items;
  }


  /**
   * Sets the value of the 'items' field.
   * @param value the value to set.
   */
  public void setItems(java.util.List<br.gabrielsmartins.schemas.order_validated.Item> value) {
    this.items = value;
  }

  /**
   * Gets the value of the 'payment_methods' field.
   * @return The value of the 'payment_methods' field.
   */
  public java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> getPaymentMethods() {
    return payment_methods;
  }


  /**
   * Sets the value of the 'payment_methods' field.
   * @param value the value to set.
   */
  public void setPaymentMethods(java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> value) {
    this.payment_methods = value;
  }

  /**
   * Creates a new OrderValidated RecordBuilder.
   * @return A new OrderValidated RecordBuilder
   */
  public static br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder newBuilder() {
    return new br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder();
  }

  /**
   * Creates a new OrderValidated RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderValidated RecordBuilder
   */
  public static br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder newBuilder(br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder other) {
    if (other == null) {
      return new br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder();
    } else {
      return new br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder(other);
    }
  }

  /**
   * Creates a new OrderValidated RecordBuilder by copying an existing OrderValidated instance.
   * @param other The existing instance to copy.
   * @return A new OrderValidated RecordBuilder
   */
  public static br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder newBuilder(br.gabrielsmartins.schemas.order_validated.OrderValidated other) {
    if (other == null) {
      return new br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder();
    } else {
      return new br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder(other);
    }
  }

  /**
   * RecordBuilder for OrderValidated instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderValidated>
    implements org.apache.avro.data.RecordBuilder<OrderValidated> {

    private long id;
    private java.lang.String customer_id;
    private java.time.LocalDateTime created_at;
    private java.time.LocalDateTime validated_at;
    private boolean fraud;
    private java.math.BigDecimal total_amount;
    private java.math.BigDecimal total_discount;
    private java.util.List<br.gabrielsmartins.schemas.order_validated.Item> items;
    private java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> payment_methods;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.customer_id)) {
        this.customer_id = data().deepCopy(fields()[1].schema(), other.customer_id);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.created_at)) {
        this.created_at = data().deepCopy(fields()[2].schema(), other.created_at);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.validated_at)) {
        this.validated_at = data().deepCopy(fields()[3].schema(), other.validated_at);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.fraud)) {
        this.fraud = data().deepCopy(fields()[4].schema(), other.fraud);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.total_amount)) {
        this.total_amount = data().deepCopy(fields()[5].schema(), other.total_amount);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.total_discount)) {
        this.total_discount = data().deepCopy(fields()[6].schema(), other.total_discount);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.items)) {
        this.items = data().deepCopy(fields()[7].schema(), other.items);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.payment_methods)) {
        this.payment_methods = data().deepCopy(fields()[8].schema(), other.payment_methods);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
    }

    /**
     * Creates a Builder by copying an existing OrderValidated instance
     * @param other The existing instance to copy.
     */
    private Builder(br.gabrielsmartins.schemas.order_validated.OrderValidated other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.customer_id)) {
        this.customer_id = data().deepCopy(fields()[1].schema(), other.customer_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.created_at)) {
        this.created_at = data().deepCopy(fields()[2].schema(), other.created_at);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.validated_at)) {
        this.validated_at = data().deepCopy(fields()[3].schema(), other.validated_at);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.fraud)) {
        this.fraud = data().deepCopy(fields()[4].schema(), other.fraud);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.total_amount)) {
        this.total_amount = data().deepCopy(fields()[5].schema(), other.total_amount);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.total_discount)) {
        this.total_discount = data().deepCopy(fields()[6].schema(), other.total_discount);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.items)) {
        this.items = data().deepCopy(fields()[7].schema(), other.items);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.payment_methods)) {
        this.payment_methods = data().deepCopy(fields()[8].schema(), other.payment_methods);
        fieldSetFlags()[8] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public long getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'customer_id' field.
      * @return The value.
      */
    public java.lang.String getCustomerId() {
      return customer_id;
    }


    /**
      * Sets the value of the 'customer_id' field.
      * @param value The value of 'customer_id'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setCustomerId(java.lang.String value) {
      validate(fields()[1], value);
      this.customer_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'customer_id' field has been set.
      * @return True if the 'customer_id' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'customer_id' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearCustomerId() {
      customer_id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'created_at' field.
      * @return The value.
      */
    public java.time.LocalDateTime getCreatedAt() {
      return created_at;
    }


    /**
      * Sets the value of the 'created_at' field.
      * @param value The value of 'created_at'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setCreatedAt(java.time.LocalDateTime value) {
      validate(fields()[2], value);
      this.created_at = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'created_at' field has been set.
      * @return True if the 'created_at' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'created_at' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearCreatedAt() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'validated_at' field.
      * @return The value.
      */
    public java.time.LocalDateTime getValidatedAt() {
      return validated_at;
    }


    /**
      * Sets the value of the 'validated_at' field.
      * @param value The value of 'validated_at'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setValidatedAt(java.time.LocalDateTime value) {
      validate(fields()[3], value);
      this.validated_at = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'validated_at' field has been set.
      * @return True if the 'validated_at' field has been set, false otherwise.
      */
    public boolean hasValidatedAt() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'validated_at' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearValidatedAt() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'fraud' field.
      * @return The value.
      */
    public boolean getFraud() {
      return fraud;
    }


    /**
      * Sets the value of the 'fraud' field.
      * @param value The value of 'fraud'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setFraud(boolean value) {
      validate(fields()[4], value);
      this.fraud = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'fraud' field has been set.
      * @return True if the 'fraud' field has been set, false otherwise.
      */
    public boolean hasFraud() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'fraud' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearFraud() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'total_amount' field.
      * @return The value.
      */
    public java.math.BigDecimal getTotalAmount() {
      return total_amount;
    }


    /**
      * Sets the value of the 'total_amount' field.
      * @param value The value of 'total_amount'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setTotalAmount(java.math.BigDecimal value) {
      validate(fields()[5], value);
      this.total_amount = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'total_amount' field has been set.
      * @return True if the 'total_amount' field has been set, false otherwise.
      */
    public boolean hasTotalAmount() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'total_amount' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearTotalAmount() {
      total_amount = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'total_discount' field.
      * @return The value.
      */
    public java.math.BigDecimal getTotalDiscount() {
      return total_discount;
    }


    /**
      * Sets the value of the 'total_discount' field.
      * @param value The value of 'total_discount'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setTotalDiscount(java.math.BigDecimal value) {
      validate(fields()[6], value);
      this.total_discount = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'total_discount' field has been set.
      * @return True if the 'total_discount' field has been set, false otherwise.
      */
    public boolean hasTotalDiscount() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'total_discount' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearTotalDiscount() {
      total_discount = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'items' field.
      * @return The value.
      */
    public java.util.List<br.gabrielsmartins.schemas.order_validated.Item> getItems() {
      return items;
    }


    /**
      * Sets the value of the 'items' field.
      * @param value The value of 'items'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setItems(java.util.List<br.gabrielsmartins.schemas.order_validated.Item> value) {
      validate(fields()[7], value);
      this.items = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'items' field has been set.
      * @return True if the 'items' field has been set, false otherwise.
      */
    public boolean hasItems() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'items' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearItems() {
      items = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'payment_methods' field.
      * @return The value.
      */
    public java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> getPaymentMethods() {
      return payment_methods;
    }


    /**
      * Sets the value of the 'payment_methods' field.
      * @param value The value of 'payment_methods'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder setPaymentMethods(java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod> value) {
      validate(fields()[8], value);
      this.payment_methods = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'payment_methods' field has been set.
      * @return True if the 'payment_methods' field has been set, false otherwise.
      */
    public boolean hasPaymentMethods() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'payment_methods' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.OrderValidated.Builder clearPaymentMethods() {
      payment_methods = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderValidated build() {
      try {
        OrderValidated record = new OrderValidated();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.customer_id = fieldSetFlags()[1] ? this.customer_id : (java.lang.String) defaultValue(fields()[1]);
        record.created_at = fieldSetFlags()[2] ? this.created_at : (java.time.LocalDateTime) defaultValue(fields()[2]);
        record.validated_at = fieldSetFlags()[3] ? this.validated_at : (java.time.LocalDateTime) defaultValue(fields()[3]);
        record.fraud = fieldSetFlags()[4] ? this.fraud : (java.lang.Boolean) defaultValue(fields()[4]);
        record.total_amount = fieldSetFlags()[5] ? this.total_amount : (java.math.BigDecimal) defaultValue(fields()[5]);
        record.total_discount = fieldSetFlags()[6] ? this.total_discount : (java.math.BigDecimal) defaultValue(fields()[6]);
        record.items = fieldSetFlags()[7] ? this.items : (java.util.List<br.gabrielsmartins.schemas.order_validated.Item>) defaultValue(fields()[7]);
        record.payment_methods = fieldSetFlags()[8] ? this.payment_methods : (java.util.List<br.gabrielsmartins.schemas.order_validated.PaymentMethod>) defaultValue(fields()[8]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderValidated>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderValidated>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderValidated>
    READER$ = (org.apache.avro.io.DatumReader<OrderValidated>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










