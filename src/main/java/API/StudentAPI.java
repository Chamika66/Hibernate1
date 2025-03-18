package API;

import DTO.StudentDto;
import com.google.gson.Gson;
import entity.StudentEntity;
import repo.StudentRepo;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/student")
public class StudentAPI extends HttpServlet {

    private StudentService studentService;
    public StudentAPI(){
        this.studentService = new StudentService(new StudentRepo());
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDto studentDto = new Gson().fromJson(reader, StudentDto.class);
            StudentDto result = studentService.saveStudent(studentDto);
            if(result != null){
                resp.getWriter().write(new Gson().toJson(result));
            }else {
                resp.getWriter().write("Something went wrong");
            }

        }else {
            resp.getWriter().write("Wrong content type");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String id = req.getParameter("id");
         resp.setContentType("application/json");

         if(id != null){
             StudentDto student =studentService.getStudentById(Integer.parseInt(id));
             if(student != null){
                 resp.getWriter().write(new Gson().toJson(student));
             }
             else{
                 resp.getWriter().write("Student not found");
             }
         }else{
             resp.getWriter().write("Student not found");
         }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("Content-Type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDto studentDto = new Gson().fromJson(reader, StudentDto.class);
            StudentDto result = studentService.updateStudent(studentDto);
            if(result != null){
                resp.getWriter().write(new Gson().toJson(result));
            }else {
                resp.getWriter().write("Something went wrong");
            }

        }else {
            resp.getWriter().write("Wrong content type");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null){
            StudentDto student = studentService.getStudentById(Integer.parseInt(id));
            if(student != null){
                studentService.deleteStudent(Integer.parseInt(id));
                resp.getWriter().write("Student deleted");
            }
            else{
                resp.getWriter().write("Student not found");
            }
        }else{
            resp.getWriter().write("Student not found");
        }
    }
}
