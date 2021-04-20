package eBankingServices.UserTools;

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
    comments = "Source: UserTools.proto")
public final class UserToolsGrpc {

  private UserToolsGrpc() {}

  public static final String SERVICE_NAME = "UserTools.UserTools";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<eBankingServices.UserTools.HelpRequest,
      eBankingServices.UserTools.HelpResponse> getHelpBotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HelpBot",
      requestType = eBankingServices.UserTools.HelpRequest.class,
      responseType = eBankingServices.UserTools.HelpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<eBankingServices.UserTools.HelpRequest,
      eBankingServices.UserTools.HelpResponse> getHelpBotMethod() {
    io.grpc.MethodDescriptor<eBankingServices.UserTools.HelpRequest, eBankingServices.UserTools.HelpResponse> getHelpBotMethod;
    if ((getHelpBotMethod = UserToolsGrpc.getHelpBotMethod) == null) {
      synchronized (UserToolsGrpc.class) {
        if ((getHelpBotMethod = UserToolsGrpc.getHelpBotMethod) == null) {
          UserToolsGrpc.getHelpBotMethod = getHelpBotMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.UserTools.HelpRequest, eBankingServices.UserTools.HelpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "UserTools.UserTools", "HelpBot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserTools.HelpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserTools.HelpResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserToolsMethodDescriptorSupplier("HelpBot"))
                  .build();
          }
        }
     }
     return getHelpBotMethod;
  }

  private static volatile io.grpc.MethodDescriptor<eBankingServices.UserTools.VaultAccess,
      eBankingServices.UserTools.VaultConfirmation> getVaultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Vault",
      requestType = eBankingServices.UserTools.VaultAccess.class,
      responseType = eBankingServices.UserTools.VaultConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<eBankingServices.UserTools.VaultAccess,
      eBankingServices.UserTools.VaultConfirmation> getVaultMethod() {
    io.grpc.MethodDescriptor<eBankingServices.UserTools.VaultAccess, eBankingServices.UserTools.VaultConfirmation> getVaultMethod;
    if ((getVaultMethod = UserToolsGrpc.getVaultMethod) == null) {
      synchronized (UserToolsGrpc.class) {
        if ((getVaultMethod = UserToolsGrpc.getVaultMethod) == null) {
          UserToolsGrpc.getVaultMethod = getVaultMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.UserTools.VaultAccess, eBankingServices.UserTools.VaultConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserTools.UserTools", "Vault"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserTools.VaultAccess.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserTools.VaultConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new UserToolsMethodDescriptorSupplier("Vault"))
                  .build();
          }
        }
     }
     return getVaultMethod;
  }

  private static volatile io.grpc.MethodDescriptor<eBankingServices.UserTools.CalcRequest,
      eBankingServices.UserTools.CalcResponse> getInterestCalcMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InterestCalc",
      requestType = eBankingServices.UserTools.CalcRequest.class,
      responseType = eBankingServices.UserTools.CalcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<eBankingServices.UserTools.CalcRequest,
      eBankingServices.UserTools.CalcResponse> getInterestCalcMethod() {
    io.grpc.MethodDescriptor<eBankingServices.UserTools.CalcRequest, eBankingServices.UserTools.CalcResponse> getInterestCalcMethod;
    if ((getInterestCalcMethod = UserToolsGrpc.getInterestCalcMethod) == null) {
      synchronized (UserToolsGrpc.class) {
        if ((getInterestCalcMethod = UserToolsGrpc.getInterestCalcMethod) == null) {
          UserToolsGrpc.getInterestCalcMethod = getInterestCalcMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.UserTools.CalcRequest, eBankingServices.UserTools.CalcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserTools.UserTools", "InterestCalc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserTools.CalcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserTools.CalcResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserToolsMethodDescriptorSupplier("InterestCalc"))
                  .build();
          }
        }
     }
     return getInterestCalcMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserToolsStub newStub(io.grpc.Channel channel) {
    return new UserToolsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserToolsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserToolsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserToolsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserToolsFutureStub(channel);
  }

  /**
   */
  public static abstract class UserToolsImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Bi-directional streaming RPC method 1: Help Bot
     * </pre>
     */
    public io.grpc.stub.StreamObserver<eBankingServices.UserTools.HelpRequest> helpBot(
        io.grpc.stub.StreamObserver<eBankingServices.UserTools.HelpResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getHelpBotMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC method 2: Vaults
     * </pre>
     */
    public void vault(eBankingServices.UserTools.VaultAccess request,
        io.grpc.stub.StreamObserver<eBankingServices.UserTools.VaultConfirmation> responseObserver) {
      asyncUnimplementedUnaryCall(getVaultMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC method 3: Interest Calculator
     * </pre>
     */
    public void interestCalc(eBankingServices.UserTools.CalcRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserTools.CalcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInterestCalcMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHelpBotMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                eBankingServices.UserTools.HelpRequest,
                eBankingServices.UserTools.HelpResponse>(
                  this, METHODID_HELP_BOT)))
          .addMethod(
            getVaultMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                eBankingServices.UserTools.VaultAccess,
                eBankingServices.UserTools.VaultConfirmation>(
                  this, METHODID_VAULT)))
          .addMethod(
            getInterestCalcMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                eBankingServices.UserTools.CalcRequest,
                eBankingServices.UserTools.CalcResponse>(
                  this, METHODID_INTEREST_CALC)))
          .build();
    }
  }

  /**
   */
  public static final class UserToolsStub extends io.grpc.stub.AbstractStub<UserToolsStub> {
    private UserToolsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserToolsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserToolsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserToolsStub(channel, callOptions);
    }

    /**
     * <pre>
     * Bi-directional streaming RPC method 1: Help Bot
     * </pre>
     */
    public io.grpc.stub.StreamObserver<eBankingServices.UserTools.HelpRequest> helpBot(
        io.grpc.stub.StreamObserver<eBankingServices.UserTools.HelpResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getHelpBotMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC method 2: Vaults
     * </pre>
     */
    public void vault(eBankingServices.UserTools.VaultAccess request,
        io.grpc.stub.StreamObserver<eBankingServices.UserTools.VaultConfirmation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVaultMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC method 3: Interest Calculator
     * </pre>
     */
    public void interestCalc(eBankingServices.UserTools.CalcRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserTools.CalcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInterestCalcMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserToolsBlockingStub extends io.grpc.stub.AbstractStub<UserToolsBlockingStub> {
    private UserToolsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserToolsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserToolsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserToolsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 2: Vaults
     * </pre>
     */
    public eBankingServices.UserTools.VaultConfirmation vault(eBankingServices.UserTools.VaultAccess request) {
      return blockingUnaryCall(
          getChannel(), getVaultMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC method 3: Interest Calculator
     * </pre>
     */
    public eBankingServices.UserTools.CalcResponse interestCalc(eBankingServices.UserTools.CalcRequest request) {
      return blockingUnaryCall(
          getChannel(), getInterestCalcMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserToolsFutureStub extends io.grpc.stub.AbstractStub<UserToolsFutureStub> {
    private UserToolsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserToolsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserToolsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserToolsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 2: Vaults
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<eBankingServices.UserTools.VaultConfirmation> vault(
        eBankingServices.UserTools.VaultAccess request) {
      return futureUnaryCall(
          getChannel().newCall(getVaultMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC method 3: Interest Calculator
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<eBankingServices.UserTools.CalcResponse> interestCalc(
        eBankingServices.UserTools.CalcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInterestCalcMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VAULT = 0;
  private static final int METHODID_INTEREST_CALC = 1;
  private static final int METHODID_HELP_BOT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserToolsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserToolsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_VAULT:
          serviceImpl.vault((eBankingServices.UserTools.VaultAccess) request,
              (io.grpc.stub.StreamObserver<eBankingServices.UserTools.VaultConfirmation>) responseObserver);
          break;
        case METHODID_INTEREST_CALC:
          serviceImpl.interestCalc((eBankingServices.UserTools.CalcRequest) request,
              (io.grpc.stub.StreamObserver<eBankingServices.UserTools.CalcResponse>) responseObserver);
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
        case METHODID_HELP_BOT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.helpBot(
              (io.grpc.stub.StreamObserver<eBankingServices.UserTools.HelpResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserToolsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserToolsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return eBankingServices.UserTools.UserToolsImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserTools");
    }
  }

  private static final class UserToolsFileDescriptorSupplier
      extends UserToolsBaseDescriptorSupplier {
    UserToolsFileDescriptorSupplier() {}
  }

  private static final class UserToolsMethodDescriptorSupplier
      extends UserToolsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserToolsMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserToolsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserToolsFileDescriptorSupplier())
              .addMethod(getHelpBotMethod())
              .addMethod(getVaultMethod())
              .addMethod(getInterestCalcMethod())
              .build();
        }
      }
    }
    return result;
  }
}
