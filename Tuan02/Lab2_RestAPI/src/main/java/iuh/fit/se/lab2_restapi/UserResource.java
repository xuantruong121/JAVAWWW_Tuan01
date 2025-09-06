package iuh.fit.se.lab2_restapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UserResource {

    private UserService userService = new UserService();

    // Lấy danh sách users: HTTP GET /api/users
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No users found.").build();
        }
        return Response.ok(users).build();
    }

    // Lấy thông tin của 1 user: HTTP GET /api/users/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) throws SQLException {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
        }
        return Response.ok(user).build();
    }

    // Thêm mới 1 user: HTTP POST /api/users
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws SQLException {
        userService.addUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    // Cập nhật 1 user: HTTP PUT /api/users/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User user) throws SQLException {
        user.setId(id);
        // Giả sử có phương thức updateUser trong UserService
        // userService.updateUser(user);
        return Response.ok(user).build();
    }

    // Xoá 1 user: HTTP DELETE /api/users/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws SQLException {
        // Giả sử có phương thức deleteUser trong UserService
        // userService.deleteUser(id);
        return Response.noContent().build();
    }
}
