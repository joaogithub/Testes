package com.testes.utils;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {


	/**
	 * checks if network is available
	 * @return boolean indicating if there is network or not
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	/**
	 * Creates a Document from a string
	 * @param xml The string from wich a new Document is going to be created
	 * @return the new Document
	 * @throws Exception 
	 */
	public static Document loadXMLFromString(DocumentBuilderFactory factory,String xml) throws Exception{
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
	
}
