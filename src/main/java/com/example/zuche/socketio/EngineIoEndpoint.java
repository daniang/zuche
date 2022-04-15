package com.example.zuche.socketio;

import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoWebSocket;
import io.socket.engineio.server.utils.ParseQS;

import javax.websocket.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * @desc :  通用服务器
 * @Author : chengzhang
 * @Date : 2022/4/13 9:56
 */
public class EngineIoEndpoint extends Endpoint {

    private Session mSession;

    private Map<String, String> mQuery;

    private EngineIoWebSocket mEngineIoWebSocket;

    private EngineIoServer mEngineIoServer; //The engine.io server instance

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {

        mSession = session;
        mQuery = ParseQS.decode(session.getQueryString());

        mEngineIoWebSocket = new EngineIoWebSocketImpl();

        /**
         * These  cannot be converted to lambda because of runtime type inference by server.
         */
        mSession.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String s) {
                mEngineIoWebSocket.emit("message", s);
            }
        });

        mSession.addMessageHandler(new MessageHandler.Whole<byte[]>() {

            @Override
            public void onMessage(byte[] bytes) {
                mEngineIoWebSocket.emit("message", (Object) bytes);
            }
        });

        mEngineIoServer.handleWebSocket(mEngineIoWebSocket);
    }


    @Override
    public void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
        mEngineIoWebSocket.emit("close");
        mSession = null;
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        super.onError(session, throwable);

        mEngineIoWebSocket.emit("error", "unknown error ", throwable.getMessage());
    }

    private class EngineIoWebSocketImpl extends EngineIoWebSocket {
        private RemoteEndpoint.Basic mBasic;

        EngineIoWebSocketImpl() {
            mBasic = mSession.getBasicRemote();
        }

        @Override
        public Map<String, String> getQuery() {
            return mQuery;
        }

        @Override
        public Map<String, List<String>> getConnectionHeaders() {
            return null;
        }

        @Override
        public void write(String s) throws IOException {

            mBasic.sendText(s);
        }

        @Override
        public void write(byte[] bytes) throws IOException {

            mBasic.sendBinary(ByteBuffer.wrap(bytes));
        }

        @Override
        public void close() {
            try {
                mSession.close();
            } catch (IOException ignore) {


            }
        }
    }
}
