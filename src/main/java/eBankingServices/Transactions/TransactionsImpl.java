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
      "positSum\022\r\n\005accNo\030\001 \001(\005\022\013\n\003sum\030\002 \001(\001\"J\n\023" +
      "DepositConfirmation\022\021\n\tdepositID\030\001 \001(\005\022\017" +
      "\n\007balance\030\002 \001(\001\022\017\n\007message\030\003 \001(\t\"@\n\013Tran" +
      "sferSum\022\022\n\nfrom_accNo\030\001 \001(\005\022\020\n\010to_accNo\030" +
      "\002 \001(\005\022\013\n\003sum\030\003 \001(\001\";\n\024TransferConfirmati" +
      "on\022\022\n\ntransferID\030\001 \001(\005\022\017\n\007message\030\002 \001(\t\"" +
      "a\n\nRequestSum\022\022\n\nfrom_accNo\030\001 \001(\005\022\020\n\010to_" +
      "accNo\030\002 \001(\005\022\013\n\003sum\030\003 \001(\001\022\017\n\007monthly\030\004 \001(" +
      "\010\022\017\n\007approve\030\005 \001(\010\"C\n\rRequestStatus\022\016\n\006s" +
      "tatus\030\001 \001(\t\022\017\n\007message\030\002 \001(\t\022\021\n\trequestI" +
      "D\030\003 \001(\0052\357\001\n\014Transactions\022H\n\007Deposit\022\030.Tr" +
      "ansactions.DepositSum\032!.Transactions.Dep" +
      "ositConfirmation\"\000\022M\n\010Transfer\022\031.Transac" +
      "tions.TransferSum\032\".Transactions.Transfe" +
      "rConfirmation\"\000(\001\022F\n\007Request\022\030.Transacti" +
      "ons.RequestSum\032\033.Transactions.RequestSta" +
      "tus\"\000(\0010\001B3\n\035eBankingServices.Transactio" +
      "nsB\020TransactionsImplP\001b\006proto3"
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
        new java.lang.String[] { "DepositID", "Balance", "Message", });
    internal_static_Transactions_TransferSum_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Transactions_TransferSum_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_TransferSum_descriptor,
        new java.lang.String[] { "FromAccNo", "ToAccNo", "Sum", });
    internal_static_Transactions_TransferConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Transactions_TransferConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Transactions_TransferConfirmation_descriptor,
        new java.lang.String[] { "TransferID", "Message", });
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
        new java.lang.String[] { "Status", "Message", "RequestID", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
