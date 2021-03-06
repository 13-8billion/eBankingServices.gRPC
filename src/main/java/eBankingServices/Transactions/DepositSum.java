// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transactions.proto

package eBankingServices.Transactions;

/**
 * <pre>
 * deposit request
 * </pre>
 *
 * Protobuf type {@code Transactions.DepositSum}
 */
public  final class DepositSum extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Transactions.DepositSum)
    DepositSumOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DepositSum.newBuilder() to construct.
  private DepositSum(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DepositSum() {
    accNo_ = 0;
    sum_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DepositSum(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            accNo_ = input.readInt32();
            break;
          }
          case 17: {

            sum_ = input.readDouble();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_DepositSum_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_DepositSum_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            eBankingServices.Transactions.DepositSum.class, eBankingServices.Transactions.DepositSum.Builder.class);
  }

  public static final int ACCNO_FIELD_NUMBER = 1;
  private int accNo_;
  /**
   * <code>int32 accNo = 1;</code>
   */
  public int getAccNo() {
    return accNo_;
  }

  public static final int SUM_FIELD_NUMBER = 2;
  private double sum_;
  /**
   * <code>double sum = 2;</code>
   */
  public double getSum() {
    return sum_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (accNo_ != 0) {
      output.writeInt32(1, accNo_);
    }
    if (sum_ != 0D) {
      output.writeDouble(2, sum_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (accNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, accNo_);
    }
    if (sum_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, sum_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof eBankingServices.Transactions.DepositSum)) {
      return super.equals(obj);
    }
    eBankingServices.Transactions.DepositSum other = (eBankingServices.Transactions.DepositSum) obj;

    boolean result = true;
    result = result && (getAccNo()
        == other.getAccNo());
    result = result && (
        java.lang.Double.doubleToLongBits(getSum())
        == java.lang.Double.doubleToLongBits(
            other.getSum()));
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ACCNO_FIELD_NUMBER;
    hash = (53 * hash) + getAccNo();
    hash = (37 * hash) + SUM_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSum()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static eBankingServices.Transactions.DepositSum parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static eBankingServices.Transactions.DepositSum parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static eBankingServices.Transactions.DepositSum parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static eBankingServices.Transactions.DepositSum parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(eBankingServices.Transactions.DepositSum prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * deposit request
   * </pre>
   *
   * Protobuf type {@code Transactions.DepositSum}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Transactions.DepositSum)
      eBankingServices.Transactions.DepositSumOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_DepositSum_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_DepositSum_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              eBankingServices.Transactions.DepositSum.class, eBankingServices.Transactions.DepositSum.Builder.class);
    }

    // Construct using eBankingServices.Transactions.DepositSum.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      accNo_ = 0;

      sum_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_DepositSum_descriptor;
    }

    @java.lang.Override
    public eBankingServices.Transactions.DepositSum getDefaultInstanceForType() {
      return eBankingServices.Transactions.DepositSum.getDefaultInstance();
    }

    @java.lang.Override
    public eBankingServices.Transactions.DepositSum build() {
      eBankingServices.Transactions.DepositSum result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public eBankingServices.Transactions.DepositSum buildPartial() {
      eBankingServices.Transactions.DepositSum result = new eBankingServices.Transactions.DepositSum(this);
      result.accNo_ = accNo_;
      result.sum_ = sum_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof eBankingServices.Transactions.DepositSum) {
        return mergeFrom((eBankingServices.Transactions.DepositSum)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(eBankingServices.Transactions.DepositSum other) {
      if (other == eBankingServices.Transactions.DepositSum.getDefaultInstance()) return this;
      if (other.getAccNo() != 0) {
        setAccNo(other.getAccNo());
      }
      if (other.getSum() != 0D) {
        setSum(other.getSum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      eBankingServices.Transactions.DepositSum parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (eBankingServices.Transactions.DepositSum) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int accNo_ ;
    /**
     * <code>int32 accNo = 1;</code>
     */
    public int getAccNo() {
      return accNo_;
    }
    /**
     * <code>int32 accNo = 1;</code>
     */
    public Builder setAccNo(int value) {
      
      accNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 accNo = 1;</code>
     */
    public Builder clearAccNo() {
      
      accNo_ = 0;
      onChanged();
      return this;
    }

    private double sum_ ;
    /**
     * <code>double sum = 2;</code>
     */
    public double getSum() {
      return sum_;
    }
    /**
     * <code>double sum = 2;</code>
     */
    public Builder setSum(double value) {
      
      sum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double sum = 2;</code>
     */
    public Builder clearSum() {
      
      sum_ = 0D;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Transactions.DepositSum)
  }

  // @@protoc_insertion_point(class_scope:Transactions.DepositSum)
  private static final eBankingServices.Transactions.DepositSum DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new eBankingServices.Transactions.DepositSum();
  }

  public static eBankingServices.Transactions.DepositSum getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DepositSum>
      PARSER = new com.google.protobuf.AbstractParser<DepositSum>() {
    @java.lang.Override
    public DepositSum parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DepositSum(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DepositSum> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DepositSum> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public eBankingServices.Transactions.DepositSum getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

