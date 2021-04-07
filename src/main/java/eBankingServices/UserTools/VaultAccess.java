// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserTools.proto

package eBankingServices.UserTools;

/**
 * <pre>
 * valut message
 * </pre>
 *
 * Protobuf type {@code UserTools.VaultAccess}
 */
public  final class VaultAccess extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:UserTools.VaultAccess)
    VaultAccessOrBuilder {
private static final long serialVersionUID = 0L;
  // Use VaultAccess.newBuilder() to construct.
  private VaultAccess(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private VaultAccess() {
    accNo_ = 0;
    sum_ = 0D;
    unlockDate_ = "";
    vaultID_ = 0;
    username_ = "";
    password_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private VaultAccess(
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
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            unlockDate_ = s;
            break;
          }
          case 32: {

            vaultID_ = input.readInt32();
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            username_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            password_ = s;
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
    return eBankingServices.UserTools.UserToolsImpl.internal_static_UserTools_VaultAccess_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return eBankingServices.UserTools.UserToolsImpl.internal_static_UserTools_VaultAccess_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            eBankingServices.UserTools.VaultAccess.class, eBankingServices.UserTools.VaultAccess.Builder.class);
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

  public static final int UNLOCKDATE_FIELD_NUMBER = 3;
  private volatile java.lang.Object unlockDate_;
  /**
   * <code>string unlockDate = 3;</code>
   */
  public java.lang.String getUnlockDate() {
    java.lang.Object ref = unlockDate_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      unlockDate_ = s;
      return s;
    }
  }
  /**
   * <code>string unlockDate = 3;</code>
   */
  public com.google.protobuf.ByteString
      getUnlockDateBytes() {
    java.lang.Object ref = unlockDate_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      unlockDate_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VAULTID_FIELD_NUMBER = 4;
  private int vaultID_;
  /**
   * <code>int32 vaultID = 4;</code>
   */
  public int getVaultID() {
    return vaultID_;
  }

  public static final int USERNAME_FIELD_NUMBER = 5;
  private volatile java.lang.Object username_;
  /**
   * <code>string username = 5;</code>
   */
  public java.lang.String getUsername() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      username_ = s;
      return s;
    }
  }
  /**
   * <code>string username = 5;</code>
   */
  public com.google.protobuf.ByteString
      getUsernameBytes() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      username_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PASSWORD_FIELD_NUMBER = 6;
  private volatile java.lang.Object password_;
  /**
   * <code>string password = 6;</code>
   */
  public java.lang.String getPassword() {
    java.lang.Object ref = password_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      password_ = s;
      return s;
    }
  }
  /**
   * <code>string password = 6;</code>
   */
  public com.google.protobuf.ByteString
      getPasswordBytes() {
    java.lang.Object ref = password_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      password_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getUnlockDateBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, unlockDate_);
    }
    if (vaultID_ != 0) {
      output.writeInt32(4, vaultID_);
    }
    if (!getUsernameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, username_);
    }
    if (!getPasswordBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, password_);
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
    if (!getUnlockDateBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, unlockDate_);
    }
    if (vaultID_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, vaultID_);
    }
    if (!getUsernameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, username_);
    }
    if (!getPasswordBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, password_);
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
    if (!(obj instanceof eBankingServices.UserTools.VaultAccess)) {
      return super.equals(obj);
    }
    eBankingServices.UserTools.VaultAccess other = (eBankingServices.UserTools.VaultAccess) obj;

    boolean result = true;
    result = result && (getAccNo()
        == other.getAccNo());
    result = result && (
        java.lang.Double.doubleToLongBits(getSum())
        == java.lang.Double.doubleToLongBits(
            other.getSum()));
    result = result && getUnlockDate()
        .equals(other.getUnlockDate());
    result = result && (getVaultID()
        == other.getVaultID());
    result = result && getUsername()
        .equals(other.getUsername());
    result = result && getPassword()
        .equals(other.getPassword());
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
    hash = (37 * hash) + UNLOCKDATE_FIELD_NUMBER;
    hash = (53 * hash) + getUnlockDate().hashCode();
    hash = (37 * hash) + VAULTID_FIELD_NUMBER;
    hash = (53 * hash) + getVaultID();
    hash = (37 * hash) + USERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUsername().hashCode();
    hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
    hash = (53 * hash) + getPassword().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static eBankingServices.UserTools.VaultAccess parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static eBankingServices.UserTools.VaultAccess parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static eBankingServices.UserTools.VaultAccess parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static eBankingServices.UserTools.VaultAccess parseFrom(
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
  public static Builder newBuilder(eBankingServices.UserTools.VaultAccess prototype) {
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
   * valut message
   * </pre>
   *
   * Protobuf type {@code UserTools.VaultAccess}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:UserTools.VaultAccess)
      eBankingServices.UserTools.VaultAccessOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return eBankingServices.UserTools.UserToolsImpl.internal_static_UserTools_VaultAccess_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return eBankingServices.UserTools.UserToolsImpl.internal_static_UserTools_VaultAccess_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              eBankingServices.UserTools.VaultAccess.class, eBankingServices.UserTools.VaultAccess.Builder.class);
    }

    // Construct using eBankingServices.UserTools.VaultAccess.newBuilder()
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

      unlockDate_ = "";

      vaultID_ = 0;

      username_ = "";

      password_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return eBankingServices.UserTools.UserToolsImpl.internal_static_UserTools_VaultAccess_descriptor;
    }

    @java.lang.Override
    public eBankingServices.UserTools.VaultAccess getDefaultInstanceForType() {
      return eBankingServices.UserTools.VaultAccess.getDefaultInstance();
    }

    @java.lang.Override
    public eBankingServices.UserTools.VaultAccess build() {
      eBankingServices.UserTools.VaultAccess result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public eBankingServices.UserTools.VaultAccess buildPartial() {
      eBankingServices.UserTools.VaultAccess result = new eBankingServices.UserTools.VaultAccess(this);
      result.accNo_ = accNo_;
      result.sum_ = sum_;
      result.unlockDate_ = unlockDate_;
      result.vaultID_ = vaultID_;
      result.username_ = username_;
      result.password_ = password_;
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
      if (other instanceof eBankingServices.UserTools.VaultAccess) {
        return mergeFrom((eBankingServices.UserTools.VaultAccess)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(eBankingServices.UserTools.VaultAccess other) {
      if (other == eBankingServices.UserTools.VaultAccess.getDefaultInstance()) return this;
      if (other.getAccNo() != 0) {
        setAccNo(other.getAccNo());
      }
      if (other.getSum() != 0D) {
        setSum(other.getSum());
      }
      if (!other.getUnlockDate().isEmpty()) {
        unlockDate_ = other.unlockDate_;
        onChanged();
      }
      if (other.getVaultID() != 0) {
        setVaultID(other.getVaultID());
      }
      if (!other.getUsername().isEmpty()) {
        username_ = other.username_;
        onChanged();
      }
      if (!other.getPassword().isEmpty()) {
        password_ = other.password_;
        onChanged();
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
      eBankingServices.UserTools.VaultAccess parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (eBankingServices.UserTools.VaultAccess) e.getUnfinishedMessage();
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

    private java.lang.Object unlockDate_ = "";
    /**
     * <code>string unlockDate = 3;</code>
     */
    public java.lang.String getUnlockDate() {
      java.lang.Object ref = unlockDate_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        unlockDate_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string unlockDate = 3;</code>
     */
    public com.google.protobuf.ByteString
        getUnlockDateBytes() {
      java.lang.Object ref = unlockDate_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        unlockDate_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string unlockDate = 3;</code>
     */
    public Builder setUnlockDate(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      unlockDate_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string unlockDate = 3;</code>
     */
    public Builder clearUnlockDate() {
      
      unlockDate_ = getDefaultInstance().getUnlockDate();
      onChanged();
      return this;
    }
    /**
     * <code>string unlockDate = 3;</code>
     */
    public Builder setUnlockDateBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      unlockDate_ = value;
      onChanged();
      return this;
    }

    private int vaultID_ ;
    /**
     * <code>int32 vaultID = 4;</code>
     */
    public int getVaultID() {
      return vaultID_;
    }
    /**
     * <code>int32 vaultID = 4;</code>
     */
    public Builder setVaultID(int value) {
      
      vaultID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 vaultID = 4;</code>
     */
    public Builder clearVaultID() {
      
      vaultID_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object username_ = "";
    /**
     * <code>string username = 5;</code>
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string username = 5;</code>
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string username = 5;</code>
     */
    public Builder setUsername(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      username_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string username = 5;</code>
     */
    public Builder clearUsername() {
      
      username_ = getDefaultInstance().getUsername();
      onChanged();
      return this;
    }
    /**
     * <code>string username = 5;</code>
     */
    public Builder setUsernameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      username_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object password_ = "";
    /**
     * <code>string password = 6;</code>
     */
    public java.lang.String getPassword() {
      java.lang.Object ref = password_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        password_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string password = 6;</code>
     */
    public com.google.protobuf.ByteString
        getPasswordBytes() {
      java.lang.Object ref = password_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string password = 6;</code>
     */
    public Builder setPassword(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      password_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string password = 6;</code>
     */
    public Builder clearPassword() {
      
      password_ = getDefaultInstance().getPassword();
      onChanged();
      return this;
    }
    /**
     * <code>string password = 6;</code>
     */
    public Builder setPasswordBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      password_ = value;
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


    // @@protoc_insertion_point(builder_scope:UserTools.VaultAccess)
  }

  // @@protoc_insertion_point(class_scope:UserTools.VaultAccess)
  private static final eBankingServices.UserTools.VaultAccess DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new eBankingServices.UserTools.VaultAccess();
  }

  public static eBankingServices.UserTools.VaultAccess getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<VaultAccess>
      PARSER = new com.google.protobuf.AbstractParser<VaultAccess>() {
    @java.lang.Override
    public VaultAccess parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new VaultAccess(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<VaultAccess> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<VaultAccess> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public eBankingServices.UserTools.VaultAccess getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

