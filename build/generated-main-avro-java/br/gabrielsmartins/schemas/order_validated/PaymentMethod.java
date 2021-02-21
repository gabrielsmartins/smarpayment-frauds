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
public class PaymentMethod extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7902642319730800645L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PaymentMethod\",\"namespace\":\"br.gabrielsmartins.schemas.order_validated\",\"fields\":[{\"name\":\"payment_type\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentType\",\"symbols\":[\"CREDIT_CARD\",\"CASH\",\"INTERNET_BANKING\",\"PAYPAL\"]}},{\"name\":\"amount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":9,\"scale\":2}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();
static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<PaymentMethod> ENCODER =
      new BinaryMessageEncoder<PaymentMethod>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PaymentMethod> DECODER =
      new BinaryMessageDecoder<PaymentMethod>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PaymentMethod> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PaymentMethod> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PaymentMethod> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PaymentMethod>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PaymentMethod to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PaymentMethod from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PaymentMethod instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PaymentMethod fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public br.gabrielsmartins.schemas.order_validated.PaymentType payment_type;
  @Deprecated public java.math.BigDecimal amount;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentMethod() {}

  /**
   * All-args constructor.
   * @param payment_type The new value for payment_type
   * @param amount The new value for amount
   */
  public PaymentMethod(br.gabrielsmartins.schemas.order_validated.PaymentType payment_type, java.math.BigDecimal amount) {
    this.payment_type = payment_type;
    this.amount = amount;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return payment_type;
    case 1: return amount;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
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
    case 0: payment_type = (br.gabrielsmartins.schemas.order_validated.PaymentType)value$; break;
    case 1: amount = (java.math.BigDecimal)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'payment_type' field.
   * @return The value of the 'payment_type' field.
   */
  public br.gabrielsmartins.schemas.order_validated.PaymentType getPaymentType() {
    return payment_type;
  }


  /**
   * Sets the value of the 'payment_type' field.
   * @param value the value to set.
   */
  public void setPaymentType(br.gabrielsmartins.schemas.order_validated.PaymentType value) {
    this.payment_type = value;
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public java.math.BigDecimal getAmount() {
    return amount;
  }


  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(java.math.BigDecimal value) {
    this.amount = value;
  }

  /**
   * Creates a new PaymentMethod RecordBuilder.
   * @return A new PaymentMethod RecordBuilder
   */
  public static br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder newBuilder() {
    return new br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder();
  }

  /**
   * Creates a new PaymentMethod RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentMethod RecordBuilder
   */
  public static br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder newBuilder(br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder other) {
    if (other == null) {
      return new br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder();
    } else {
      return new br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder(other);
    }
  }

  /**
   * Creates a new PaymentMethod RecordBuilder by copying an existing PaymentMethod instance.
   * @param other The existing instance to copy.
   * @return A new PaymentMethod RecordBuilder
   */
  public static br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder newBuilder(br.gabrielsmartins.schemas.order_validated.PaymentMethod other) {
    if (other == null) {
      return new br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder();
    } else {
      return new br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder(other);
    }
  }

  /**
   * RecordBuilder for PaymentMethod instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PaymentMethod>
    implements org.apache.avro.data.RecordBuilder<PaymentMethod> {

    private br.gabrielsmartins.schemas.order_validated.PaymentType payment_type;
    private java.math.BigDecimal amount;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.payment_type)) {
        this.payment_type = data().deepCopy(fields()[0].schema(), other.payment_type);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.amount)) {
        this.amount = data().deepCopy(fields()[1].schema(), other.amount);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentMethod instance
     * @param other The existing instance to copy.
     */
    private Builder(br.gabrielsmartins.schemas.order_validated.PaymentMethod other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.payment_type)) {
        this.payment_type = data().deepCopy(fields()[0].schema(), other.payment_type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.amount)) {
        this.amount = data().deepCopy(fields()[1].schema(), other.amount);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'payment_type' field.
      * @return The value.
      */
    public br.gabrielsmartins.schemas.order_validated.PaymentType getPaymentType() {
      return payment_type;
    }


    /**
      * Sets the value of the 'payment_type' field.
      * @param value The value of 'payment_type'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder setPaymentType(br.gabrielsmartins.schemas.order_validated.PaymentType value) {
      validate(fields()[0], value);
      this.payment_type = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'payment_type' field has been set.
      * @return True if the 'payment_type' field has been set, false otherwise.
      */
    public boolean hasPaymentType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'payment_type' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder clearPaymentType() {
      payment_type = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public java.math.BigDecimal getAmount() {
      return amount;
    }


    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder setAmount(java.math.BigDecimal value) {
      validate(fields()[1], value);
      this.amount = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public br.gabrielsmartins.schemas.order_validated.PaymentMethod.Builder clearAmount() {
      amount = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentMethod build() {
      try {
        PaymentMethod record = new PaymentMethod();
        record.payment_type = fieldSetFlags()[0] ? this.payment_type : (br.gabrielsmartins.schemas.order_validated.PaymentType) defaultValue(fields()[0]);
        record.amount = fieldSetFlags()[1] ? this.amount : (java.math.BigDecimal) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PaymentMethod>
    WRITER$ = (org.apache.avro.io.DatumWriter<PaymentMethod>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentMethod>
    READER$ = (org.apache.avro.io.DatumReader<PaymentMethod>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










