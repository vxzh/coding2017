package com.github.fei9009.coderising0305.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fei9009.coderising0305.download.api.ConnectionManager;
import com.github.fei9009.coderising0305.download.api.DownloadListener;
import com.github.fei9009.coderising0305.download.impl.ConnectionManagerImpl;



public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		
		String url = "http://images2015.cnblogs.com/blog/610238/201604/610238-20160421154632101-286208268.png";
		
		FileDownloader downloader = new FileDownloader(url,"c:\\ctest.jpg");

	
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});

		
		downloader.execute();
		
		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");
		

	}

}
