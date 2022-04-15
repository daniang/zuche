package com.example.zuche.socketio;

import io.socket.engineio.server.EngineIoServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/4/13 9:50
 */
@WebServlet("/engine.io/*")
public class EngineIoServlet extends HttpServlet {

    private final EngineIoServer mEngineIoServer = new EngineIoServer();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mEngineIoServer.handleRequest(req, resp);
    }


}
