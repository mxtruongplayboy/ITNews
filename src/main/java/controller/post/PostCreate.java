package controller.post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.BO.categorieBO;
import model.BO.postBO;
import model.Bean.account;
import model.Bean.categorieFK;
import model.Bean.post;

@WebServlet("/post/create")
@MultipartConfig
public class PostCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categorieBO categorieBO = new categorieBO();
    	List<categorieFK> listCategorieFK = new ArrayList<>();
    	listCategorieFK = categorieBO.getAllCategorieFK();
    	request.getSession().setAttribute("listCategorieFK", listCategorieFK);
    	response.sendRedirect("../admin/addPost.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Collection<Part> parts = request.getParts();
		String title = request.getParameter("title");
		String fileName = null;
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		int categorieID =Integer.parseInt(request.getParameter("categorieID"));
		int accountID = Integer.parseInt(request.getParameter("accountsID"));
		boolean hot =Boolean.parseBoolean(request.getParameter("hot"));
		postBO postBO = new postBO();
		for (Part part : parts) {
        	fileName = getFileExtension(part);
            part.write("E:\\JAVA\\ITNews\\src\\main\\webapp\\assets\\imagesPost" + File.separator + fileName);
        }
        postBO.addPost(title, fileName, content, status, categorieID, accountID, hot);
        List<post> listPost = new ArrayList<>();
	    listPost = postBO.getAllpost();
	    request.getSession().setAttribute("listPost", listPost);
	    response.sendRedirect("../admin/post.jsp");
	}
	
	private String getFileExtension(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                int lastDotIndex = fileName.lastIndexOf(".");
                if (lastDotIndex != -1) {
                    return fileName.substring(lastDotIndex);
                }
            }
        }
        return "";
    }
}
