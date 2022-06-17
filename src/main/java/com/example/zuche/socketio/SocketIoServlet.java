package com.example.zuche.socketio;

import io.socket.engineio.server.Emitter;
import io.socket.engineio.server.EngineIoServer;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import io.swagger.models.Xml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc :  示例servlet类
 * @Author : chengzhang
 * @Date : 2022/4/13 9:35
 */
@WebServlet("/socket.io/*")
public class SocketIoServlet extends HttpServlet {

    private static final EngineIoServer mEngineIoServer = new EngineIoServer();

    private static final SocketIoServer mSocketIoServer = new SocketIoServer(mEngineIoServer);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mEngineIoServer.handleRequest(req, resp);
    }


    public static void main(String[] args) {
        SocketIoNamespace namespace = mSocketIoServer.namespace("/");

        namespace.on("connection", new Emitter.Listener() {


            @Override
            public void call(Object... objects) {
                SocketIoSocket socket = (SocketIoSocket) objects[0];
            }
        });

    }
}
