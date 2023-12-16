package controller.post;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.BO.categorieBO;
import model.BO.postBO;
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
		Part part = request.getPart("image");
		String title = request.getParameter("title");
		String fileName = null;
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		int categorieID =Integer.parseInt(request.getParameter("categorieID"));
		int accountID = Integer.parseInt(request.getParameter("accountsID"));
		boolean hot =Boolean.parseBoolean(request.getParameter("hot"));
		String description = request.getParameter("description");
		postBO postBO = new postBO();
		
		// Start upload file
		ServletContext servletContext = request.getServletContext();
		// Get the real path to the root of the web application
		String rootPath = servletContext.getRealPath("/");

		// Specify a relative path for storing uploaded files
		String relativePath = "assets/imagePosts/";

		// Combine the root path and relative path
		String basePath = rootPath + relativePath;
		System.out.println(basePath);
		
		// Extract file name and extension from Content-Disposition header
        String contentDispositionHeader = part.getHeader("Content-Disposition");
        fileName = extractFileName(contentDispositionHeader);
        fileName = extractFileNameWithoutExtension(fileName) + generateUniqueId() + "." + extractFileNameExtension(fileName);
        System.out.println(fileName);
        
        InputStream inputStream = part.getInputStream();

        // Tạo đường dẫn đầy đủ đến file đích sử dụng đường dẫn tương đối
        Path outputPath = Paths.get(basePath, fileName);

        // Sử dụng Files.copy để sao chép InputStream vào file
        Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);

        // Tùy chọn, đóng InputStream
        inputStream.close();
        // End
            
        postBO.addPost(title, fileName, content, status, categorieID, accountID, hot, description);
        List<post> listPost = new ArrayList<>();
	    listPost = postBO.getAllpost();
	    request.getSession().setAttribute("listPost", listPost);
	    response.sendRedirect("../admin/post.jsp");
	}
	
	private String extractFileNameWithoutExtension(String filePath) {
	    // Remove the file extension
	    int dotIndex = filePath.lastIndexOf('.');
	    String fileName = "default";
	    if (dotIndex > 0) {
	    	fileName = filePath.substring(0, dotIndex);
	    }

	    return fileName;
	}
	
	private String extractFileNameExtension(String filePath) {
	    // Remove the file extension
	    int dotIndex = filePath.lastIndexOf('.');
	    String fileExtension = "png";
	    if (dotIndex > 0) {
	    	fileExtension = filePath.substring(dotIndex + 1);
	    }

	    return fileExtension;
	}
	
	private String generateUniqueId() {
		UUID id = UUID.randomUUID();
		return id.toString();
	}
	
	private String extractFileName(String contentDispositionHeader) {
	    String[] elements = contentDispositionHeader.split(";");
	    for (String element : elements) {
	        if (element.trim().startsWith("filename")) {
	            // Extract and return the file name with extension
	            return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    // If no filename information found in the header, generate a default name or handle accordingly
	    return "defaultFileName"; 
	}
}
