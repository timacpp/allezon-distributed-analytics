/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.allezon.domain;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Product extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7325922075340320157L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Product\",\"namespace\":\"com.allezon.domain\",\"fields\":[{\"name\":\"product_id\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"brand_id\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"category_id\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"price\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Product> ENCODER =
      new BinaryMessageEncoder<Product>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Product> DECODER =
      new BinaryMessageDecoder<Product>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Product> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Product> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Product> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Product>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Product to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Product from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Product instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Product fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String product_id;
  @Deprecated public java.lang.String brand_id;
  @Deprecated public java.lang.String category_id;
  @Deprecated public java.lang.String price;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Product() {}

  /**
   * All-args constructor.
   * @param product_id The new value for product_id
   * @param brand_id The new value for brand_id
   * @param category_id The new value for category_id
   * @param price The new value for price
   */
  public Product(java.lang.String product_id, java.lang.String brand_id, java.lang.String category_id, java.lang.String price) {
    this.product_id = product_id;
    this.brand_id = brand_id;
    this.category_id = category_id;
    this.price = price;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return product_id;
    case 1: return brand_id;
    case 2: return category_id;
    case 3: return price;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: product_id = (java.lang.String)value$; break;
    case 1: brand_id = (java.lang.String)value$; break;
    case 2: category_id = (java.lang.String)value$; break;
    case 3: price = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'product_id' field.
   * @return The value of the 'product_id' field.
   */
  public java.lang.String getProductId() {
    return product_id;
  }


  /**
   * Sets the value of the 'product_id' field.
   * @param value the value to set.
   */
  public void setProductId(java.lang.String value) {
    this.product_id = value;
  }

  /**
   * Gets the value of the 'brand_id' field.
   * @return The value of the 'brand_id' field.
   */
  public java.lang.String getBrandId() {
    return brand_id;
  }


  /**
   * Sets the value of the 'brand_id' field.
   * @param value the value to set.
   */
  public void setBrandId(java.lang.String value) {
    this.brand_id = value;
  }

  /**
   * Gets the value of the 'category_id' field.
   * @return The value of the 'category_id' field.
   */
  public java.lang.String getCategoryId() {
    return category_id;
  }


  /**
   * Sets the value of the 'category_id' field.
   * @param value the value to set.
   */
  public void setCategoryId(java.lang.String value) {
    this.category_id = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public java.lang.String getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(java.lang.String value) {
    this.price = value;
  }

  /**
   * Creates a new Product RecordBuilder.
   * @return A new Product RecordBuilder
   */
  public static com.allezon.domain.Product.Builder newBuilder() {
    return new com.allezon.domain.Product.Builder();
  }

  /**
   * Creates a new Product RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Product RecordBuilder
   */
  public static com.allezon.domain.Product.Builder newBuilder(com.allezon.domain.Product.Builder other) {
    if (other == null) {
      return new com.allezon.domain.Product.Builder();
    } else {
      return new com.allezon.domain.Product.Builder(other);
    }
  }

  /**
   * Creates a new Product RecordBuilder by copying an existing Product instance.
   * @param other The existing instance to copy.
   * @return A new Product RecordBuilder
   */
  public static com.allezon.domain.Product.Builder newBuilder(com.allezon.domain.Product other) {
    if (other == null) {
      return new com.allezon.domain.Product.Builder();
    } else {
      return new com.allezon.domain.Product.Builder(other);
    }
  }

  /**
   * RecordBuilder for Product instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Product>
    implements org.apache.avro.data.RecordBuilder<Product> {

    private java.lang.String product_id;
    private java.lang.String brand_id;
    private java.lang.String category_id;
    private java.lang.String price;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.allezon.domain.Product.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.product_id)) {
        this.product_id = data().deepCopy(fields()[0].schema(), other.product_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.brand_id)) {
        this.brand_id = data().deepCopy(fields()[1].schema(), other.brand_id);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.category_id)) {
        this.category_id = data().deepCopy(fields()[2].schema(), other.category_id);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.price)) {
        this.price = data().deepCopy(fields()[3].schema(), other.price);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing Product instance
     * @param other The existing instance to copy.
     */
    private Builder(com.allezon.domain.Product other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.product_id)) {
        this.product_id = data().deepCopy(fields()[0].schema(), other.product_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.brand_id)) {
        this.brand_id = data().deepCopy(fields()[1].schema(), other.brand_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.category_id)) {
        this.category_id = data().deepCopy(fields()[2].schema(), other.category_id);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.price)) {
        this.price = data().deepCopy(fields()[3].schema(), other.price);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'product_id' field.
      * @return The value.
      */
    public java.lang.String getProductId() {
      return product_id;
    }


    /**
      * Sets the value of the 'product_id' field.
      * @param value The value of 'product_id'.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder setProductId(java.lang.String value) {
      validate(fields()[0], value);
      this.product_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'product_id' field has been set.
      * @return True if the 'product_id' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'product_id' field.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder clearProductId() {
      product_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'brand_id' field.
      * @return The value.
      */
    public java.lang.String getBrandId() {
      return brand_id;
    }


    /**
      * Sets the value of the 'brand_id' field.
      * @param value The value of 'brand_id'.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder setBrandId(java.lang.String value) {
      validate(fields()[1], value);
      this.brand_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'brand_id' field has been set.
      * @return True if the 'brand_id' field has been set, false otherwise.
      */
    public boolean hasBrandId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'brand_id' field.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder clearBrandId() {
      brand_id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'category_id' field.
      * @return The value.
      */
    public java.lang.String getCategoryId() {
      return category_id;
    }


    /**
      * Sets the value of the 'category_id' field.
      * @param value The value of 'category_id'.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder setCategoryId(java.lang.String value) {
      validate(fields()[2], value);
      this.category_id = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'category_id' field has been set.
      * @return True if the 'category_id' field has been set, false otherwise.
      */
    public boolean hasCategoryId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'category_id' field.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder clearCategoryId() {
      category_id = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'price' field.
      * @return The value.
      */
    public java.lang.String getPrice() {
      return price;
    }


    /**
      * Sets the value of the 'price' field.
      * @param value The value of 'price'.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder setPrice(java.lang.String value) {
      validate(fields()[3], value);
      this.price = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public com.allezon.domain.Product.Builder clearPrice() {
      price = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Product build() {
      try {
        Product record = new Product();
        record.product_id = fieldSetFlags()[0] ? this.product_id : (java.lang.String) defaultValue(fields()[0]);
        record.brand_id = fieldSetFlags()[1] ? this.brand_id : (java.lang.String) defaultValue(fields()[1]);
        record.category_id = fieldSetFlags()[2] ? this.category_id : (java.lang.String) defaultValue(fields()[2]);
        record.price = fieldSetFlags()[3] ? this.price : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Product>
    WRITER$ = (org.apache.avro.io.DatumWriter<Product>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Product>
    READER$ = (org.apache.avro.io.DatumReader<Product>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.product_id == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.product_id);
    }

    if (this.brand_id == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.brand_id);
    }

    if (this.category_id == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.category_id);
    }

    if (this.price == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.price);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.product_id = null;
      } else {
        this.product_id = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.brand_id = null;
      } else {
        this.brand_id = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.category_id = null;
      } else {
        this.category_id = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.price = null;
      } else {
        this.price = in.readString();
      }

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.product_id = null;
          } else {
            this.product_id = in.readString();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.brand_id = null;
          } else {
            this.brand_id = in.readString();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.category_id = null;
          } else {
            this.category_id = in.readString();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.price = null;
          } else {
            this.price = in.readString();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










