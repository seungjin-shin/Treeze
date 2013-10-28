package com.hansung.treeze.controller.ajax;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.Slide;
import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.service.TicketService;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;



@Controller
@RequestMapping("/ajax")
//@SessionAttributes(LoginFilter.SIGNED_USER)
public class UploadSlideController {
	@Autowired private TicketService ticketService;
	
	
	

}
