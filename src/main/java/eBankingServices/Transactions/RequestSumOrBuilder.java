// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transactions.proto

package eBankingServices.Transactions;

public interface RequestSumOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Transactions.RequestSum)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 from_accNo = 1;</code>
   */
  int getFromAccNo();

  /**
   * <code>int32 to_accNo = 2;</code>
   */
  int getToAccNo();

  /**
   * <code>double sum = 3;</code>
   */
  double getSum();

  /**
   * <code>bool monthly = 4;</code>
   */
  boolean getMonthly();
}
