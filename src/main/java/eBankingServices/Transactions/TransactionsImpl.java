// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transactions.proto

package eBankingServices.Transactions;

public final class TransactionsImpl {
  private TransactionsImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transactions_DepositSum_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transactions_DepositSum_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transactions_DepositConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transactions_DepositConfirmation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transactions_TransferSum_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transactions_TransferSum_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transactions_TransferConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transactions_TransferConfirmation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transactions_RequestSum_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transactions_RequestSum_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Transactions_RequestStatus_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Transactions_RequestStatus_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022Transactions.proto\022\014Transactions\"(\n\nDe" +
      "positSum\022\r\n\005accNo\030\001 \001(\005\022\013\n\003sum\030\002 \001(\001\"7\n\023" +
      "DepositConfirmation\022\017\n\007balance\030\001 \001(\001\022\017\n\007" +
      "message\030\002 \001(\t\"Q\n\013TransferSum\022\022\n\nfrom_acc" +
      "No\030\001 \001(\005\022\020\n\010to_accNo\030\002 \001(\005\022\013\n\003sum\030\003 \001(\001\022" +
      "\017\n\007message\030\004 \001(\t\"$\n\024TransferConfirmation" +
      "\022\014\n\004conf\030\001 \001(\t\"a\n\nRequestSum\022\022\n\nfrom_acc" +
      "No\030\001 \001(\005\022\020\n\010to_accNo\030\002 \001(\005\022\013\n\003sum\030\003 \001(\001\022" +
      "\017\n\007monthly\030\004 \001(\010\022\017\n\007approve\030\005 \001(\010\"0\n\rReq" +
      "uestStatus\022\016\n\006status\030\001 \001(\t\022\017\n\007message\030\002 " +
      "\001(\t2\361\001\n\014Transactions\022H\n\007Deposit\022\030.Transa" +
      "ctions.DepositSum\032!.Transactions.Deposit" +
      "Confirmation\"\000\022O\n\010Transfer\022\031.Transaction" +
      "s.TransferSum\032\".Transactions.TransferCon" +
      "firmation\"\000(\0010\001\022F\n\007Request\022\030.Transaction" +
      "s.RequestSum\032\033.Transactions.RequestStatu" +
      "s\"\000(\0010\001B3\n\035eBankingServices.Transactions" +
      "B\020TransactionsImplP\001b\006proto3"
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
    internal_static_Transactions_DepositSum_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Transactions_DepositSum_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_DepositSum_descriptor,
        new java.lang.String[] { "AccNo", "Sum", });
    internal_static_Transactions_DepositConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Transactions_DepositConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_DepositConfirmation_descriptor,
        new java.lang.String[] { "Balance", "Message", });
    internal_static_Transactions_TransferSum_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Transactions_TransferSum_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_TransferSum_descriptor,
        new java.lang.String[] { "FromAccNo", "ToAccNo", "Sum", "Message", });
    internal_static_Transactions_TransferConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Transactions_TransferConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_TransferConfirmation_descriptor,
        new java.lang.String[] { "Conf", });
    internal_static_Transactions_RequestSum_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_Transactions_RequestSum_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_RequestSum_descriptor,
        new java.lang.String[] { "FromAccNo", "ToAccNo", "Sum", "Monthly", "Approve", });
    internal_static_Transactions_RequestStatus_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_Transactions_RequestStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_RequestStatus_descriptor,
        new java.lang.String[] { "Status", "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
