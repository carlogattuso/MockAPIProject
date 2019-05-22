package edu.upc.dsa.services;

import edu.upc.dsa.MockAPI;
import edu.upc.dsa.MockAPIImpl;
import edu.upc.dsa.models.ExistantUserException;
import edu.upc.dsa.models.PasswordNotMatchException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/mock", description = "Endpoint to Mock API Service")
@Path("/mock")
public class MockAPIService {

    private MockAPI ma;

    public MockAPIService() throws Exception {
        this.ma = MockAPIImpl.getInstance();
        if (ma.size() == 0) {
            this.ma.addUser("admin", "admin");
            this.ma.addUser("carlo", "carlo");
        }
    }

    @POST
    @ApiOperation(value = "Mock Login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found", responseContainer="List"),
            @ApiResponse(code = 500, message = "Password not match", responseContainer="List")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        try{
            User u = this.ma.getUser(user.getUsername(),user.getPassword());
            return Response.status(201).entity(u).build();
        }catch(UserNotFoundException e1){
            return Response.status(404).build();
        }catch(PasswordNotMatchException e2){
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "Mock Register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Existant user", responseContainer="List")
    })
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        try{
            User u = this.ma.addUser(user.getUsername(),user.getPassword());
            return Response.status(201).entity(u).build();
        }catch(ExistantUserException e1) {
            return Response.status(500).build();
        }
    }
}