package com.omdasoft.orderonline.gwt.order.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omdasoft.orderonline.gwt.order.server.login.ImageUrlActionHandler;
import com.omdasoft.orderonline.util.DateUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author yanrui
 * 
 * */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// text/html IE浏览器下无法解析
		response.setContentType("text/plain;charset=utf-8");

		StringBuffer responseMessage = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		responseMessage.append("<root>");

		StringBuffer errorMsg = new StringBuffer(responseMessage)
				.append("<result>").append("FAILED").append("</result>");

		String info = "";
		ServletFileUpload upload = new ServletFileUpload();

		FileItemIterator iter = null;
		try {
			iter = upload.getItemIterator(request);

			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();

				if (item.isFormField()) {
					info = "Form field " + name + " with value "
							+ Streams.asString(stream) + " detected.";
					responseMessage = new StringBuffer(responseMessage)
							.append(errorMsg).append("<info>").append(info)
							.append("</info>");
					finishPrintResponseMsg(response, responseMessage);
					return;
				} else {
					byte[] bytes = IOUtils.toByteArray(stream);
					// BufferedInputStream inputStream = new
					// BufferedInputStream(bytes);// 获得输入流

					String uploadPath = getUploadPath(request,  request.getParameter("corpid"));
					if (uploadPath != null) {
						String fileName = getOutputFileName(item);
						String outputFilePath = getOutputFilePath(uploadPath,
								fileName);

						int widthdist = 72;
						int heightdist = 72;

						widthdist = 144;
						heightdist = 144;

						String uploadPathmin = getUploadPathMin(request, request.getParameter("corpid"));
						String outputFilePathMin = getOutputFilePath(
								uploadPathmin, fileName);

						FileOutputStream outputStream = new FileOutputStream(
								new File(outputFilePath));// 获得文件输出流

						// stream.close();

						IOUtils.write(bytes, outputStream);
						// Streams.copy(inputStream, outputStream, true);
						// //开始把文件写到你指定的上传文件夹

						reduceImg(new ByteArrayInputStream(bytes),
								outputFilePathMin, outputFilePathMin,
								widthdist, heightdist, 0);

						stream.close();

						responseMessage.append("<result>").append("SUCCESS")
								.append("</result>");
						responseMessage.append("<info>");
						responseMessage.append(fileName);
						responseMessage.append(info).append("</info>");
					} else {
						responseMessage = errorMsg.append("<info>")
								.append("未定义图片上传路径").append("</info>");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMessage = errorMsg.append("<info>")
					.append("文件上传异常:" + e.getMessage()).append("</info>");
		}
		finishPrintResponseMsg(response, responseMessage);
	}

	private static String getOutputFilePath(String uploadPath, String fileName) {

		String outputFilePath = uploadPath + File.separator + fileName;
		return outputFilePath;

	}

	private static String getOutputFileName(FileItemStream item) {
		String itemName = item.getName();
		itemName = itemName.substring(itemName.lastIndexOf("."),
				itemName.length());
		String fileName = DateUtil.getDateString("yyyyMMddHHmmss");
		fileName += new Random().nextInt(10000);
		fileName += itemName;

		return fileName;
	}

	/**
	 * @param imgsrc
	 *            本地图片路径
	 * @param imgdist
	 *            上传服务器路径
	 * @param widthdist
	 *            压缩宽度
	 * @param heightdist
	 *            压缩高度
	 * @param int benchmark 说明:0,长宽哪个长，以哪个为标准；1，以宽为基准；2，以高为基准
	 * 
	 */
	public void reduceImg(InputStream inputStream, String outputFilePath,
			String imgdist, int widthdist, int heightdist, int benchmark) {
		try {
			// File srcfile = new File(srcFile);
			// if (!srcfile.exists()) {
			// return;
			// }

			boolean flag = true;

			Image srcImage = javax.imageio.ImageIO.read(inputStream);

			if (srcImage != null) {
				int width = srcImage.getWidth(null);
				int height = srcImage.getHeight(null);
				if (width <= widthdist && height <= heightdist) {// 像素更小，直接上传
					BufferedOutputStream outputStream = new BufferedOutputStream(
							new FileOutputStream(new File(outputFilePath)));
					Streams.copy(inputStream, outputStream, true);
					flag = false;
				}

				if (flag) {

					// 宽度除以高度的比例
					float wh = (float) width / (float) height;
					if (benchmark == 0) {
						if (wh > 1) {
							float tmp_heigth = (float) widthdist / wh;
							BufferedImage tag = new BufferedImage(widthdist,
									(int) tmp_heigth,
									BufferedImage.TYPE_INT_RGB);
							tag.getGraphics().drawImage(srcImage, 0, 0,
									widthdist, (int) tmp_heigth, null);
							FileOutputStream out = new FileOutputStream(imgdist);
							JPEGImageEncoder encoder = JPEGCodec
									.createJPEGEncoder(out);
							encoder.encode(tag);
							out.close();
						} else {
							float tmp_width = (float) heightdist * wh;
							BufferedImage tag = new BufferedImage(
									(int) tmp_width, heightdist,
									BufferedImage.TYPE_INT_RGB);
							tag.getGraphics().drawImage(srcImage, 0, 0,
									(int) tmp_width, heightdist, null);
							FileOutputStream out = new FileOutputStream(imgdist);
							JPEGImageEncoder encoder = JPEGCodec
									.createJPEGEncoder(out);
							encoder.encode(tag);
							out.close();
						}
					}
					if (benchmark == 1) {
						float tmp_heigth = (float) widthdist / wh;
						BufferedImage tag = new BufferedImage(widthdist,
								(int) tmp_heigth, BufferedImage.TYPE_INT_RGB);
						tag.getGraphics().drawImage(srcImage, 0, 0, widthdist,
								(int) tmp_heigth, null);
						FileOutputStream out = new FileOutputStream(imgdist);
						JPEGImageEncoder encoder = JPEGCodec
								.createJPEGEncoder(out);
						encoder.encode(tag);
						out.close();
					}
					if (benchmark == 2) {
						float tmp_width = (float) heightdist * wh;
						BufferedImage tag = new BufferedImage((int) tmp_width,
								heightdist, BufferedImage.TYPE_INT_RGB);
						tag.getGraphics().drawImage(srcImage, 0, 0,
								(int) tmp_width, heightdist, null);
						FileOutputStream out = new FileOutputStream(imgdist);
						JPEGImageEncoder encoder = JPEGCodec
								.createJPEGEncoder(out);
						encoder.encode(tag);
						out.close();
					}

				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param uploadRootName
	 *            上传图片目录
	 * 
	 * */
	private static String getUploadPath(HttpServletRequest request,
			String uploadRootName) {
		String uploadurl="";
		String jbossName="";
		Properties properties = new Properties();
		try {
			properties.load(ImageUrlActionHandler.class	.getResourceAsStream("configuration.properties"));
			jbossName=properties.getProperty("jbossName");
			uploadurl=properties.getProperty("uploadUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String uploadPath = null;

		if (!StringUtil.isEmpty(uploadurl)) {
			uploadPath = uploadurl + uploadRootName;
			createFilePath(uploadPath);
		} else {
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");

			// System.out.println("============realPath:" + realPath);
			int rootIndex = realPath.indexOf(jbossName);
			if (rootIndex < 0) {
				rootIndex = realPath.indexOf("war");
			}

			if (rootIndex < 0) {
				return null;
			} else {
				realPath = realPath.substring(0, rootIndex);
				uploadPath = realPath + uploadRootName;
				createFilePath(uploadPath);
			}
		}
		return uploadPath;
	}

	/**
	 * 检查文件目录
	 * 
	 * @param uploadPath
	 */
	private static void createFilePath(String uploadPath) {
		File myFilePath = new File(uploadPath);
		if (!myFilePath.exists()) {
			myFilePath.mkdirs();
			System.out.println("创建图片上传文件夹：" + myFilePath);
		}
	}

	/**
	 * @param uploadRootName
	 *            上传图片目录
	 * 
	 * */
	private static String getUploadPathMin(HttpServletRequest request,
			String uploadRootName) {
		String uploadurl="";
		String jbossName="";
		Properties properties = new Properties();
		try {
			properties.load(ImageUrlActionHandler.class	.getResourceAsStream("configuration.properties"));
			jbossName=properties.getProperty("jbossName");
			uploadurl=properties.getProperty("uploadUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String uploadPath = null;

		if (!StringUtil.isEmpty(uploadurl)) {
			uploadPath = uploadurl + uploadRootName + "/thumb";
			createFilePath(uploadPath);
		} else {
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");

			// System.out.println("============realPath:" + realPath);
			int rootIndex = realPath.indexOf(jbossName);
			if (rootIndex < 0) {
				rootIndex = realPath.indexOf("war");
			}

			if (rootIndex < 0) {
				return null;
			} else {
				realPath = realPath.substring(0, rootIndex);
				uploadPath = realPath + uploadRootName + "/thumb";
				createFilePath(uploadPath);
			}
		}
		return uploadPath;
	}

	private void finishPrintResponseMsg(HttpServletResponse response,
			StringBuffer responseMessage) {
		try {
			responseMessage.append("</root>");
			System.out.println(responseMessage);
			response.getWriter().print(responseMessage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
