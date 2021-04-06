// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserAccount.proto

package eBankingServices.UserAccount;

public final class UserAccountImpl {
  private UserAccountImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAccount_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAccount_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAccount_LoginConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAccount_LoginConfirmation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAccount_ViewRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAccount_ViewRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAccount_AccountInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAccount_AccountInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAccount_PasswordRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAccount_PasswordRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAccount_PasswordConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAccount_PasswordConfirmation_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021UserAccount.proto\022\013UserAccount\"2\n\014Logi" +
      "nRequest\022\020\n\010username\030\001 \001(\t\022\020\n\010password\030\002" +
      " \001(\t\"$\n\021LoginConfirmation\022\017\n\007message\030\001 \001" +
      "(\t\"\034\n\013ViewRequest\022\r\n\005accNo\030\001 \001(\005\"c\n\013Acco" +
      "untInfo\022\017\n\007message\030\001 \001(\t\022\r\n\005accNo\030\002 \001(\005\022" +
      "\021\n\tfirstName\030\003 \001(\t\022\020\n\010lastName\030\004 \001(\t\022\017\n\007" +
      "balance\030\005 \001(\001\"F\n\017PasswordRequest\022\020\n\010user" +
      "name\030\001 \001(\t\022\020\n\010currPass\030\002 \001(\t\022\017\n\007newPass\030" +
      "\003 \001(\t\"\'\n\024PasswordConfirmation\022\017\n\007message" +
      "\030\001 \001(\t2\357\001\n\013UserAccount\022D\n\005Login\022\031.UserAc" +
      "count.LoginRequest\032\036.UserAccount.LoginCo" +
      "nfirmation\"\000\022E\n\013ViewAccount\022\030.UserAccoun" +
      "t.ViewRequest\032\030.UserAccount.AccountInfo\"" +
      "\0000\001\022S\n\016ChangePassword\022\034.UserAccount.Pass" +
      "wordRequest\032!.UserAccount.PasswordConfir" +
      "mation\"\000B1\n\034eBankingServices.UserAccount" +
      "B\017UserAccountImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_UserAccount_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_UserAccount_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAccount_LoginRequest_descriptor,
        new java.lang.String[] { "Username", "Password", });
    internal_static_UserAccount_LoginConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_UserAccount_LoginConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAccount_LoginConfirmation_descriptor,
        new java.lang.String[] { "Message", });
    internal_static_UserAccount_ViewRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_UserAccount_ViewRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAccount_ViewRequest_descriptor,
        new java.lang.String[] { "AccNo", });
    internal_static_UserAccount_AccountInfo_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_UserAccount_AccountInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAccount_AccountInfo_descriptor,
        new java.lang.String[] { "Message", "AccNo", "FirstName", "LastName", "Balance", });
    internal_static_UserAccount_PasswordRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_UserAccount_PasswordRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAccount_PasswordRequest_descriptor,
        new java.lang.String[] { "Username", "CurrPass", "NewPass", });
    internal_static_UserAccount_PasswordConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_UserAccount_PasswordConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAccount_PasswordConfirmation_descriptor,
        new java.lang.String[] { "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
