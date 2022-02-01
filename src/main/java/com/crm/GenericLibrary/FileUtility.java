package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
		
		public String readDatafromPropertyfile(String key) throws IOException
		{
			FileInputStream fs=new FileInputStream(IPathConstant.FilePath);
			Properties pLoad=new Properties();
			pLoad.load(fs);
			return pLoad.getProperty(key);
		}


}
