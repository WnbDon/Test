package cn.edu.jxnu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1������һ������Ӳ�̵Ĺ���
		DiskFileItemFactory disk = new DiskFileItemFactory();
		//2��������ʱĿ¼
		disk.setRepository(new File("d:/a"));  //���������ʱĿ¼�������ʹ��ϵͳ��ʱĿ¼��
		//3�� ������Ӳ��д���ݵĻ�������С,����û������ã�Ĭ��Ϊ10K��
		disk.setSizeThreshold(1024*8);//���ļ����ڴ�����ʱ����������ʱĿ¼���γ���ʱ�ļ�
		//4���������ڽ����Ķ���
		ServletFileUpload upload = new ServletFileUpload(disk);
		//5�������ϴ��ļ�������С,����Ƕ���ļ�����Ϊ����ļ��ĺ����5M
		upload.setSizeMax(1024*1024*5);
		//����httpServletRequest
		List<FileItem> items;
		try {
			items = upload.parseRequest(request);			
			FileItem item = items.get(0);//�����һ���ļ�
			//��ȡ�����ļ���
			String fileName = item.getName();
			//����ȡ�ļ���
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			//��ȡ������
			InputStream in = item.getInputStream(); 
			String path= getServletContext().getRealPath("upload");
			FileOutputStream os = new FileOutputStream(path+"/"+fileName);
			byte[] buff=new byte[1024];
			while(in.read(buff)!=-1){
				os.write(buff);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����ļ��ϴ� 

	}

}
