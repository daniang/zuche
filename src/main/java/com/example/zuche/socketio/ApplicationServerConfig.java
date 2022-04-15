package com.example.zuche.socketio;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/4/13 10:21
 */
public final class ApplicationServerConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {

        final HashSet<ServerEndpointConfig> result = new HashSet<>();
        result.add(ServerEndpointConfig.Builder.create(ServerEndpointConfig.class, "engine.io").build());
        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        return null;
    }
}
