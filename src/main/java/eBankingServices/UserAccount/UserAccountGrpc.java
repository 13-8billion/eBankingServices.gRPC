package eBankingServices.UserAccount;

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
    comments = "Source: UserAccount.proto")
public final class UserAccountGrpc {

  private UserAccountGrpc() {}

  public static final String SERVICE_NAME = "UserAccount.UserAccount";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<eBankingServices.UserAccount.LoginRequest,
      eBankingServices.UserAccount.LoginConfirmation> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = eBankingServices.UserAccount.LoginRequest.class,
      responseType = eBankingServices.UserAccount.LoginConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<eBankingServices.UserAccount.LoginRequest,
      eBankingServices.UserAccount.LoginConfirmation> getLoginMethod() {
    io.grpc.MethodDescriptor<eBankingServices.UserAccount.LoginRequest, eBankingServices.UserAccount.LoginConfirmation> getLoginMethod;
    if ((getLoginMethod = UserAccountGrpc.getLoginMethod) == null) {
      synchronized (UserAccountGrpc.class) {
        if ((getLoginMethod = UserAccountGrpc.getLoginMethod) == null) {
          UserAccountGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.UserAccount.LoginRequest, eBankingServices.UserAccount.LoginConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserAccount.UserAccount", "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserAccount.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserAccount.LoginConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new UserAccountMethodDescriptorSupplier("Login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<eBankingServices.UserAccount.ViewRequest,
      eBankingServices.UserAccount.AccountInfo> getViewAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ViewAccount",
      requestType = eBankingServices.UserAccount.ViewRequest.class,
      responseType = eBankingServices.UserAccount.AccountInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<eBankingServices.UserAccount.ViewRequest,
      eBankingServices.UserAccount.AccountInfo> getViewAccountMethod() {
    io.grpc.MethodDescriptor<eBankingServices.UserAccount.ViewRequest, eBankingServices.UserAccount.AccountInfo> getViewAccountMethod;
    if ((getViewAccountMethod = UserAccountGrpc.getViewAccountMethod) == null) {
      synchronized (UserAccountGrpc.class) {
        if ((getViewAccountMethod = UserAccountGrpc.getViewAccountMethod) == null) {
          UserAccountGrpc.getViewAccountMethod = getViewAccountMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.UserAccount.ViewRequest, eBankingServices.UserAccount.AccountInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "UserAccount.UserAccount", "ViewAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserAccount.ViewRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserAccount.AccountInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new UserAccountMethodDescriptorSupplier("ViewAccount"))
                  .build();
          }
        }
     }
     return getViewAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<eBankingServices.UserAccount.PasswordRequest,
      eBankingServices.UserAccount.PasswordConfirmation> getChangePasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangePassword",
      requestType = eBankingServices.UserAccount.PasswordRequest.class,
      responseType = eBankingServices.UserAccount.PasswordConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<eBankingServices.UserAccount.PasswordRequest,
      eBankingServices.UserAccount.PasswordConfirmation> getChangePasswordMethod() {
    io.grpc.MethodDescriptor<eBankingServices.UserAccount.PasswordRequest, eBankingServices.UserAccount.PasswordConfirmation> getChangePasswordMethod;
    if ((getChangePasswordMethod = UserAccountGrpc.getChangePasswordMethod) == null) {
      synchronized (UserAccountGrpc.class) {
        if ((getChangePasswordMethod = UserAccountGrpc.getChangePasswordMethod) == null) {
          UserAccountGrpc.getChangePasswordMethod = getChangePasswordMethod = 
              io.grpc.MethodDescriptor.<eBankingServices.UserAccount.PasswordRequest, eBankingServices.UserAccount.PasswordConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "UserAccount.UserAccount", "ChangePassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserAccount.PasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  eBankingServices.UserAccount.PasswordConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new UserAccountMethodDescriptorSupplier("ChangePassword"))
                  .build();
          }
        }
     }
     return getChangePasswordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserAccountStub newStub(io.grpc.Channel channel) {
    return new UserAccountStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserAccountBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserAccountBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserAccountFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserAccountFutureStub(channel);
  }

  /**
   */
  public static abstract class UserAccountImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC method 1: Login/Logout
     * </pre>
     */
    public void login(eBankingServices.UserAccount.LoginRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserAccount.LoginConfirmation> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server-Streaming RPC method 2: View Account
     * </pre>
     */
    public void viewAccount(eBankingServices.UserAccount.ViewRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserAccount.AccountInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getViewAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC method 3: Change Password
     * </pre>
     */
    public void changePassword(eBankingServices.UserAccount.PasswordRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserAccount.PasswordConfirmation> responseObserver) {
      asyncUnimplementedUnaryCall(getChangePasswordMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                eBankingServices.UserAccount.LoginRequest,
                eBankingServices.UserAccount.LoginConfirmation>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getViewAccountMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                eBankingServices.UserAccount.ViewRequest,
                eBankingServices.UserAccount.AccountInfo>(
                  this, METHODID_VIEW_ACCOUNT)))
          .addMethod(
            getChangePasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                eBankingServices.UserAccount.PasswordRequest,
                eBankingServices.UserAccount.PasswordConfirmation>(
                  this, METHODID_CHANGE_PASSWORD)))
          .build();
    }
  }

  /**
   */
  public static final class UserAccountStub extends io.grpc.stub.AbstractStub<UserAccountStub> {
    private UserAccountStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserAccountStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserAccountStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserAccountStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 1: Login/Logout
     * </pre>
     */
    public void login(eBankingServices.UserAccount.LoginRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserAccount.LoginConfirmation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server-Streaming RPC method 2: View Account
     * </pre>
     */
    public void viewAccount(eBankingServices.UserAccount.ViewRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserAccount.AccountInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getViewAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC method 3: Change Password
     * </pre>
     */
    public void changePassword(eBankingServices.UserAccount.PasswordRequest request,
        io.grpc.stub.StreamObserver<eBankingServices.UserAccount.PasswordConfirmation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangePasswordMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserAccountBlockingStub extends io.grpc.stub.AbstractStub<UserAccountBlockingStub> {
    private UserAccountBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserAccountBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserAccountBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserAccountBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 1: Login/Logout
     * </pre>
     */
    public eBankingServices.UserAccount.LoginConfirmation login(eBankingServices.UserAccount.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server-Streaming RPC method 2: View Account
     * </pre>
     */
    public java.util.Iterator<eBankingServices.UserAccount.AccountInfo> viewAccount(
        eBankingServices.UserAccount.ViewRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getViewAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC method 3: Change Password
     * </pre>
     */
    public eBankingServices.UserAccount.PasswordConfirmation changePassword(eBankingServices.UserAccount.PasswordRequest request) {
      return blockingUnaryCall(
          getChannel(), getChangePasswordMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserAccountFutureStub extends io.grpc.stub.AbstractStub<UserAccountFutureStub> {
    private UserAccountFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserAccountFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserAccountFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserAccountFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC method 1: Login/Logout
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<eBankingServices.UserAccount.LoginConfirmation> login(
        eBankingServices.UserAccount.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC method 3: Change Password
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<eBankingServices.UserAccount.PasswordConfirmation> changePassword(
        eBankingServices.UserAccount.PasswordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChangePasswordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_VIEW_ACCOUNT = 1;
  private static final int METHODID_CHANGE_PASSWORD = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserAccountImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserAccountImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((eBankingServices.UserAccount.LoginRequest) request,
              (io.grpc.stub.StreamObserver<eBankingServices.UserAccount.LoginConfirmation>) responseObserver);
          break;
        case METHODID_VIEW_ACCOUNT:
          serviceImpl.viewAccount((eBankingServices.UserAccount.ViewRequest) request,
              (io.grpc.stub.StreamObserver<eBankingServices.UserAccount.AccountInfo>) responseObserver);
          break;
        case METHODID_CHANGE_PASSWORD:
          serviceImpl.changePassword((eBankingServices.UserAccount.PasswordRequest) request,
              (io.grpc.stub.StreamObserver<eBankingServices.UserAccount.PasswordConfirmation>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserAccountBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserAccountBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return eBankingServices.UserAccount.UserAccountImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserAccount");
    }
  }

  private static final class UserAccountFileDescriptorSupplier
      extends UserAccountBaseDescriptorSupplier {
    UserAccountFileDescriptorSupplier() {}
  }

  private static final class UserAccountMethodDescriptorSupplier
      extends UserAccountBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserAccountMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserAccountGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserAccountFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getViewAccountMethod())
              .addMethod(getChangePasswordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
