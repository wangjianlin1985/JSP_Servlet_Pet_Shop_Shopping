// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileUploadException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.FileOutputStream;
import java.util.Date;
import org.apache.commons.fileupload.FileItem;
import model.Goods;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import service.GoodsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "admin_goods_add", urlPatterns = { "/admin/goods_add" })
public class AdminGoodsAddServlet extends HttpServlet
{
    private GoodsService gService;
    
    public AdminGoodsAddServlet() {
        this.gService = new GoodsService();
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DiskFileItemFactory factory = new DiskFileItemFactory();
        final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
        try {
            final List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
            final Goods g = new Goods();
            for (final FileItem item : list) {
                if (item.isFormField()) {
                    final String fieldName;
                    switch ((fieldName = item.getFieldName()).hashCode()) {
                        case -858802731: {
                            if (!fieldName.equals("typeid")) {
                                continue;
                            }
                            g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                            continue;
                        }
                        case 3373707: {
                            if (!fieldName.equals("name")) {
                                continue;
                            }
                            g.setName(item.getString("utf-8"));
                            continue;
                        }
                        case 100361836: {
                            if (!fieldName.equals("intro")) {
                                continue;
                            }
                            g.setIntro(item.getString("utf-8"));
                            continue;
                        }
                        case 106934601: {
                            if (!fieldName.equals("price")) {
                                continue;
                            }
                            g.setPrice(Integer.parseInt(item.getString("utf-8")));
                            continue;
                        }
                        case 109770518: {
                            if (!fieldName.equals("stock")) {
                                continue;
                            }
                            g.setStock(Integer.parseInt(item.getString("utf-8")));
                            continue;
                        }
                    }
                }
                else {
                    if (item.getInputStream().available() <= 0) {
                        continue;
                    }
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/" + new Date().getTime() + fileName;
                    final String path = String.valueOf(this.getServletContext().getRealPath("/picture")) + fileName;
                    final InputStream in = item.getInputStream();
                    final FileOutputStream out = new FileOutputStream(path);
                    final byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    final String fieldName2;
                    switch ((fieldName2 = item.getFieldName()).hashCode()) {
                        case -1185250762: {
                            if (!fieldName2.equals("image1")) {
                                continue;
                            }
                            g.setImage1("/picture" + fileName);
                            continue;
                        }
                        case -1185250761: {
                            if (!fieldName2.equals("image2")) {
                                continue;
                            }
                            g.setImage2("/picture" + fileName);
                            continue;
                        }
                        case 94852023: {
                            if (!fieldName2.equals("cover")) {
                                continue;
                            }
                            g.setCover("/picture" + fileName);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            this.gService.insert(g);
            request.getRequestDispatcher("/admin/goods_list").forward((ServletRequest)request, (ServletResponse)response);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
