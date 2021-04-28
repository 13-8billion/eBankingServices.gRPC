package eBankingServices.Transactions;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Transactions.proto")
public final class TransactionsGrpc {

  private TransactionsGrpc() {}

  public static final String SERVICE_NAME = "Transactions.Transactions";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<eBankingServices.Transactions.DepositSum,
      eBankingServices.Transactions.DepositConfirmation> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = eBankingServices.Transactions.DepositSum.class,
      responseType = eBankingServices.Transactions.DepositConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<eBankingServices.Transactions.DepositSum,
      eBankingServices.Transactions.DepositConfirmation> getDepositMethod() {
    io.grpc.MethodDescriptor<eBankingServices.Transactions.DepositSum, eBankingServices.Transactions.DepositConfirmation> getDepositMethod;
    if ((getDepositMethod = TransactionsGrpc.getDepositMethod) == null) {
      synchronized (TransactionsGrpc.class) {
        if ((getDepositMethod = TransactionsGrpc.getDepositMethod) == null) {
          TransactionsGrpc.getDepositMethod = getDepositMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.Transactions.DepositSum, eBankingServices.Transactions.DepositConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Transactions.Transactions", "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.Transactions.DepositSum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.Transactions.DepositConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new TransactionsMethodDescriptorSupplier("Deposit"))
                  .build();
          }
        }
     }
     return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<eBankingServices.Transactions.TransferSum,
      eBankingServices.Transactions.TransferConfirmation> getTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Transfer",
      requestType = eBankingServices.Transactions.TransferSum.class,
      responseType = eBankingServices.Transactions.TransferConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<eBankingServices.Transactions.TransferSum,
      eBankingServices.Transactions.TransferConfirmation> getTransferMethod() {
    io.grpc.MethodDescriptor<eBankingServices.Transactions.TransferSum, eBankingServices.Transactions.TransferConfirmation> getTransferMethod;
    if ((getTransferMethod = TransactionsGrpc.getTransferMethod) == null) {
      synchronized (TransactionsGrpc.class) {
        if ((getTransferMethod = TransactionsGrpc.getTransferMethod) == null) {
          TransactionsGrpc.getTransferMethod = getTransferMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.Transactions.TransferSum, eBankingServices.Transactions.TransferConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Transactions.Transactions", "Transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.Transactions.TransferSum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.Transactions.TransferConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new TransactionsMethodDescriptorSupplier("Transfer"))
                  .build();
          }
        }
     }
     return getTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<eBankingServices.Transactions.RequestSum,
      eBankingServices.Transactions.RequestStatus> getRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Request",
      requestType = eBankingServices.Transactions.RequestSum.class,
      responseType = eBankingServices.Transactions.RequestStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<eBankingServices.Transactions.RequestSum,
      eBankingServices.Transactions.RequestStatus> getRequestMethod() {
    io.grpc.MethodDescriptor<eBankingServices.Transactions.RequestSum, eBankingServices.Transactions.RequestStatus> getRequestMethod;
    if ((getRequestMethod = TransactionsGrpc.getRequestMethod) == null) {
      synchronized (TransactionsGrpc.class) {
        if ((getRequestMethod = TransactionsGrpc.getRequestMethod) == null) {
          TransactionsGrpc.getRequestMethod = getRequestMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.Transactions.RequestSum, eBankingServices.Transactions.RequestStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Transactions.Transactions", "Request"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.Transactions.RequestSum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.Transactions.RequestStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new TransactionsMethodDescriptorSupplier("Request"))
                  .build();
          }
        }
     }
     return getRequestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionsStub newStub(io.grpc.Channel channel) {
    return new TransactionsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TransactionsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TransactionsFutureStub(channel);
  }

  /**
   */
  public static abstract class TransactionsImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC method 1: Deposit
     * </pre>
     */
    public void deposit(eBankingServices.Transactions.DepositSum request,
        io.grpc.stub.StreamObserver<eBankingServices.Transactions.DepositConfirmation> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client-Streaming RPC method 2: Transfer
     * </pre>
     */
    public io.grpc.stub.StreamObserver<eBankingServices.Transactions.TransferSum> transfer(
        io.grpc.stub.StreamObserver<eBankingServices.Transactions.TransferConfirmation> responseObserver) {
      return asyncUnimplementedStreamingCall(getTransferMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bi-Directional RPC method 3: Request
     * </pre>
     */
    public io.grpc.stub.StreamObserver<eBankingServices.Transactions.RequestSum> request(
        io.grpc.stub.StreamObserver<eBankingServices.Transactions.RequestStatus> responseObserver) {
      return asyncUnimplementedStreamingCall(getRequestMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                eBankingServices.Transactions.DepositSum,
                eBankingServices.Transactions.DepositConfirmation>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getTransferMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                eBankingServices.Transactions.TransferSum,
                eBankingServices.Transactions.TransferConfirmation>(
                  this, METHODID_TRANSFER)))
          .addMethod(
            getRequestMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                eBankingServices.Transactions.RequestSum,
                eBankingServices.Transactions.RequestStatus>(
                  this, METHODID_REQUEST)))
          .build();
    }
  }

  /**
   */
  public static final class TransactionsStub extends io.grpc.stub.AbstractStub<TransactionsStub> {
    private TransactionsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransactionsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransactionsStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 1: Deposit
     * </pre>
     */
    public void deposit(eBankingServices.Transactions.DepositSum request,
        io.grpc.stub.StreamObserver<eBankingServices.Transactions.DepositConfirmation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client-Streaming RPC method 2: Transfer
     * </pre>
     */
    public io.grpc.stub.StreamObserver<eBankingServices.Transactions.TransferSum> transfer(
        io.grpc.stub.StreamObserver<eBankingServices.Transactions.TransferConfirmation> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Bi-Directional RPC method 3: Request
     * </pre>
     */
    public io.grpc.stub.StreamObserver<eBankingServices.Transactions.RequestSum> request(
        io.grpc.stub.StreamObserver<eBankingServices.Transactions.RequestStatus> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class TransactionsBlockingStub extends io.grpc.stub.AbstractStub<TransactionsBlockingStub> {
    private TransactionsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransactionsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransactionsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 1: Deposit
     * </pre>
     */
    public eBankingServices.Transactions.DepositConfirmation deposit(eBankingServices.Transactions.DepositSum request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TransactionsFutureStub extends io.grpc.stub.AbstractStub<TransactionsFutureStub> {
    private TransactionsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransactionsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransactionsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 1: Deposit
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<eBankingServices.Transactions.DepositConfirmation> deposit(
        eBankingServices.Transactions.DepositSum request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_TRANSFER = 1;
  private static final int METHODID_REQUEST = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TransactionsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TransactionsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSIT:
          serviceImpl.deposit((eBankingServices.Transactions.DepositSum) request,
              (io.grpc.stub.StreamObserver<eBankingServices.Transactions.DepositConfirmation>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRANSFER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.transfer(
              (io.grpc.stub.StreamObserver<eBankingServices.Transactions.TransferConfirmation>) responseObserver);
        case METHODID_REQUEST:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.request(
              (io.grpc.stub.StreamObserver<eBankingServices.Transactions.RequestStatus>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TransactionsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return eBankingServices.Transactions.TransactionsImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Transactions");
    }
  }

  private static final class TransactionsFileDescriptorSupplier
      extends TransactionsBaseDescriptorSupplier {
    TransactionsFileDescriptorSupplier() {}
  }

  private static final class TransactionsMethodDescriptorSupplier
      extends TransactionsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TransactionsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TransactionsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionsFileDescriptorSupplier())
              .addMethod(getDepositMethod())
              .addMethod(getTransferMethod())
              .addMethod(getRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
