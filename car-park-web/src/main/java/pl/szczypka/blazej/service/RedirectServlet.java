//package pl.szczypka.blazej.service;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/test")
//public class RedirectServlet extends HttpServlet {
//
//        @Override
//        protected void doGet(HttpServletRequest req,
//                             HttpServletResponse resp)
//                throws ServletException, IOException {
//            System.out.println("----- Get Request for /test ---------");
//            resp.setStatus(HttpServletResponse.SC_FOUND);//302
//            resp.setHeader("Location", "http://bbc.com");
//            // resp.sendRedirect("http://localhost:8080/example/test2");
//        }
//}