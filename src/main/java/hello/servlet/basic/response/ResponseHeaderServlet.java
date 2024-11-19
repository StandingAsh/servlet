package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Status Line
        response.setStatus(HttpServletResponse.SC_OK);

        // Response Headers
        // response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // Header 편의 메소드
        content(response);
        cookie(response);
        redirect(response);

        // Message Body
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }

    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("my-cookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location", "/basic/hello-form.html");

        response.sendRedirect("/basic/hello-form.html");
    }
}
