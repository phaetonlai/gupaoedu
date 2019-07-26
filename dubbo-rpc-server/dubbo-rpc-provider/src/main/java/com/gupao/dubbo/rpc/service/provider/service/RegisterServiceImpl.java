package com.gupao.dubbo.rpc.service.provider.service;

import com.gupao.dubbo.rpc.service.api.IRegisterService;
import com.gupao.dubbo.rpc.service.api.User;
import org.apache.dubbo.rpc.protocol.rest.support.ContentType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Author laihui
 * @Date 2019/7/25
 * @Desc
 * @Version 1.0
 **/
@Path("/register")
public class RegisterServiceImpl implements IRegisterService {

    @GET
    @Path("/{name}")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
    public User register(@PathParam("name") String name) {
        return new User(name);
    }
}
