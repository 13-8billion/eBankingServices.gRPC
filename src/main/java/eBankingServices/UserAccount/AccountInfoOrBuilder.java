// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserAccount.proto

package eBankingServices.UserAccount;

public interface AccountInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UserAccount.AccountInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string message = 1;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 1;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>int32 accNo = 2;</code>
   */
  int getAccNo();

  /**
   * <code>string firstName = 3;</code>
   */
  java.lang.String getFirstName();
  /**
   * <code>string firstName = 3;</code>
   */
  com.google.protobuf.ByteString
      getFirstNameBytes();

  /**
   * <code>string lastName = 4;</code>
   */
  java.lang.String getLastName();
  /**
   * <code>string lastName = 4;</code>
   */
  com.google.protobuf.ByteString
      getLastNameBytes();

  /**
   * <code>double balance = 5;</code>
   */
  double getBalance();

  /**
   * <code>string accInfo = 6;</code>
   */
  java.lang.String getAccInfo();
  /**
   * <code>string accInfo = 6;</code>
   */
  com.google.protobuf.ByteString
      getAccInfoBytes();
}
