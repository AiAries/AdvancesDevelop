package com.bankcomm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	/** 日志保存路径 */
	private static final String LOG_SAVE_PATH = "/sdcard/Log_PRO/"; // sd卡上的日志文件目录
	/** 日志开关 */
	private static final boolean LOG_SWITCH = true;
	private static Log _lg;
	// 日志保存的数量 ,默认保存最近10天的日志
	public int logSaveNum = 10;

	// single
	public static Log getInstance() {
		if (_lg == null) {
			_lg = new Log();
		}
		return _lg;
	}



	/** 插入日志 */
	public void writeLog(String msg) {

		if (msg == null)
			return;
		System.out.println(msg);
		if (LOG_SWITCH) {
			File file = checkLogFileIsExist();
			if (file == null)
				return;
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file, true);
				fos.write((new Date().toLocaleString() + "	" + msg)
						.getBytes("gbk"));
				fos.write("\r\n".getBytes("gbk"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				fos = null;
				file = null;
			}
		}
	}
	
	/** 插入日志 */
	public void writeLog(String msg, Throwable ex) {
		if (msg == null)
			return;
		System.out.println(msg);
		if (LOG_SWITCH) {
			File file = checkLogFileIsExist();
			if (file == null)
				return;
			msg += getExceptionInfo(ex);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file, true);
				fos.write((new Date().toLocaleString() + "	" + msg)
						.getBytes("gbk"));
				fos.write("\r\n".getBytes("gbk"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				fos = null;
				file = null;
			}
		}
	}

	/**
	 * 打印异常堆栈信息
	 * 
	 * @param e
	 * @return
	 */
	public void WriteStackTrace(Throwable e) {
		if (e != null) {
			writeLog(e.getStackTrace().toString());
		}
	}

	private Log() {
		CheckLogDele();

	}

	private void CheckLogDele() {

	}



	/** 检查日志文件是否存在 */
	private File checkLogFileIsExist() {
		File file = new File(LOG_SAVE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		file = new File(LOG_SAVE_PATH + dateStr + ".txt");
		if (!isLogExist(file)) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sdf = null;
		return file;
	}
	
	public String getExceptionInfo(Throwable ex){
		String result = null;
		try{
			
			Writer info = new StringWriter();
			PrintWriter printWrite = new PrintWriter(info);
			ex.printStackTrace(printWrite);
			
			Throwable cause = ex.getCause();
			while(cause != null){
				cause.printStackTrace(printWrite);
				cause = cause.getCause();
			}
			result = info.toString();
			printWrite.close();
		}
		catch(Exception e){
			
		}
		return result;
	}

	/**
	 * 检查当天日志文件是否存在
	 * 
	 * @param file
	 * @return
	 */
	private boolean isLogExist(File file) {
		boolean ret = false;
		try {
			File tempFile = new File(LOG_SAVE_PATH);
			File[] files = tempFile.listFiles();
			if (files == null)
				return ret;
			for (int i = 0; i < files.length; i++) {
				String name = files[i].getName().trim();
				if (name != null && name.equalsIgnoreCase(file.getName())) {
					ret = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
