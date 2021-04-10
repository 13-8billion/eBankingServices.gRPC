// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transactions.proto

package eBankingServices.Transactions;

/**
 * <pre>
 * transfer request
 * </pre>
 *
 * Protobuf type {@code Transactions.TransferSum}
 */
public  final class TransferSum extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Transactions.TransferSum)
    TransferSumOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TransferSum.newBuilder() to construct.
  private TransferSum(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransferSum() {
    fromAccNo_ = 0;
    toAccNo_ = 0;
    sum_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TransferSum(
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

            fromAccNo_ = input.readInt32();
            break;
          }
          case 16: {

            toAccNo_ = input.readInt32();
            break;
          }
          case 25: {

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
    return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_TransferSum_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_TransferSum_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            eBankingServices.Transactions.TransferSum.class, eBankingServices.Transactions.TransferSum.Builder.class);
  }

  public static final int FROM_ACCNO_FIELD_NUMBER = 1;
  private int fromAccNo_;
  /**
   * <code>int32 from_accNo = 1;</code>
   */
  public int getFromAccNo() {
    return fromAccNo_;
  }

  public static final int TO_ACCNO_FIELD_NUMBER = 2;
  private int toAccNo_;
  /**
   * <code>int32 to_accNo = 2;</code>
   */
  public int getToAccNo() {
    return toAccNo_;
  }

  public static final int SUM_FIELD_NUMBER = 3;
  private double sum_;
  /**
   * <code>double sum = 3;</code>
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
    if (fromAccNo_ != 0) {
      output.writeInt32(1, fromAccNo_);
    }
    if (toAccNo_ != 0) {
      output.writeInt32(2, toAccNo_);
    }
    if (sum_ != 0D) {
      output.writeDouble(3, sum_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (fromAccNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, fromAccNo_);
    }
    if (toAccNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, toAccNo_);
    }
    if (sum_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, sum_);
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
    if (!(obj instanceof eBankingServices.Transactions.TransferSum)) {
      return super.equals(obj);
    }
    eBankingServices.Transactions.TransferSum other = (eBankingServices.Transactions.TransferSum) obj;

    boolean result = true;
    result = result && (getFromAccNo()
        == other.getFromAccNo());
    result = result && (getToAccNo()
        == other.getToAccNo());
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
    hash = (37 * hash) + FROM_ACCNO_FIELD_NUMBER;
    hash = (53 * hash) + getFromAccNo();
    hash = (37 * hash) + TO_ACCNO_FIELD_NUMBER;
    hash = (53 * hash) + getToAccNo();
    hash = (37 * hash) + SUM_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSum()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static eBankingServices.Transactions.TransferSum parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static eBankingServices.Transactions.TransferSum parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static eBankingServices.Transactions.TransferSum parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static eBankingServices.Transactions.TransferSum parseFrom(
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
  public static Builder newBuilder(eBankingServices.Transactions.TransferSum prototype) {
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
   * transfer request
   * </pre>
   *
   * Protobuf type {@code Transactions.TransferSum}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Transactions.TransferSum)
      eBankingServices.Transactions.TransferSumOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_TransferSum_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_TransferSum_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              eBankingServices.Transactions.TransferSum.class, eBankingServices.Transactions.TransferSum.Builder.class);
    }

    // Construct using eBankingServices.Transactions.TransferSum.newBuilder()
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
      fromAccNo_ = 0;

      toAccNo_ = 0;

      sum_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return eBankingServices.Transactions.TransactionsImpl.internal_static_Transactions_TransferSum_descriptor;
    }

    @java.lang.Override
    public eBankingServices.Transactions.TransferSum getDefaultInstanceForType() {
      return eBankingServices.Transactions.TransferSum.getDefaultInstance();
    }

    @java.lang.Override
    public eBankingServices.Transactions.TransferSum build() {
      eBankingServices.Transactions.TransferSum result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public eBankingServices.Transactions.TransferSum buildPartial() {
      eBankingServices.Transactions.TransferSum result = new eBankingServices.Transactions.TransferSum(this);
      result.fromAccNo_ = fromAccNo_;
      result.toAccNo_ = toAccNo_;
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
      if (other instanceof eBankingServices.Transactions.TransferSum) {
        return mergeFrom((eBankingServices.Transactions.TransferSum)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(eBankingServices.Transactions.TransferSum other) {
      if (other == eBankingServices.Transactions.TransferSum.getDefaultInstance()) return this;
      if (other.getFromAccNo() != 0) {
        setFromAccNo(other.getFromAccNo());
      }
      if (other.getToAccNo() != 0) {
        setToAccNo(other.getToAccNo());
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
      eBankingServices.Transactions.TransferSum parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (eBankingServices.Transactions.TransferSum) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int fromAccNo_ ;
    /**
     * <code>int32 from_accNo = 1;</code>
     */
    public int getFromAccNo() {
      return fromAccNo_;
    }
    /**
     * <code>int32 from_accNo = 1;</code>
     */
    public Builder setFromAccNo(int value) {
      
      fromAccNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 from_accNo = 1;</code>
     */
    public Builder clearFromAccNo() {
      
      fromAccNo_ = 0;
      onChanged();
      return this;
    }

    private int toAccNo_ ;
    /**
     * <code>int32 to_accNo = 2;</code>
     */
    public int getToAccNo() {
      return toAccNo_;
    }
    /**
     * <code>int32 to_accNo = 2;</code>
     */
    public Builder setToAccNo(int value) {
      
      toAccNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 to_accNo = 2;</code>
     */
    public Builder clearToAccNo() {
      
      toAccNo_ = 0;
      onChanged();
      return this;
    }

    private double sum_ ;
    /**
     * <code>double sum = 3;</code>
     */
    public double getSum() {
      return sum_;
    }
    /**
     * <code>double sum = 3;</code>
     */
    public Builder setSum(double value) {
      
      sum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double sum = 3;</code>
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


    // @@protoc_insertion_point(builder_scope:Transactions.TransferSum)
  }

  // @@protoc_insertion_point(class_scope:Transactions.TransferSum)
  private static final eBankingServices.Transactions.TransferSum DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new eBankingServices.Transactions.TransferSum();
  }

  public static eBankingServices.Transactions.TransferSum getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransferSum>
      PARSER = new com.google.protobuf.AbstractParser<TransferSum>() {
    @java.lang.Override
    public TransferSum parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TransferSum(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransferSum> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransferSum> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public eBankingServices.Transactions.TransferSum getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

